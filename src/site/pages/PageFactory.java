package site.pages;

public class PageFactory {
    public Page getPage(PageTypes pageType) {
        switch (pageType) {
            case HOMEPAGE_NOAUTH -> {
                return new HomePageNoAuth(PageTypes.HOMEPAGE_NOAUTH);
            }
            case HOMEPAGE_AUTH -> {
                return new HomePageAuth(PageTypes.HOMEPAGE_NOAUTH);
            }
            case REGISTERPAGE -> {
                return new RegisterPage(PageTypes.REGISTERPAGE);
            }
            case LOGINPAGE -> {
                return new LoginPage(PageTypes.LOGINPAGE);
            }
            case UPGRADESPAGE -> {
                return new UpgradesPage(PageTypes.UPGRADESPAGE);
            }
            case MOVIESPAGE -> {
                return new MoviesPage(PageTypes.MOVIESPAGE);
            }
            case DETAILSPAGE -> {
                return new DetailsPage(PageTypes.DETAILSPAGE);
            }
            case LOGOUTPAGE -> {
                return new LogoutPage(PageTypes.LOGOUTPAGE);
            }
        }
        return null;
    }
}
