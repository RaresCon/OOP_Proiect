package site.account;

import site.Config;

public class StandardAccount extends Account {
    /**
     * constructor
     * @param creds the credentials used for the new user
     */
    public StandardAccount(final Credentials creds) {
        super(creds);
        numFreePremiumMovies = Config.STARTING_NUM_FREE_MOVIES;
    }

    public void refundCost() {
        tokensCount += 2;
    }
}
