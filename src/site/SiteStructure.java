package site;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import input.ActionInput;
import site.account.Account;
import site.movies.Movie;
import site.pages.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class SiteStructure {
    private final HashMap<PageTypes, Page> pageStructure = new HashMap<>();
    private final ArrayList<Account> users = new ArrayList<>();
    private final ArrayList<Movie> currentMoviesList = new ArrayList<>();
    private final ArrayList<Movie> moviesDataBase = new ArrayList<>();
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
        if (currentPage.setNextPage(nextPage, this)) {
            return currentPage.setState(nextPage, this);
        } else {
            return Utility.getError();
        }
    }

    public ObjectNode executeAction(ActionInput action) {
        ObjectNode output = JsonNodeFactory.instance.objectNode();
        output.put("error", (String)null);

        if (!currentPage.getAvailableActions().containsKey(action.getFeature())) {
            return Utility.getError();
        }

        if (!currentPage.getAvailableActions().get(action.getFeature())
                                              .executeAction(action, this)) {
            return Utility.getError();
        }

        output.replace("currentMoviesList", Utility.movieListOutput(currentMoviesList));
        if (currentUser == null) {
            output.put("currentUser", (String)null);
        } else {
            output.replace("currentUser", Utility.userOutput(currentUser));
        }

        if (action.getFeature().equals("buy tokens")
            || action.getFeature().equals("buy premium account"))
            return null;

        return output;
    }

    public void linkPagesAuth() {
        for (Map.Entry<PageTypes, Page> page : pageStructure.entrySet()) {
            page.getValue().linkToPages();
        }
    }

    public Movie listContainsMovie(List<Movie> list, String name) {
        for (Movie movie : list) {
            if (movie.getName().equals(name)) {
                return movie;
            }
        }

        return null;
    }

    public HashMap<PageTypes, Page> getPageStructure() {
        return pageStructure;
    }

    public ArrayList<Account> getUsers() {
        return users;
    }

    public Page getCurrentPage() {
        return currentPage;
    }

    public Account getCurrentUser() {
        return currentUser;
    }

    public void setCurrentPage(Page currentPage) {
        this.currentPage = currentPage;
    }

    public void setCurrentUser(Account currentUser) {
        this.currentUser = currentUser;
    }

    public ArrayList<Movie> getCurrentMoviesList() {
        return currentMoviesList;
    }

    public ArrayList<Movie> getMoviesDataBase() {
        return moviesDataBase;
    }
}
