package site;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import input.ActionInput;
import jdk.jshell.execution.Util;
import site.account.Account;
import site.movies.Movie;
import site.pages.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public final class SiteStructure {
    private final HashMap<PageTypes, Page> pageStructure = new HashMap<>();
    private final ArrayList<Account> users = new ArrayList<>();
    private Account currentUser;
    private Page currentPage;

    public SiteStructure() {
        PageFactory factory = new PageFactory();
        pageStructure.put(PageTypes.HOMEPAGE_NOAUTH, factory.getPage(PageTypes.HOMEPAGE_NOAUTH));
        pageStructure.put(PageTypes.HOMEPAGE_AUTH, factory.getPage(PageTypes.HOMEPAGE_AUTH));
        pageStructure.put(PageTypes.REGISTERPAGE, factory.getPage(PageTypes.REGISTERPAGE));
        pageStructure.put(PageTypes.LOGINPAGE, factory.getPage(PageTypes.LOGINPAGE));
        pageStructure.put(PageTypes.UPGRADESPAGE, factory.getPage(PageTypes.UPGRADESPAGE));
        pageStructure.put(PageTypes.MOVIESPAGE, factory.getPage(PageTypes.MOVIESPAGE));
        pageStructure.put(PageTypes.DETAILSPAGE, factory.getPage(PageTypes.DETAILSPAGE));
        pageStructure.put(PageTypes.LOGOUTPAGE, factory.getPage(PageTypes.LOGOUTPAGE));

        linkPagesAuth();
        currentPage = pageStructure.get(PageTypes.HOMEPAGE_NOAUTH);
    }

    public ObjectNode changePage(ActionInput nextPage) {
        ObjectNode output = JsonNodeFactory.instance.objectNode();
        output.put("error", "null");

        PageTypes nextPageType = currentPage.nextPage(nextPage);

        if (nextPageType == PageTypes.DETAILSPAGE) {
            Movie foundMovie = currentPage.containsMovie(nextPage.getMovie());

            if (foundMovie != null) {
                currentPage = pageStructure.get(nextPageType);
                ((DetailsPage) currentPage).setMovieToDetail(foundMovie);
            } else {
                output.put("error", "Error");
            }
        } else if (nextPageType != null) {
            currentPage = pageStructure.get(nextPageType);
        } else {
            output.put("error", "Error");
        }

        output.replace("currentMovies", Utility.movieListOutput(currentPage.getCurrentMovies()));
        if (currentUser == null) {
            output.put("currentUser", "null");
        } else {
            output.replace("currentUser", Utility.userOutput(currentUser));
        }

        return output;
    }

    public ObjectNode executeAction(ActionInput action) {
        ObjectNode output = JsonNodeFactory.instance.objectNode();
        output.put("error", "null");

        output.replace("currentMovies", Utility.movieListOutput(currentPage.getCurrentMovies()));
        if (currentUser == null) {
            output.put("currentUser", "null");
        } else {
            output.replace("currentUser", Utility.userOutput(currentUser));
        }

        if (!currentPage.getAvailableActions().containsKey(action.getFeature())) {
            output.put("error", "Error");
            return output;
        }

        if (!currentPage.getAvailableActions().get(action.getFeature())
                                              .executeAction(action, this)) {
            output.put("error", "Error");
            return output;
        }

        return output;
    }

    public void linkPagesAuth() {
        for (Map.Entry<PageTypes, Page> page : pageStructure.entrySet()) {
            page.getValue().linkToPages();
        }
    }

    public HashMap<PageTypes, Page> getPageStructure() {
        return pageStructure;
    }

    public ArrayList<Account> getUsers() {
        return users;
    }

    public Account getCurrentUser() {
        return currentUser;
    }

    public Page getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Page currentPage) {
        this.currentPage = currentPage;
    }

    public void setCurrentUser(Account currentUser) {
        this.currentUser = currentUser;
    }
}
