package site.movies;

import input.ContainsInput;
import input.SortInput;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Filter {
    public static void sortMovies(List<Movie> movies, SortInput sortFilters) {
        if (sortFilters.getRating() != null) {
            if (sortFilters.getRating().equals("increasing")) {
                movies.sort(Comparator.comparingDouble(Movie::getRating));
            } else if (sortFilters.getRating().equals("decreasing")) {
                movies.sort((o1, o2) -> Double.compare(o2.getRating(), o1.getRating()));
            }
        }

        if (sortFilters.getDuration() != null) {
            if (sortFilters.getDuration().equals("increasing")) {
                movies.sort(((o1, o2) -> o1.getDuration() - o2.getDuration()));
            } else if (sortFilters.getDuration().equals("decreasing")) {
                movies.sort((o1, o2) -> o2.getDuration() - o1.getDuration());
            }
        }
    }

    public static void filterMovies(List<Movie> movies, ContainsInput filters) {
        if (filters.getActors() != null && !filters.getActors().isEmpty()) {
            movies.removeIf(movie -> !movie.getActors().containsAll(filters.getActors()));
        }

        if (filters.getGenre() != null && !filters.getGenre().isEmpty()) {
            movies.removeIf(movie -> !movie.getGenres().containsAll(filters.getGenre()));
        }
    }
}
