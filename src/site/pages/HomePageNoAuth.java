package site.pages;

public class HomePageNoAuth extends Page {
    public HomePageNoAuth(PageTypes pageType) {
        super(pageType);
    }

    @Override
    public void linkToPages() {
        accessiblePages.put("login", PageTypes.LOGINPAGE);
        accessiblePages.put("register", PageTypes.REGISTERPAGE);
    }
}
