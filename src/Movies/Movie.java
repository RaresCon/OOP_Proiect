package Movies;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    private String name;
    private int year;
    private int duration;
    private List<String> genres = new ArrayList<>();
    private List<String> actors = new ArrayList<>();
    private List<String> bannedCountries = new ArrayList<>();
    private List<Integer> ratings = new ArrayList<>();

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public int getDuration() {
        return duration;
    }

    public List<String> getGenres() {
        return genres;
    }

    public List<String> getActors() {
        return actors;
    }

    public List<String> getBannedCountries() {
        return bannedCountries;
    }

    public List<Integer> getRatings() {
        return ratings;
    }

    public void setRatings(List<Integer> ratings) {
        this.ratings = ratings;
    }
}
