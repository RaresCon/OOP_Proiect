package site.account;

import site.pages.*;

public final class AccountFactory {
    public Account getAccount(String accountType, Credentials creds) {
        switch (accountType) {
            case "premium" -> {
                return new PremiumAccount(creds);
            }
            case "standard" -> {
                return new StandardAccount(creds);
            }
        }
        return null;
    }
}
