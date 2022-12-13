package site.account;

import site.movies.Movie;

import java.util.ArrayList;
import java.util.List;

public abstract class Account {
    private Credentials creds;
    private int tokensCount = 0;
    protected int numFreePremiumMovies;
    private final List<Movie> purchasedMovies = new ArrayList<>();
    private final List<Movie> watchedMovies = new ArrayList<>();
    private final List<Movie> likedMovies = new ArrayList<>();
    private final List<Movie> ratedMovies = new ArrayList<>();

    public Account(Credentials creds) {
        this.creds = new Credentials(creds);
    }

    public boolean checkCreds(Credentials creds) {
        return this.creds.getName().equals(creds.getName())
               && this.creds.getPasswordHash().equals(creds.getPasswordHash());
    }

    public void addTokens(int toAddTokens) {
        tokensCount += toAddTokens;
    }

    public void subTokens(int toSubTokens) {
        tokensCount -= toSubTokens;
    }

    public Credentials getCreds() {
        return creds;
    }

    public int getTokensCount() {
        return tokensCount;
    }

    public List<Movie> getPurchasedMovies() {
        return purchasedMovies;
    }

    public List<Movie> getWatchedMovies() {
        return watchedMovies;
    }

    public List<Movie> getLikedMovies() {
        return likedMovies;
    }

    public List<Movie> getRatedMovies() {
        return ratedMovies;
    }

    public void setTokensCount(int tokensCount) {
        this.tokensCount = tokensCount;
    }

    public int getNumFreePremiumMovies() {
        return numFreePremiumMovies;
    }

    public void setNumFreePremiumMovies(int numFreePremiumMovies) {
        this.numFreePremiumMovies = numFreePremiumMovies;
    }
}
