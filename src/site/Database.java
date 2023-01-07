package site;

import com.fasterxml.jackson.databind.node.ObjectNode;
import input.ActionInput;
import input.Input;
import site.account.Account;
import site.account.AccountFactory;
import site.actions.AdminActions;
import site.movies.Movie;
import site.notifications.DatabaseObserver;
import site.notifications.Observable;
import site.pages.Page;
import site.pages.PageFactory;
import site.pages.PageState;
import site.pages.PageTypes;

import java.util.*;

import static site.ResponseCodes.ERROR;
import static site.ResponseCodes.RECOMMENDATION;

public final class Database extends Observable {
    private final HashMap<PageTypes, Page> pageStructure = new HashMap<>();
    private final List<Account> usersDataBase = new ArrayList<>();
    private final List<Movie> currentMoviesList = new ArrayList<>();
    private final List<Movie> moviesDataBase = new ArrayList<>();
    private final HashMap<String, AdminActions> adminActions = new HashMap<>();
    private Account currentUser;
    private Page currentPage;
    private String currentMovie;
    private final Stack<PageState> pagesStack = new Stack<>();

    /**
     * the database construction with all the pages created by the site architect
     * the site architect has to put here all the pages that he wants to have on the site
     */
    public Database() {
        PageFactory factory = PageFactory.getInstance();
        pageStructure.put(PageTypes.HOMEPAGE_NOAUTH, factory.getPage(PageTypes.HOMEPAGE_NOAUTH));
        pageStructure.put(PageTypes.HOMEPAGE_AUTH, factory.getPage(PageTypes.HOMEPAGE_AUTH));
        pageStructure.put(PageTypes.REGISTERPAGE, factory.getPage(PageTypes.REGISTERPAGE));
        pageStructure.put(PageTypes.LOGINPAGE, factory.getPage(PageTypes.LOGINPAGE));
        pageStructure.put(PageTypes.UPGRADESPAGE, factory.getPage(PageTypes.UPGRADESPAGE));
        pageStructure.put(PageTypes.MOVIESPAGE, factory.getPage(PageTypes.MOVIESPAGE));
        pageStructure.put(PageTypes.DETAILSPAGE, factory.getPage(PageTypes.DETAILSPAGE));
        pageStructure.put(PageTypes.LOGOUTPAGE, factory.getPage(PageTypes.LOGOUTPAGE));

        linkPagesAuth();
        currentPage = pageStructure.get(PageTypes.HOMEPAGE_NOAUTH);
        adminActions.put("add", AdminActions.ADD_MOVIE);
        adminActions.put("delete", AdminActions.DELETE_MOVIE);
        this.addObserver(new DatabaseObserver(this));
    }

    /**
     * method to load in the database for the current session
     * @param input input from the current session
     */
    public void loadDataBase(final Input input) {
        moviesDataBase.addAll(input.getMovies());

        AccountFactory factory = AccountFactory.getInstance();
        input.getUsers().forEach(user -> usersDataBase
                                         .add(factory.getAccount(user.getCredentials())));
    }

    /**
     * method to check if a change of page is possible for the current site
     * @param action the action giving the next page
     * @return outputs "Error" if the change was not possible, "OK" otherwise
     */
    public ObjectNode changePage(final ActionInput action) {
        if (currentPage.setNextPage(action, this)) {
            return currentPage.setState(action, this);
        } else {
            return Utility.response(null, ERROR);
        }
    }

    /**
     * method to check if a "back" action is possible
     * @return outputs "Error" if the change was not possible, "OK" or null otherwise
     */
    public ObjectNode backPage() {
        if (currentUser != null && !pagesStack.empty()) {
            PageState prevPageState = pagesStack.pop();

            currentPage = pageStructure.get(prevPageState.pageType());
            return currentPage.setState(prevPageState, this);
        } else {
            return Utility.response(null, ERROR);
        }
    }

    /**
     * method to check and execute a certain feature on the current page
     * @param action the action giving information about the feature
     * @return outputs "Error" if the change was not possible, "OK" otherwise
     */
    public ObjectNode executeAction(final ActionInput action) {
        if (!currentPage.getAvailableActions().containsKey(action.getFeature())) {
            return Utility.response(null, ERROR);
        }

        return currentPage.getAvailableActions().get(action.getFeature())
                          .executeAction(action, this);
    }

    /**
     * method to check and execute a certain action that affects the database
     * @param action the action giving information about the feature
     * @return the response from the database
     */
    public ObjectNode modifyDatabase(final ActionInput action) {
        if (!adminActions.containsKey(action.getFeature())) {
            return Utility.response(this, ERROR);
        }

        return adminActions.get(action.getFeature()).executeAction(action, this);
    }

    /**
     * method that adds a recommendation to the notifications of the current user
     * @return outputs the format for a recommendation after all actions are completed
     */
    public ObjectNode getRecommendation() {
        if (currentUser != null && currentUser.recommendMovie(getAvailableMovies())) {
            return Utility.response(this, RECOMMENDATION);
        }

        return null;
    }

    /**
     * links pages when a new session is established
     */
    public void linkPagesAuth() {
        for (Map.Entry<PageTypes, Page> page : pageStructure.entrySet()) {
            page.getValue().linkToPages();
        }
    }

    /**
     * method that returns a list of available movies from the database for the user
     * @return the list of available movies
     */
    public List<Movie> getAvailableMovies() {
        List<Movie> availableMovies = new ArrayList<>();

        moviesDataBase.forEach(movie -> {
            if (!movie.getCountriesBanned().contains(currentUser.getCreds().getCountry())) {
                availableMovies.add(movie);
            }
        });

        return availableMovies;
    }

    /**
     * method to return a movie from a list if it is present by a given name
     * @param list the list of movies in which it searches
     * @param name the name of the movie for which it searches
     * @return null if the movie isn't present, the movie object otherwise
     */
    public Movie getMovieFromList(final List<Movie> list, final String name) {
        for (Movie movie : list) {
            if (movie.getName().equals(name)) {
                return movie;
            }
        }

        return null;
    }

    /**
     * getter
     * @return the page structure of the current site
     */
    public HashMap<PageTypes, Page> getPageStructure() {
        return pageStructure;
    }

    /**
     * getter
     * @return the users database
     */
    public List<Account> getUsersDataBase() {
        return usersDataBase;
    }

    /**
     * getter
     * @return the current page on which the user can execute actions
     */
    public Page getCurrentPage() {
        return currentPage;
    }

    /**
     * getter
     * @return the current logged user
     */
    public Account getCurrentUser() {
        return currentUser;
    }

    /**
     * setter
     * @param currentPage the page to be set as the current page
     */
    public void setCurrentPage(final Page currentPage) {
        this.currentPage = currentPage;
    }

    /**
     * setter
     * @param currentUser the user to be set as the current page
     */
    public void setCurrentUser(final Account currentUser) {
        this.currentUser = currentUser;
    }

    /**
     * getter
     * @return the list of visible movies to the user
     */
    public List<Movie> getCurrentMoviesList() {
        return currentMoviesList;
    }

    /**
     * getter
     * @return the movies database
     */
    public List<Movie> getMoviesDataBase() {
        return moviesDataBase;
    }

    /**
     * getter
     * @return the name of the current accessed movie
     */
    public String getCurrentMovie() {
        return currentMovie;
    }

    /**
     * setter
     * @param currentMovie the name of the current accessed movie
     */
    public void setCurrentMovie(final String currentMovie) {
        this.currentMovie = currentMovie;
    }

    /**
     * getter
     * @return the stack of page states of the previous pages
     */
    public Stack<PageState> getPagesStack() {
        return pagesStack;
    }
}
