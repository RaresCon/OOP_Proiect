package site.account;

public final class AccountFactory {
    private static AccountFactory instance = null;

    /**
     * default private constructor for Singleton
     */
    private AccountFactory() {
    }

    /**
     * getter
     * @return the instance of this factory
     */
    public static AccountFactory getInstance() {
        if (instance == null) {
            instance = new AccountFactory();
        }

        return instance;
    }

    /**
     * factory to get a new instance of a type of account,
     * it throws
     * @param creds the credentials used for the new account
     * @throws IllegalArgumentException if the requested account type isn't
     * recognized by the factory
     * @return the newly created account
     */
    public Account getAccount(final Credentials creds) {
        switch (creds.getAccountType()) {
            case "premium" -> {
                return new PremiumAccount(creds);
            }
            case "standard" -> {
                return new StandardAccount(creds);
            }
            default -> throw new IllegalArgumentException("ACCOUNT FACTORY ERROR: The requested"
                                                          + "account type is not implemented");
        }

    }
}
