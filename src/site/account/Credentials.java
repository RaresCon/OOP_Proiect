package site.account;

public class Credentials {
    private String name;
    private String password;
    private String accountType;
    private String country;
    private int balance;

    /**
     * default constructor
     */
    public Credentials() {
    }

    /**
     * constructor
     * @param creds the credentials used for the new user
     */
    public Credentials(final Credentials creds) {
        name = creds.name;
        password = creds.password;
        accountType = creds.accountType;
        country = creds.country;
        balance = creds.balance;
    }

    /**
     * function to subtract a certain amount of balance from a user
     * @param balanceToSub the amount to subtract from a user
     */
    public void subBalance(final int balanceToSub) {
        balance -= balanceToSub;
    }

    /**
     * getter
     * @return the name of the user
     */
    public String getName() {
        return name;
    }

    /**
     * setter
     * @param name the new name for the user
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * getter
     * @return user's account type
     */
    public String getAccountType() {
        return accountType;
    }

    /**
     * setter
     * @param accountType the new account type
     */
    public void setAccountType(final String accountType) {
        this.accountType = accountType;
    }

    /**
     * getter
     * @return user's country
     */
    public String getCountry() {
        return country;
    }

    /**
     * setter
     * @param country the new country
     */
    public void setCountry(final String country) {
        this.country = country;
    }

    /**
     * getter
     * @return user's balance
     */
    public int getBalance() {
        return balance;
    }

    /**
     * setter
     * @param balance the new balance
     */
    public void setBalance(final int balance) {
        this.balance = balance;
    }

    /**
     * getter
     * @return user's password
     */
    public String getPassword() {
        return password;
    }
}
