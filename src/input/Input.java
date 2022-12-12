package input;

import site.account.Account;
import site.movies.Movie;

import java.util.ArrayList;
import java.util.List;

public class Input {
    List<UserInput> users = new ArrayList<>();
    List<Movie> movies = new ArrayList<>();
    List<ActionInput> actions = new ArrayList<>();

    public List<UserInput> getUsers() {
        return users;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public List<ActionInput> getActions() {
        return actions;
    }
}
