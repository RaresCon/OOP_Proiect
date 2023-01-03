package site.notifications;

import site.Database;
import site.account.Account;
import site.movies.Movie;

public class DatabaseObserver extends Observer {
    public DatabaseObserver(Database site) {
        this.site = site;
    }

    @Override
    public void update(Notification notification) {
        Movie foundMovie = site.getMovieFromList(site.getMoviesDataBase(), notification.movieName());

        switch (notification.message()) {
            case "ADD" -> add_notifications(foundMovie, notification);
            case "DELETE" -> delete_notifications(foundMovie, notification);
            default -> System.exit(1);
        }
    }

    private void add_notifications(Movie foundMovie, Notification notification) {
        for (Account user : site.getUsersDataBase()) {
            if (!foundMovie.getCountriesBanned().contains(user.getCreds().getCountry())
                && user.getSubbedGenres().stream().anyMatch(foundMovie.getGenres()::contains)) {
                user.getNotifications().add(notification);
            }
        }
    }

    private void delete_notifications(Movie foundMovie, Notification notification) {
        for (Account user : site.getUsersDataBase()) {
            if (user.getPurchasedMovies().contains(foundMovie)) {
                user.getNotifications().add(notification);
            }
        }
    }
}
