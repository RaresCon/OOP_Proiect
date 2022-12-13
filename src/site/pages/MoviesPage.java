package site.pages;

import site.movies.Movie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MoviesPage extends Page {
    private List<Movie> movies = new ArrayList<>();

    public MoviesPage(PageTypes pageType) {
        super(pageType);
    }

    @Override
    public void linkToPages() {
        accessiblePages.put("homepage", PageTypes.HOMEPAGE_AUTH);
        accessiblePages.put("see details", PageTypes.DETAILSPAGE);
        accessiblePages.put("logout", PageTypes.LOGOUTPAGE);
    }

    @Override
    public List<Movie> getCurrentMovies() {
        return Collections.unmodifiableList(movies);
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
