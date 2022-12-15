package site.pages;

import site.movies.Movie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DetailsPage extends Page {

    public DetailsPage(PageTypes pageType) {
        super(pageType);
        availableActions.put("purchase", Actions.BUY_MOVIE);
        availableActions.put("watch", Actions.WATCH_MOVIE);
        availableActions.put("like", Actions.LIKE_MOVIE);
        availableActions.put("rate", Actions.RATE_MOVIE);
    }

    @Override
    public void linkToPages() {
        accessiblePages.put("homepage", PageTypes.HOMEPAGE_AUTH);
        accessiblePages.put("movies", PageTypes.MOVIESPAGE);
        accessiblePages.put("upgrades", PageTypes.UPGRADESPAGE);
        accessiblePages.put("logout", PageTypes.LOGOUTPAGE);
    }
}
