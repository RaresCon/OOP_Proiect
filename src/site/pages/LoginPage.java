package site.pages;

public class LoginPage extends Page {
    public LoginPage(PageTypes pageType) {
        super(pageType);
    }

    @Override
    public void linkToPages() {
        accessiblePages.put("homepage auth", PageTypes.HOMEPAGE_AUTH);
    }
}
