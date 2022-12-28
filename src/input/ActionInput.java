package input;

import site.account.Credentials;
import site.movies.Movie;

public final class ActionInput {
    private String type;
    private String page;
    private String feature;
    private FilterInput filters;
    private Credentials credentials;
    private String startsWith;
    private int count;
    private String objectType;
    private String movie;
    private int rate;
    private String subscribedGenre;
    private Movie addedMovie;
    private String deletedMovie;

    /**
     * getter
     * @return action type
     */
    public String getType() {
        return type;
    }

    /**
     * getter
     * @return on which page to move
     */
    public String getPage() {
        return page;
    }

    /**
     * getter
     * @return action to be executed
     */
    public String getFeature() {
        return feature;
    }

    /**
     * getter
     * @return credentials of the user
     */
    public Credentials getCredentials() {
        return credentials;
    }

    /**
     * getter
     * @return the string with which a search is done
     */
    public String getStartsWith() {
        return startsWith;
    }

    /**
     * getter
     * @return the count of tokens
     */
    public int getCount() {
        return count;
    }

    /**
     * getter
     * @return the movie to see details
     */
    public String getMovie() {
        return movie;
    }

    /**
     * getter
     * @return the rating
     */
    public int getRate() {
        return rate;
    }

    /**
     * getter
     * @return the filters
     */
    public FilterInput getFilters() {
        return filters;
    }

    public String getSubscribedGenre() {
        return subscribedGenre;
    }

    public Movie getAddedMovie() {
        return addedMovie;
    }

    public String getDeletedMovie() {
        return deletedMovie;
    }
}
