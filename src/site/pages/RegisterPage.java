package site.pages;

public class RegisterPage extends Page {
    public RegisterPage(PageTypes pageType) {
        super(pageType);
    }

    @Override
    public void linkToPages() {
        accessiblePages.put("homepage auth", PageTypes.HOMEPAGE_AUTH);
    }
}