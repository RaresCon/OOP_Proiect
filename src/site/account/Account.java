package site.account;

import site.movies.Movie;
import site.notifications.Notification;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class Account {
    protected Credentials creds;
    protected int tokensCount = 0;
    protected int numFreePremiumMovies;
    protected final List<Movie> purchasedMovies = new ArrayList<>();
    protected final List<Movie> watchedMovies = new ArrayList<>();
    protected final List<Movie> likedMovies = new ArrayList<>();
    protected final List<Movie> ratedMovies = new ArrayList<>();
    protected final HashMap<String, Integer> ratings = new HashMap<>();
    protected final HashMap<String, Integer> likedGenres = new HashMap<>();
    protected final List<Notification> notifications = new ArrayList<>();
    protected final List<String> subbedGenres = new ArrayList<>();

    /**
     * constructor
     * @param creds the credentials used for the new user
     */
    public Account(final Credentials creds) {
        this.creds = new Credentials(creds);
    }

    /**
     * abstract method that refunds the cost of a user for a deleted movie
     */
    public abstract void refundCost();

    /**
     * abstract method that adds a recommendation in the list of notifications
     * @param availableMovies the list of available movies for the current user
     * @return true if the current user account can get a recommendation,
     *         false otherwise
     */
    public abstract boolean recommendMovie(List<Movie> availableMovies);

    /**
     * method to check if two credentials match, used during login action
     * @param credentials the passed credentials by the user at input
     * @return true if the credentials match, false otherwise
     */
    public boolean checkCreds(final Credentials credentials) {
        return this.creds.getName().equals(credentials.getName())
               && this.creds.getPassword().equals(credentials.getPassword());
    }

    /**
     * method to add a certain amount of tokens to the user
     * @param toAddTokens the amount of tokens to add
     */
    public void addTokens(final int toAddTokens) {
        tokensCount += toAddTokens;
    }

    /**
     * method to subtract a certain amout of tokens from the user
     * @param toSubTokens the amount of tokens to subtract
     */
    public void subTokens(final int toSubTokens) {
        tokensCount -= toSubTokens;
    }

    /**
     * method to decrement the number of free premium movies after purchasing one movie
     */
    public void subNumFreePremiumMovies() {
        numFreePremiumMovies -= 1;
    }

    /**
     * method to add a liked genre to the list of a user
     * if the genre already exists, it increments the number of likes
     * @param movie liked movie
     */
    public void addLikedGenres(final Movie movie) {
        movie.getGenres().forEach(genre -> {
            likedGenres.computeIfAbsent(genre, value -> 0);
            likedGenres.computeIfPresent(genre, (key, value) -> value + 1);
        });
    }

    /**
     * getter
     * @return the credentials of a user
     */
    public Credentials getCreds() {
        return creds;
    }

    /**
     * getter
     * @return the amount of tokens of a user
     */
    public int getTokensCount() {
        return tokensCount;
    }

    /**
     * getter
     * @return the list of purchased movies
     */
    public List<Movie> getPurchasedMovies() {
        return purchasedMovies;
    }

    /**
     * getter
     * @return the list of watched movies
     */
    public List<Movie> getWatchedMovies() {
        return watchedMovies;
    }

    /**
     * getter
     * @return the list of liked movies
     */
    public List<Movie> getLikedMovies() {
        return likedMovies;
    }

    /**
     * getter
     * @return the list of rated movies
     */
    public List<Movie> getRatedMovies() {
        return ratedMovies;
    }

    /**
     * getter
     * @return the number of remaining free premium movies
     */
    public int getNumFreePremiumMovies() {
        return numFreePremiumMovies;
    }

    /**
     * getter
     * @return the liked genres hashmap
     */
    public HashMap<String, Integer> getLikedGenres() {
        return likedGenres;
    }

    /**
     * getter
     * @return the ratings hashmap for rated movies
     */
    public HashMap<String, Integer> getRatings() {
        return ratings;
    }

    /**
     * getter
     * @return the list of notifications
     */
    public List<Notification> getNotifications() {
        return notifications;
    }

    /**
     * getter
     * @return the list of genres to which the user is subscribed
     */
    public List<String> getSubbedGenres() {
        return subbedGenres;
    }
}
