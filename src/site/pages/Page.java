package site.pages;

import com.fasterxml.jackson.databind.node.ObjectNode;
import input.ActionInput;
import site.SiteStructure;
import site.movies.Movie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class Page {
    protected PageTypes pageType;
    protected HashMap<String, PageTypes> accessiblePages = new HashMap<>();
    protected HashMap<String, Actions> availableActions = new HashMap<>();

    public Page(PageTypes pageType) {
        this.pageType = pageType;
    }

    public abstract void linkToPages();

    public boolean setNextPage(ActionInput action, SiteStructure site) {
        if (accessiblePages.containsKey(action.getPage())) {
            site.setCurrentPage(site.getPageStructure().get(accessiblePages.get(action.getPage())));
            return true;
        }

        return false;
    }

    public ObjectNode setState(ActionInput input, SiteStructure site) {
        site.getCurrentMoviesList().clear();
        return null;
    }

    public HashMap<String, Actions> getAvailableActions() {
        return availableActions;
    }
}
