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
     * function to set the next page
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
     * method that takes a previous page state and sets it as current
     * @param site the database on which the state is set
     * @return outputs different responses according to the page of the page state
     */
    public ObjectNode setPrevPage(final Database site) {
        PageState prevState = site.getPagesStack().pop();

        site.setCurrentPage(site.getPageStructure().get(prevState.pageType()));
        site.getCurrentMoviesList().clear();
        if (prevState.pageType() == PageTypes.MOVIESPAGE) {
            site.getCurrentPage().setState(null, site);
        } else if (prevState.pageType() == PageTypes.HOMEPAGE_AUTH
                   || prevState.pageType() == PageTypes.UPGRADESPAGE) {
            return null;
        } else {
            site.getCurrentMoviesList().addAll(prevState.pageMovies());
        }
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
