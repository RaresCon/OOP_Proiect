package site.pages;

import input.ActionInput;
import site.Database;

import java.util.HashMap;

public abstract class Page implements PageFunctions {
    protected final PageTypes pageType;
    protected final HashMap<String, PageTypes> accessiblePages = new HashMap<>();
    protected final HashMap<String, Actions> availableActions = new HashMap<>();

    /**
     * constructor
     * @param pageType the type of the new page
     */
    public Page(final PageTypes pageType) {
        this.pageType = pageType;
    }

    /**
     * function to set the next page
     * @param action the action that gives the next page
     * @param site the site database
     * @return true if the change was possible, false otherwise
     */
    public boolean setNextPage(final ActionInput action, final Database site) {
        if (accessiblePages.containsKey(action.getPage())) {
            site.setCurrentPage(site.getPageStructure().get(accessiblePages.get(action.getPage())));
            site.setCurrentMovie(null);
            return true;
        }
        return false;
    }

    /**
     * getter
     * @return the available actions of the page
     */
    public HashMap<String, Actions> getAvailableActions() {
        return availableActions;
    }
}
