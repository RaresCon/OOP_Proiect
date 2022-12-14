package site.movies;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Movie {
    private String name;
    private int year;
    private int duration;
    private int numLikes = 0;
    private Set<String> genres = new HashSet<>();
    private Set<String> actors = new HashSet<>();
    private Set<String> bannedCountries = new HashSet<>();
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

    public Set<String> getGenres() {
        return genres;
    }

    public Set<String> getActors() {
        return actors;
    }

    public Set<String> getBannedCountries() {
        return bannedCountries;
    }

    public List<Double> getRatings() {
        return ratings;
    }

    public void setRatings(List<Double> ratings) {
        this.ratings = ratings;
    }

    public void incNumLikes() {
        numLikes += 1;
    }
}
