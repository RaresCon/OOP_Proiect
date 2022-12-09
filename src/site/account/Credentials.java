package site.account;

public class Credentials {
    private String name;
    private String passwordHash;
    private AccountType accountType;
    private String country;
    private int balance;

    public Credentials(String name, String passwordHash, AccountType type,
                       String country, int balance) {
        this.name = name;
        this.passwordHash = passwordHash;
        this.accountType = type;
        this.country = country;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
