package site.pages;

import site.movies.Movie;

import java.util.ArrayList;
import java.util.List;

public record PageState(PageTypes pageType, List<Movie> pageMovies, String currentMovie) {
    public PageState(final PageTypes pageType,
                     final List<Movie> pageMovies,
                     final String currentMovie) {
        this.pageType = pageType;
        this.pageMovies = new ArrayList<>(pageMovies);
        this.currentMovie = currentMovie;
    }
}
