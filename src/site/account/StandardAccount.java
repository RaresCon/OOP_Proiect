package site.account;

public class StandardAccount extends Account {

    public StandardAccount(Credentials creds) {
        super(creds);
        numFreePremiumMovies = 0;
    }
}
