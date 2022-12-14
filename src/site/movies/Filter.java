package site.movies;

import input.ContainsInput;
import input.SortInput;

import java.util.Comparator;
import java.util.List;

public class Filter {
    public static void sortMovies(List<Movie> movies, SortInput sortFilters) {
        if (sortFilters.getRating() != null && sortFilters.getRating().equals("increasing")) {
            movies.sort(Comparator.comparingDouble(Movie::getRating));
        } else if (sortFilters.getRating().equals("decreasing")) {
            movies.sort((o1, o2) -> Double.compare(o2.getRating(), o1.getRating()));
        }

        if (sortFilters.getDuration() != null && sortFilters.getDuration().equals("increasing")) {
            movies.sort(Comparator.comparingDouble(Movie::getDuration));
        } else if (sortFilters.getRating().equals("decreasing")) {
            movies.sort((o1, o2) -> Integer.compare(o2.getDuration(), o1.getDuration()));
        }
    }

    public static void filterMovies(List<Movie> movies, ContainsInput filters) {
        if (filters.getActors() != null && !filters.getActors().isEmpty()) {
            movies.removeIf((movie -> !movie.getActors().containsAll(filters.getActors())));
        }

        if (filters.getGenres() != null && !filters.getGenres().isEmpty()) {
            movies.removeIf((movie -> !movie.getActors().containsAll(filters.getGenres())));
        }
    }
}
