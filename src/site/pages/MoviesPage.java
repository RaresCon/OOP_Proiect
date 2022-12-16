package site.pages;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import input.ActionInput;
import site.SiteStructure;
import site.Utility;
import site.movies.Movie;

public class MoviesPage extends Page {

    public MoviesPage(PageTypes pageType) {
        super(pageType);
        availableActions.put("search", Actions.SEARCH);
        availableActions.put("filter", Actions.FILTER);
    }

    @Override
    public void linkToPages() {
        accessiblePages.put("homepage", PageTypes.HOMEPAGE_AUTH);
        accessiblePages.put("see details", PageTypes.DETAILSPAGE);
        accessiblePages.put("logout", PageTypes.LOGOUTPAGE);
        accessiblePages.put("movies", PageTypes.MOVIESPAGE);
    }

    public boolean setNextPage(ActionInput action, SiteStructure site) {
        if (action.getPage().equals("see details")
            && site.listContainsMovie(site.getCurrentMoviesList(), action.getMovie()) != null) {
            site.setCurrentPage(site.getPageStructure().get(accessiblePages.get(action.getPage())));
            return true;
        } else if (action.getPage().equals("see details")) {
            return false;
        }

        return super.setNextPage(action, site);
    }

    @Override
    public ObjectNode setState(ActionInput action, SiteStructure site) {
        site.getCurrentMoviesList().clear();
        for (Movie movie : site.getMoviesDataBase()) {
            if (!movie.getCountriesBanned()
                      .contains(site.getCurrentUser().getCreds().getCountry())) {
                site.getCurrentMoviesList().add(movie);
            }
        }

        ObjectNode output = JsonNodeFactory.instance.objectNode();
        output.put("error", (String)null);
        output.replace("currentMoviesList", Utility.movieListOutput(site.getCurrentMoviesList()));
        output.replace("currentUser", Utility.userOutput(site.getCurrentUser()));

        return output;
    }
}
