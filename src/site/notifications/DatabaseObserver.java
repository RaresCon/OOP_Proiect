package site.notifications;

import site.Database;
import site.account.Account;
import site.movies.Movie;

public final class DatabaseObserver extends Observer {
    public DatabaseObserver(final Database site) {
        this.site = site;
    }

    /**
     * update method of the Observer class for this implementation
     * @param notification the notification that will be added to the users
     *        present in the database at update time
     */
    public void update(final Object notification) {
        Movie foundMovie = site.getMovieFromList(site.getMoviesDataBase(),
                ((Notification) notification).movieName());

        switch (((Notification) notification).message()) {
            case "ADD" -> appendAddNotifications(foundMovie, (Notification) notification);
            case "DELETE" -> appendDeleteNotifications(foundMovie, (Notification) notification);
            default ->
            throw new IllegalArgumentException("This action for database is not implemented");
        }
    }

    /**
     * method that appends ADD notifications to all users' notifications lists
     * @param addedMovie the movie which was added to the database
     * @param notification the notification that will be appended
     */
    private void appendAddNotifications(final Movie addedMovie,
                                        final Notification notification) {
        for (Account user : site.getUsersDataBase()) {
            if (!addedMovie.getCountriesBanned().contains(user.getCreds().getCountry())
                && user.getSubbedGenres().stream().anyMatch(addedMovie.getGenres()::contains)) {
                user.getNotifications().add(notification);
            }
        }
    }

    /**
     * method that appends DELETE notifications to all users' notifications lists
     * @param deletedMovie the movie which was removed from the database
     * @param notification the notification that will be appended
     */
    private void appendDeleteNotifications(final Movie deletedMovie,
                                           final Notification notification) {
        for (Account user : site.getUsersDataBase()) {
            if (user.getPurchasedMovies().contains(deletedMovie)) {
                user.getNotifications().add(notification);
            }
        }
    }
}
