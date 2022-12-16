package site.pages;

import input.ActionInput;
import site.SiteStructure;

public class HomePageAuth extends Page {
    public HomePageAuth(PageTypes pageType) {
        super(pageType);
    }

    @Override
    public void linkToPages() {
        accessiblePages.put("movies", PageTypes.MOVIESPAGE);
        accessiblePages.put("upgrades", PageTypes.UPGRADESPAGE);
        accessiblePages.put("logout", PageTypes.LOGOUTPAGE);
    }
}
