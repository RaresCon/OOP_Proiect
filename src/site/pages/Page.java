package site.pages;

import com.fasterxml.jackson.databind.node.ObjectNode;
import input.ActionInput;
import site.Database;
import site.Utility;
import site.actions.UserActions;

import java.util.HashMap;

import static site.ResponseCodes.OK;

public abstract class Page implements PageFunctions {
    protected final PageTypes pageType;
    protected final HashMap<String, PageTypes> accessiblePages = new HashMap<>();
    protected final HashMap<String, UserActions> availableActions = new HashMap<>();

    /**
     * constructor
     * @param pageType the type of the new page
     */
    public Page(final PageTypes pageType) {
        this.pageType = pageType;
    }

    /**
     * method to set the next page
     * @param action the action that gives the next page
     * @param site the site database
     * @return true if the change was possible, false otherwise
     */
    public boolean setNextPage(final ActionInput action, final Database site) {
        if (accessiblePages.containsKey(action.getPage())) {
            if (site.getCurrentUser() != null) {
                site.getPagesStack().add(new PageState(site.getCurrentPage().pageType,
                                                       site.getCurrentMoviesList(),
                                                       site.getCurrentMovie()));
            }
            site.setCurrentPage(site.getPageStructure().get(accessiblePages.get(action.getPage())));
            site.setCurrentMovie(null);

            return true;
        }
        return false;
    }

    /**
     * overload method for setState that uses a PageState as input
     * @param prevState the state of the previous page
     * @param site the database
     * @return the response from the database
     */
    public ObjectNode setState(final PageState prevState, final Database site) {
        site.setCurrentPage(site.getPageStructure().get(prevState.pageType()));
        site.getCurrentMoviesList().clear();
        site.setCurrentMovie(prevState.currentMovie());

        return Utility.response(site, OK);
    }

    /**
     * getter
     * @return the available actions of the page
     */
    public HashMap<String, UserActions> getAvailableActions() {
        return availableActions;
    }
}
