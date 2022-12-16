package site.pages;

import input.ActionInput;
import site.SiteStructure;

public class LoginPage extends Page {
    public LoginPage(PageTypes pageType) {
        super(pageType);
        availableActions.put("login", Actions.LOGIN);
    }

    @Override
    public void linkToPages() {
        accessiblePages.put("homepage auth", PageTypes.HOMEPAGE_AUTH);
    }
}
