package site.pages;

public class MoviesPage extends Page {

    public MoviesPage(PageTypes pageType) {
        super(pageType);
        availableActions.put("search", Actions.SEARCH);
        availableActions.put("filter", Actions.FILTER);
    }

    @Override
    public void linkToPages() {
        accessiblePages.put("homepage", PageTypes.HOMEPAGE_AUTH);
        accessiblePages.put("see details", PageTypes.DETAILSPAGE);
        accessiblePages.put("logout", PageTypes.LOGOUTPAGE);
    }
}
