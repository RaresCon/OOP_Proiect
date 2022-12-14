package site.pages;

import input.ActionInput;
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

    public PageTypes nextPage(ActionInput action) {
        if (accessiblePages.containsKey(action.getPage())) {
            return accessiblePages.get(action.getPage());
        }
        return null;
    }

    public Movie containsMovie(String movie) {
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
