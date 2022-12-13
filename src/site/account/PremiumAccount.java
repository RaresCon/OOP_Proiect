package site.account;

public class PremiumAccount extends Account {

    public PremiumAccount(Credentials creds) {
        super(creds);
        numFreePremiumMovies = 15;
    }
}
