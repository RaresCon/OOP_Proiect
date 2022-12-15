package site.movies;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    private String name;
    private int year;
    private int duration;
    private int numLikes = 0;
    private List<String> genres = new ArrayList<>();
    private List<String> actors = new ArrayList<>();
    private List<String> countriesBanned = new ArrayList<>();
    private List<Double> ratings = new ArrayList<>();

    public Movie() {
    }

    public Double getRating() {
        Double overall = 0.0;
        for (Double rating : ratings) {
            overall += rating;
        }

        return ratings.size() > 0 ? overall / ratings.size() : 0.0;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public int getDuration() {
        return duration;
    }

    public int getNumLikes() {
        return numLikes;
    }

    public List<String> getGenres() {
        return genres;
    }

    public List<String> getActors() {
        return actors;
    }

    public List<String> getCountriesBanned() {
        return countriesBanned;
    }

    public List<Double> getRatings() {
        return ratings;
    }

    public void incNumLikes() {
        numLikes += 1;
    }
}
