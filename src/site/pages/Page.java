package site.pages;

import site.movies.Movie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class Page {
    protected PageTypes pageType;
    protected HashMap<String, PageTypes> accessiblePages = new HashMap<>();
    protected HashMap<String, Actions> availableActions = new HashMap<>();

    public Page(PageTypes pageType) {
        this.pageType = pageType;
    }

    public PageTypes nextPage(String nextPage) {
        if (accessiblePages.containsKey(nextPage)) {
            return accessiblePages.get(nextPage);
        }
        return null;
    }

    public List<Movie> getCurrentMovies() {
        return null;
    }

    public HashMap<String, Actions> getAvailableActions() {
        return availableActions;
    }

    public abstract void linkToPages();
}
