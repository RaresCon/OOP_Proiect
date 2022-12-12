package site.pages;

public class UpgradesPage extends Page {
    public UpgradesPage(PageTypes pageType) {
        super(pageType);
    }

    @Override
    public void linkToPages() {
        accessiblePages.put("homepage auth", PageTypes.HOMEPAGE_AUTH);
        accessiblePages.put("movies", PageTypes.MOVIESPAGE);
        accessiblePages.put("logout", PageTypes.LOGOUTPAGE);
    }
}
