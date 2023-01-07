package site.pages;

import com.fasterxml.jackson.databind.node.ObjectNode;
import input.ActionInput;
import site.Database;
import site.Utility;

import static site.ResponseCodes.ERROR;

public class HomePageAuth extends Page {
    /**
     * constructor that also sets available actions on the page
     * @param pageType the type of the new page
     */
    public HomePageAuth(final PageTypes pageType) {
        super(pageType);
    }

    /**
     * function to link this page to other pages
     */
    public void linkToPages() {
        accessiblePages.put("movies", PageTypes.MOVIESPAGE);
        accessiblePages.put("upgrades", PageTypes.UPGRADESPAGE);
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
        site.setCurrentMovie(null);

        return null;
    }

    /**
     * special method override for this specific page
     * @param site the database
     * @return "Error" if the "back" action is not possible, null otherwise
     */
    public ObjectNode setPrevPage(final Database site) {
        if (super.setPrevPage(site) == null) {
            return Utility.response(null, ERROR);
        }

        return null;
    }
}
