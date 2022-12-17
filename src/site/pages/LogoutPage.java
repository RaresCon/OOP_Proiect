package site.pages;

import com.fasterxml.jackson.databind.node.ObjectNode;
import input.ActionInput;
import site.Database;

public class LogoutPage extends Page {
    /**
     * constructor that also sets available actions on the page
     * @param pageType the type of the new page
     */
    public LogoutPage(final PageTypes pageType) {
        super(pageType);
    }

    /**
     * function to link this page to other pages
     */
    public void linkToPages() {
        accessiblePages.put("homepage", PageTypes.HOMEPAGE_NOAUTH);
    }

    /**
     * function to set the state of the current session
     * @param action action that sets the state
     * @param site the site database
     * @return the output
     */
    public ObjectNode setState(final ActionInput action, final Database site) {
        site.setCurrentPage(site.getPageStructure().get(PageTypes.HOMEPAGE_NOAUTH));
        site.setCurrentUser(null);
        site.getCurrentMoviesList().clear();
        site.setCurrentMovie(null);

        return null;
    }
}
