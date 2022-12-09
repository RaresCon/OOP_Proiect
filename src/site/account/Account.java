package site.account;

import site.movies.Movie;

import java.util.ArrayList;
import java.util.List;

public abstract class Account {
    private Credentials creds;
    private int tokensCount = 0;
    private List<Movie> purchasedMovies = new ArrayList<>();
    private List<Movie> watchedMovies = new ArrayList<>();
    private List<Movie> likedMovies = new ArrayList<>();
    private List<Movie> ratedMovies = new ArrayList<>();


}
