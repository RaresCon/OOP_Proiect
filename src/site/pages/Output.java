package site.pages;

import site.account.Account;
import site.movies.Movie;

import java.util.ArrayList;
import java.util.List;

public class Output {
    private String error;
    private List<Movie> currentMovieList = new ArrayList<>();
    private Account currentUser;

    public void initOutput(Account currentUser, List<Movie> currentMovieList) {
        this.currentUser = currentUser;
        this.currentMovieList = currentMovieList;
    }

    public void setError(boolean ok) {
        if (!ok) {
            error = "Error";
        }
    }

    public String getError() {
        return error;
    }

    public Account getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Account currentUser) {
        this.currentUser = currentUser;
    }
}
