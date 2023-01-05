package site.actions;

import com.fasterxml.jackson.databind.node.ObjectNode;
import input.ActionInput;
import site.Database;
import site.Utility;
import site.account.Account;
import site.movies.Movie;
import site.notifications.Notification;

import static site.ResponseCodes.ERROR;

public enum AdminActions implements Action {
    ADD_MOVIE {
        @Override
        public ObjectNode executeAction(ActionInput action, Database site) {
            if (site.getMoviesDataBase().contains(site.getMovieFromList(site.getMoviesDataBase(),
                                                  action.getAddedMovie().getName()))) {
                return Utility.response(site, ERROR);
            }

            site.getMoviesDataBase().add(action.getAddedMovie());
            site.notifyObservers(new Notification(action.getAddedMovie().getName(),
                                                  action.getFeature().toUpperCase()));

            return null;
        }
    },

    DELETE_MOVIE {
        @Override
        public ObjectNode executeAction(ActionInput action, Database site) {
            Movie foundMovie = site.getMovieFromList(site.getMoviesDataBase(),
                                                     action.getDeletedMovie());
            if (foundMovie == null) {
                return Utility.response(site, ERROR);
            }

            site.notifyObservers(new Notification(action.getDeletedMovie(),
                                                  action.getFeature().toUpperCase()));
            for (Account user : site.getUsersDataBase()) {
                if (user.getPurchasedMovies().remove(foundMovie)) {
                    user.refundCost();
                    user.getWatchedMovies().remove(foundMovie);
                    user.getLikedMovies().remove(foundMovie);
                    user.getRatedMovies().remove(foundMovie);
                }
            }

            site.getMoviesDataBase().remove(foundMovie);

            return null;
        }
    }
}
