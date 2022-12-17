package site.pages;

import com.fasterxml.jackson.databind.node.ObjectNode;
import input.ActionInput;
import site.Database;

public class RegisterPage extends Page {
    /**
     * constructor that also sets available actions on the page
     * @param pageType the type of the new page
     */
    public RegisterPage(final PageTypes pageType) {
        super(pageType);
        availableActions.put("register", Actions.REGISTER);
    }

    /**
     * function to link this page to other pages
     */
    public void linkToPages() {
        accessiblePages.put("homepage", PageTypes.HOMEPAGE_NOAUTH);
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
}
