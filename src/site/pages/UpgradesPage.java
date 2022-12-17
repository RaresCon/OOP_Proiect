package site.pages;

import com.fasterxml.jackson.databind.node.ObjectNode;
import input.ActionInput;
import site.Database;

public class UpgradesPage extends Page {
    /**
     * constructor that also sets available actions on the page
     * @param pageType the type of the new page
     */
    public UpgradesPage(final PageTypes pageType) {
        super(pageType);
        availableActions.put("buy premium account", Actions.BUY_PREMIUM);
        availableActions.put("buy tokens", Actions.BUY_TOKENS);
    }

    /**
     * function to link this page to other pages
     */
    public void linkToPages() {
        accessiblePages.put("homepage", PageTypes.HOMEPAGE_AUTH);
        accessiblePages.put("movies", PageTypes.MOVIESPAGE);
        accessiblePages.put("logout", PageTypes.LOGOUTPAGE);
    }

    /**
     * function to set the state of the current session
     * @param input action that sets the state
     * @param site the site database
     * @return the output
     */
    public ObjectNode setState(final ActionInput input, final Database site) {
        site.getCurrentMoviesList().clear();
        return null;
    }
}
