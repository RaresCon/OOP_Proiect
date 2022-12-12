package site.pages;

public class PageFactory {
    public Page getPage(PageTypes pageType) {
        switch (pageType) {
            case HOMEPAGE_NOAUTH -> {
                return new HomePage(PageTypes.HOMEPAGE_NOAUTH);
            }
            case HOMEPAGE_AUTH -> {
                return new HomePageAuth();
            }
            case REGISTERPAGE -> {
                return new RegisterPage();
            }
            case LOGINPAGE -> {
                return new LoginPage();
            }
            case UPGRADESPAGE -> {
                return new UpgradesPage();
            }
            case MOVIESPAGE -> {
                return new MoviesPage();
            }
            case DETAILSPAGE -> {
                return new DetailsPage();
            }
        }
        return null;
    }
}
