package site.pages;

import com.fasterxml.jackson.databind.node.ObjectNode;
import input.ActionInput;
import site.Database;
import site.Utility;
import site.actions.UserActions;
import site.movies.Movie;

import static site.ResponseCodes.OK;

public class DetailsPage extends Page {

    /**
     * constructor that also sets available actions on the page
     * @param pageType the type of the new page
     */
    public DetailsPage(final PageTypes pageType) {
        super(pageType);
        availableActions.put("purchase", UserActions.PURCHASE_MOVIE);
        availableActions.put("watch", UserActions.WATCH_MOVIE);
        availableActions.put("like", UserActions.LIKE_MOVIE);
        availableActions.put("unlike", UserActions.UNLIKE_MOVIE);
        availableActions.put("rate", UserActions.RATE_MOVIE);
        availableActions.put("subscribe", UserActions.SUBSCRIBE);
    }

    /**
     * function to link with other pages of the site architecture,
     * update this if you want to have any other link to any other page
     */
    public void linkToPages() {
        accessiblePages.put("homepage", PageTypes.HOMEPAGE_AUTH);
        accessiblePages.put("movies", PageTypes.MOVIESPAGE);
        accessiblePages.put("upgrades", PageTypes.UPGRADESPAGE);
        accessiblePages.put("logout", PageTypes.LOGOUTPAGE);
    }

    /**
     * function to set the state of the site when arriving at this page
     * @param action the action that sets the state
     * @param site the site database
     * @return output
     */
    public ObjectNode setState(final ActionInput action, final Database site) {
        Movie foundMovie = site.getMovieFromList(site.getCurrentMoviesList(), action.getMovie());

        site.getCurrentMoviesList().clear();
        site.setCurrentMovie(action.getMovie());
        site.getCurrentMoviesList().add(foundMovie);

        return Utility.response(site, OK);
    }
}
