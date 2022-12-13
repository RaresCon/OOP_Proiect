package site.pages;

import site.movies.Movie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DetailsPage extends Page {
    private Movie movieToDetail;

    public DetailsPage(PageTypes pageType) {
        super(pageType);
    }

    @Override
    public void linkToPages() {
        accessiblePages.put("homepage", PageTypes.HOMEPAGE_AUTH);
        accessiblePages.put("movies", PageTypes.MOVIESPAGE);
        accessiblePages.put("upgrades", PageTypes.UPGRADESPAGE);
        accessiblePages.put("logout", PageTypes.LOGOUTPAGE);
    }

    public List<Movie> getCurrentMovies() {
        return Collections.singletonList(movieToDetail);
    }

    public Movie getMovieToDetail() {
        return movieToDetail;
    }

    public void setMovieToDetail(Movie movieToDetail) {
        this.movieToDetail = movieToDetail;
    }
}
