package site.pages;

import com.fasterxml.jackson.databind.node.ObjectNode;
import input.ActionInput;
import site.Database;
import site.actions.UserActions;

public class UpgradesPage extends Page {
    /**
     * constructor that also sets available actions on the page
     * @param pageType the type of the new page
     */
    public UpgradesPage(final PageTypes pageType) {
        super(pageType);
        availableActions.put("buy premium account", UserActions.BUY_PREMIUM);
        availableActions.put("buy tokens", UserActions.BUY_TOKENS);
    }

    /**
     * method to link this page to other pages
     */
    public void linkToPages() {
        accessiblePages.put("homepage", PageTypes.HOMEPAGE_AUTH);
        accessiblePages.put("movies", PageTypes.MOVIESPAGE);
        accessiblePages.put("logout", PageTypes.LOGOUTPAGE);
    }

    /**
     * method to set the state of the current session
     * @param input action that sets the state
     * @param site the site database
     * @return the output
     */
    public ObjectNode setState(final ActionInput input, final Database site) {
        site.getCurrentMoviesList().clear();
        site.setCurrentMovie(null);
        return null;
    }

    /**
     * overload method for setState that uses a PageState as input
     * @param prevState the state of the previous page
     * @param site the database
     * @return the response from the database (null)
     */
    public ObjectNode setState(final PageState prevState, final Database site) {
        site.setCurrentPage(site.getPageStructure().get(prevState.pageType()));
        site.getCurrentMoviesList().clear();
        site.setCurrentMovie(prevState.currentMovie());

        return null;
    }
}
