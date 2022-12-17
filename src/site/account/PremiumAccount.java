package site.account;

import site.Config;

public class PremiumAccount extends Account {
    /**
     * constructor
     * @param creds the credentials used for the new user
     */
    public PremiumAccount(final Credentials creds) {
        super(creds);
        numFreePremiumMovies = Config.STARTING_NUM_FREE_MOVIES;
    }
}
