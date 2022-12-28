package site.actions;

import com.fasterxml.jackson.databind.node.ObjectNode;
import input.ActionInput;
import site.Database;
import site.Utility;
import site.account.Account;
import site.movies.Movie;

import static site.ResponseCodes.ERROR;

public enum AdminActions implements Action {
    ADD_MOVIE {
        @Override
        public ObjectNode executeAction(ActionInput action, Database site) {
            if (site.getMoviesDataBase().contains(action.getAddedMovie())) {
                return Utility.response(site, ERROR);
            }

            site.getMoviesDataBase().add(action.getAddedMovie());

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
