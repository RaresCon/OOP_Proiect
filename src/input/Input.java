package input;

import site.movies.Movie;

import java.util.ArrayList;
import java.util.List;

public final class Input {
    private final List<UserInput> users = new ArrayList<>();
    private final List<Movie> movies = new ArrayList<>();
    private final List<ActionInput> actions = new ArrayList<>();

    /**
     * getter
     * @return the user to be loaded in the database
     */
    public List<UserInput> getUsers() {
        return users;
    }

    /**
     * getter
     * @return the movies to be loaded in the database
     */
    public List<Movie> getMovies() {
        return movies;
    }

    /**
     * getter
     * @return all actions that happen in a session
     */
    public List<ActionInput> getActions() {
        return actions;
    }
}
