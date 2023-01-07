package site.pages;

public final class PageFactory {
    private static PageFactory instance = null;

    /**
     * default private constructor for Singleton
     */
    private PageFactory() {
    }

    /**
     * getter
     * @return the instance of PageFactory
     */
    public static PageFactory getInstance() {
        if (instance == null) {
            instance = new PageFactory();
        }

        return instance;
    }

    /**
     * factory to get a new type of page
     * @param pageType the requested page type
     * @throws IllegalArgumentException if the requested page isn't recognized by the factory
     * @return the newly created instance of the requested page
     */
    public Page getPage(final PageTypes pageType) {
        switch (pageType) {
            case HOMEPAGE_NOAUTH -> {
                return new HomePageNoAuth(PageTypes.HOMEPAGE_NOAUTH);
            }
            case HOMEPAGE_AUTH -> {
                return new HomePageAuth(PageTypes.HOMEPAGE_AUTH);
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
            default -> throw new IllegalArgumentException("PAGE FACTORY ERROR: The requested page"
                                                          + "is not implemented.");
        }
    }
}
