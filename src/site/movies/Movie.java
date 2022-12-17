package site.movies;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    private String name;
    private int year;
    private int duration;
    private int numLikes = 0;
    private final List<String> genres = new ArrayList<>();
    private final List<String> actors = new ArrayList<>();
    private final List<String> countriesBanned = new ArrayList<>();
    private final List<Double> ratings = new ArrayList<>();

    /**
     * default constructor
     */
    public Movie() {
    }

    /**
     * function to get the rating of a movie
     * @return
     */
    public Double getRating() {
        Double overall = ratings.stream().reduce(0.0, Double::sum);

        return ratings.size() > 0 ? overall / ratings.size() : 0.0;
    }

    /**
     * function to increment the number of likes
     */
    public void incNumLikes() {
        numLikes += 1;
    }

    /**
     * function to decrement the number of likes
     */
    public void subNumLikes() {
        numLikes -= 1;
    }

    /**
     * getter
     * @return the name of the movie
     */
    public String getName() {
        return name;
    }

    /**
     * getter
     * @return the year of release
     */
    public int getYear() {
        return year;
    }

    /**
     * getter
     * @return the length of the movie
     */
    public int getDuration() {
        return duration;
    }

    /**
     * getter
     * @return the number of likes
     */
    public int getNumLikes() {
        return numLikes;
    }

    /**
     * getter
     * @return the genres of the movie
     */
    public List<String> getGenres() {
        return genres;
    }

    /**
     * getter
     * @return the actors that play in the movie
     */
    public List<String> getActors() {
        return actors;
    }

    /**
     * getter
     * @return the countries in which the movie is banned
     */
    public List<String> getCountriesBanned() {
        return countriesBanned;
    }

    /**
     * getter
     * @return all ratings for the movie
     */
    public List<Double> getRatings() {
        return ratings;
    }
}
