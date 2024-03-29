package site.pages;

import com.fasterxml.jackson.databind.node.ObjectNode;
import input.ActionInput;
import site.Database;
import site.ResponseCodes;
import site.Utility;
import site.actions.UserActions;

public class MoviesPage extends Page {
    /**
     * constructor that also sets available actions on the page
     * @param pageType the type of the new page
     */
    public MoviesPage(final PageTypes pageType) {
        super(pageType);
        availableActions.put("search", UserActions.SEARCH);
        availableActions.put("filter", UserActions.FILTER);
    }

    /**
     * method to link this page to other pages
     */
    public void linkToPages() {
        accessiblePages.put("homepage", PageTypes.HOMEPAGE_AUTH);
        accessiblePages.put("see details", PageTypes.DETAILSPAGE);
        accessiblePages.put("logout", PageTypes.LOGOUTPAGE);
        accessiblePages.put("movies", PageTypes.MOVIESPAGE);
    }

    /**
     * method to set the next page for the current session
     * @param action the action that gives the next page
     * @param site the site database
     * @return true if the change was possible, false otherwise
     */
    public boolean setNextPage(final ActionInput action, final Database site) {
        if (action.getPage().equals("see details")
            && site.getMovieFromList(site.getCurrentMoviesList(), action.getMovie()) != null) {
            site.getPagesStack().add(new PageState(pageType,
                                                   site.getCurrentMoviesList(),
                                                   site.getCurrentMovie()));

            site.setCurrentPage(site.getPageStructure().get(accessiblePages.get(action.getPage())));
            return true;
        } else if (action.getPage().equals("see details")) {
            return false;
        }

        return super.setNextPage(action, site);
    }

    /**
     * method to set the state of the current session
     * @param action action that sets the state
     * @param site the site database
     * @return the output
     */
    public ObjectNode setState(final ActionInput action, final Database site) {
        site.getCurrentMoviesList().clear();
        site.getCurrentMoviesList().addAll(site.getAvailableMovies());

        return Utility.response(site, ResponseCodes.OK);
    }

    /**
     * overload method for setState that uses a PageState as input
     * @param prevState the state of the previous page
     * @param site the database
     * @return the response from the database
     */
    public ObjectNode setState(final PageState prevState, final Database site) {
        return setState((ActionInput) null, site);
    }
}
