package site;

import input.ActionInput;
import site.account.Account;
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
    }

    public Output changePage(String nextPage) {
        Output output = new Output();
        output.initOutput(currentUser, currentPage.getCurrentMovies());

        PageTypes nextPageType = currentPage.nextPage(nextPage);

        if (nextPageType != null) {
            currentPage = pageStructure.get(nextPageType);
        } else {
            output.setError(true);
        }

        return output;
    }

    public Output executeAction(ActionInput action) {
        Output output = new Output();
        output.initOutput(currentUser, currentPage.getCurrentMovies());

        if (!currentPage.getAvailableActions().containsKey(action.getFeature())) {
            output.setError(true);
            return output;
        }

        if (!currentPage.getAvailableActions().get(action.getFeature())
                                              .executeAction(action, this)) {
            output.setError(true);
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
