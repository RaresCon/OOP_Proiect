package site.account;

import site.Config;
import site.movies.Movie;

import java.util.List;

public final class StandardAccount extends Account {
    /**
     * constructor
     * @param creds the credentials used for the new user
     */
    public StandardAccount(final Credentials creds) {
        super(creds);
        numFreePremiumMovies = Config.STARTING_NUM_FREE_MOVIES;
    }

    @Override
    public void refundCost() {
        tokensCount += 2;
    }

    @Override
    public boolean recommendMovie(final List<Movie> availableMovies) {
        return false;
    }
}
