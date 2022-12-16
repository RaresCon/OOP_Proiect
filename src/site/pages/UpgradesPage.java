package site.pages;

import input.ActionInput;
import site.SiteStructure;

public class UpgradesPage extends Page {
    public UpgradesPage(PageTypes pageType) {
        super(pageType);
        availableActions.put("buy premium account", Actions.BUY_PREMIUM);
        availableActions.put("buy tokens", Actions.BUY_TOKENS);
    }

    @Override
    public void linkToPages() {
        accessiblePages.put("homepage", PageTypes.HOMEPAGE_AUTH);
        accessiblePages.put("movies", PageTypes.MOVIESPAGE);
        accessiblePages.put("logout", PageTypes.LOGOUTPAGE);
    }
}
