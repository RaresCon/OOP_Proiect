package site.pages;

import site.account.Account;

public interface ActionsOnPage {
    void changePage(Page currentPage);
    void actionOnPage(Account user);
}
