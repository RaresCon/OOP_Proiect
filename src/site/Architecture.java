package site;

import site.account.Account;
import site.pages.HomePage;
import site.pages.Page;
import site.pages.PageTypes;

import java.util.HashMap;

public final class Architecture {
    private Account currentUser = null;
    private Page currentPage;
    private final HashMap<PageTypes, Page> pageStructure = new HashMap<>();

    public Architecture() {
        pageStructure.put(PageTypes.HOMEPAGE_NOAUTH, new HomePage(PageTypes.HOMEPAGE_NOAUTH));
        pageStructure.get(PageTypes.HOMEPAGE_NOAUTH).linkPages();
    }
}
