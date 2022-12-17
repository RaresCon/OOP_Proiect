package site.movies;

import input.ContainsInput;
import input.SortInput;

import java.util.Comparator;
import java.util.List;

public final class Filter {
    private Filter() {
    }

    /**
     * function to sort movies according to some settings
     * @param movies list of movies to be sorted
     * @param sortFilters settings of sorting, containing the way to sort by some parameters
     */
    public static void sortMovies(final List<Movie> movies, final SortInput sortFilters) {
        if (sortFilters.getRating() != null) {
            if (sortFilters.getRating().equals("increasing")) {
                movies.sort(Comparator.comparingDouble(Movie::getRating));
            } else if (sortFilters.getRating().equals("decreasing")) {
                movies.sort((o1, o2) -> Double.compare(o2.getRating(), o1.getRating()));
            }
        }

        if (sortFilters.getDuration() != null) {
            if (sortFilters.getDuration().equals("increasing")) {
                movies.sort((Comparator.comparingInt(Movie::getDuration)));
            } else if (sortFilters.getDuration().equals("decreasing")) {
                movies.sort((o1, o2) -> o2.getDuration() - o1.getDuration());
            }
        }
    }

    /**
     * function to filter movies according to some settings
     * @param movies list of movies to be filtered
     * @param filters settings of filtering, containing the actors and genres by which to filter
     */
    public static void filterMovies(final List<Movie> movies, final ContainsInput filters) {
        if (filters.getActors() != null && !filters.getActors().isEmpty()) {
            movies.removeIf(movie -> !movie.getActors().containsAll(filters.getActors()));
        }

        if (filters.getGenre() != null && !filters.getGenre().isEmpty()) {
            movies.removeIf(movie -> !movie.getGenres().containsAll(filters.getGenre()));
        }
    }
}
