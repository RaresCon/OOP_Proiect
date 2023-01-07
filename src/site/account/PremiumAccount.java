package site.account;

import site.Config;
import site.movies.Movie;
import site.notifications.Notification;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

public final class PremiumAccount extends Account {
    /**
     * constructor
     * @param creds the credentials used for the new user
     */
    public PremiumAccount(final Credentials creds) {
        super(creds);
        numFreePremiumMovies = Config.STARTING_NUM_FREE_MOVIES;
    }

    public PremiumAccount(final Account user) {
        super(user.creds);
        tokensCount = user.tokensCount;
        numFreePremiumMovies = user.numFreePremiumMovies;
        purchasedMovies.addAll(user.purchasedMovies);
        watchedMovies.addAll(user.getWatchedMovies());
        likedMovies.addAll(user.getLikedMovies());
        ratedMovies.addAll(user.ratedMovies);
        ratings.putAll(user.ratings);
        notifications.addAll(user.notifications);
        subbedGenres.addAll(user.subbedGenres);
    }

    @Override
    public void refundCost() {
        numFreePremiumMovies += 1;
    }

    @Override
    public boolean recommendMovie(final List<Movie> availableMovies) {
        availableMovies.sort((movie1, movie2) -> movie2.getNumLikes() - movie1.getNumLikes());
        AtomicBoolean foundRecommendation = new AtomicBoolean(false);

        /* firstly, it sorts the entries in the hashmap, then it takes every
         * entry and filters the available movies by that entry's key (genre)
         * after filtering the movies, it iterates through them and checks if
         * the current movie wasn't already watched and if there is no recommendation yet
         * if a recommendation was found, it breaks at first from the forEach method
         * of the filtered movies stream and then from the filtered entrySet stream
         */
        likedGenres.entrySet().stream().sorted((entry1, entry2) -> {
            if (Objects.equals(entry1.getValue(), entry2.getValue())) {
                return entry1.getKey().compareTo(entry2.getKey());
            }

            return entry2.getValue().compareTo(entry1.getValue());
        }).takeWhile(junk -> !foundRecommendation.get())
          .forEach(entry -> availableMovies.stream().filter(movie ->
                   movie.getGenres().contains(entry.getKey()))
              .takeWhile(junk -> !foundRecommendation.get())
              .forEach(movie -> {
                    if (!watchedMovies.contains(movie)) {
                        notifications.add(new Notification(movie.getName(), "Recommendation"));
                        foundRecommendation.set(true);
                    }
          }
        ));

        if (!foundRecommendation.get()) {
            notifications.add(new Notification("No recommendation", "Recommendation"));
        }

        return true;
    }
}
