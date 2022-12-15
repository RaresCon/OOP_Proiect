package site.account;

public class Credentials {
    private String name;
    private String password;
    private String accountType;
    private String country;
    private int balance;

    public Credentials() {
    }

    public Credentials(Credentials creds) {
        name = creds.name;
        password = creds.password;
        accountType = creds.accountType;
        country = creds.country;
        balance = creds.balance;
    }

    public void subBalance(int balanceToSub) {
        balance -= balanceToSub;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
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

    public String getPassword() {
        return password;
    }
}
