package site.pages;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import input.ActionInput;
import site.SiteStructure;
import site.Utility;
import site.movies.Movie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DetailsPage extends Page {

    public DetailsPage(PageTypes pageType) {
        super(pageType);
        availableActions.put("purchase", Actions.BUY_MOVIE);
        availableActions.put("watch", Actions.WATCH_MOVIE);
        availableActions.put("like", Actions.LIKE_MOVIE);
        availableActions.put("rate", Actions.RATE_MOVIE);
    }

    @Override
    public void linkToPages() {
        accessiblePages.put("homepage", PageTypes.HOMEPAGE_AUTH);
        accessiblePages.put("movies", PageTypes.MOVIESPAGE);
        accessiblePages.put("upgrades", PageTypes.UPGRADESPAGE);
        accessiblePages.put("logout", PageTypes.LOGOUTPAGE);
    }

    public ObjectNode setState(ActionInput action, SiteStructure site) {
        Movie foundMovie = site.listContainsMovie(site.getCurrentMoviesList(), action.getMovie());

        site.getCurrentMoviesList().clear();
        site.getCurrentMoviesList().add(foundMovie);

        ObjectNode output = JsonNodeFactory.instance.objectNode();
        output.put("error", (String)null);
        output.replace("currentMoviesList", Utility.movieListOutput(site.getCurrentMoviesList()));
        output.replace("currentUser", Utility.userOutput(site.getCurrentUser()));

        return output;
    }
}
