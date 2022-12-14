package site.pages;

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
