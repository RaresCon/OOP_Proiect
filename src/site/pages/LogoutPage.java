package site.pages;

public class LogoutPage extends Page {
    public LogoutPage(PageTypes pageType) {
        super(pageType);
    }

    @Override
    public void linkToPages() {
        accessiblePages.put("homepage", PageTypes.HOMEPAGE_NOAUTH);
    }
}
