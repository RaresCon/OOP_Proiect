package site.pages;

import com.fasterxml.jackson.databind.node.ObjectNode;
import input.ActionInput;
import site.Config;
import site.Database;
import site.Utility;
import site.account.Account;
import site.account.AccountFactory;
import site.movies.Filter;
import site.movies.Movie;

import java.util.List;

import static site.ResponseCodes.*;

public enum Actions {
    LOGIN {
        @Override
        public ObjectNode executeAction(final ActionInput action, final Database site) {
            for (Account user : site.getUsersDataBase()) {
                if (user.checkCreds(action.getCredentials())) {
                    site.setCurrentUser(user);
                    site.setCurrentPage(site.getPageStructure().get(PageTypes.HOMEPAGE_AUTH));
                    return Utility.response(site, OK);
                }
            }
            site.setCurrentPage(site.getPageStructure().get(PageTypes.HOMEPAGE_NOAUTH));

            return Utility.response(site, ERROR);
        }
    },

    REGISTER {
        @Override
        public ObjectNode executeAction(final ActionInput action, final Database site) {
            if (action.getCredentials().getName().isEmpty()
                || action.getCredentials().getPassword().isEmpty()
                || action.getCredentials().getCountry().isEmpty()) {
                return Utility.response(site, ERROR);
            }

            for (Account user : site.getUsersDataBase()) {
                if (user.getCreds().getName().equals(action.getCredentials().getName())) {
                    site.setCurrentPage(site.getPageStructure().get(PageTypes.HOMEPAGE_NOAUTH));
                    return Utility.response(site, ERROR);
                }
            }

            AccountFactory factory = AccountFactory.getInstance();
            Account newUser = factory.getAccount(action.getCredentials());

            site.getUsersDataBase().add(newUser);
            site.setCurrentUser(newUser);

            site.setCurrentPage(site.getPageStructure().get(PageTypes.HOMEPAGE_AUTH));
            return Utility.response(site, OK);
        }
    },

    SEARCH {
        @Override
        public ObjectNode executeAction(final ActionInput action, final Database site) {
            List<Movie> currentMovies = site.getCurrentMoviesList();
            currentMovies.removeIf(movie -> !movie.getName().startsWith(action.getStartsWith()));

            return Utility.response(site, OK);
        }
    },

    FILTER {
        @Override
        public ObjectNode executeAction(final ActionInput action, final Database site) {
            site.getCurrentPage().setState(null, site);

            if (action.getFilters().getSort() != null) {
                Filter.sortMovies(site.getCurrentMoviesList(),
                                  action.getFilters().getSort());
            }

            if (action.getFilters().getContains() != null) {
                Filter.filterMovies(site.getCurrentMoviesList(),
                                    action.getFilters().getContains());
            }

            return Utility.response(site, OK);
        }
    },

    BUY_TOKENS {
        @Override
        public ObjectNode executeAction(final ActionInput action, final Database site) {
            if (action.getCount() <= site.getCurrentUser().getCreds().getBalance()) {
                site.getCurrentUser().getCreds().subBalance(action.getCount());
                site.getCurrentUser().addTokens(action.getCount());

                return null;
            }

            return Utility.response(site, ERROR);
        }
    },

    BUY_PREMIUM {
        @Override
        public ObjectNode executeAction(final ActionInput action, final Database site) {
            if (Config.PREMIUM_PRICE <= site.getCurrentUser().getTokensCount()) {
                site.getCurrentUser().subTokens(Config.PREMIUM_PRICE);
                site.getCurrentUser().getCreds().setAccountType("premium");

                return null;
            }

            return Utility.response(site, OK);
        }
    },

    BUY_MOVIE {
        @Override
        public ObjectNode executeAction(final ActionInput action, final Database site) {
            if (site.getCurrentUser().getNumFreePremiumMovies() > 0
                && site.getCurrentUser().getCreds().getAccountType().equals("premium")) {
                site.getCurrentUser().subNumFreePremiumMovies();
                site.getCurrentUser().getPurchasedMovies()
                                     .addAll(site.getCurrentMoviesList());

                return Utility.response(site, OK);
            } else if (Config.MOVIE_PRICE <= site.getCurrentUser().getTokensCount()) {
                site.getCurrentUser().getPurchasedMovies()
                                     .addAll(site.getCurrentMoviesList());
                site.getCurrentUser().subTokens(Config.MOVIE_PRICE);

                return Utility.response(site, OK);
            }

            return Utility.response(site, ERROR);
        }
    },

    WATCH_MOVIE {
        @Override
        public ObjectNode executeAction(final ActionInput action, final Database site) {
            Account currentUser = site.getCurrentUser();
            List<Movie> userWatchedMovies = currentUser.getWatchedMovies();

            if (site.getMovieFromList(userWatchedMovies, site.getCurrentMovie()) != null) {
                return Utility.response(site, OK);
            }

            if (site.getCurrentUser().getPurchasedMovies()
                                     .containsAll(site.getCurrentMoviesList())) {
                site.getCurrentUser().getWatchedMovies()
                        .addAll(site.getCurrentMoviesList());
                return Utility.response(site, OK);
            }

            return Utility.response(site, ERROR);
        }
    },

    LIKE_MOVIE {
        @Override
        public ObjectNode executeAction(final ActionInput action, final Database site) {
            Account currentUser = site.getCurrentUser();
            List<Movie> userLikedMovies = currentUser.getLikedMovies();

            if (site.getMovieFromList(userLikedMovies, site.getCurrentMovie()) != null) {
                return Utility.response(site, ERROR);
            }

            for (Movie movie : site.getCurrentMoviesList()) {
                if (currentUser.getWatchedMovies().contains(movie)) {
                    currentUser.getLikedMovies().add(movie);
                    movie.incNumLikes();
                    return Utility.response(site, OK);
                }
            }
            return Utility.response(site, ERROR);
        }
    },

    RATE_MOVIE {
        @Override
        public ObjectNode executeAction(final ActionInput action, final Database site) {
            Account currentUser = site.getCurrentUser();
            List<Movie> userRatesMovies = currentUser.getRatedMovies();

            if (action.getRate() > Config.MAX_RATING
                || action.getRate() < 0
                || site.getMovieFromList(userRatesMovies, site.getCurrentMovie()) != null) {
                return Utility.response(site, ERROR);
            }

            for (Movie movie : site.getCurrentMoviesList()) {
                if (currentUser.getWatchedMovies().contains(movie)) {
                    currentUser.getRatedMovies().add(movie);
                    movie.getRatings().add((double) action.getRate());

                    return Utility.response(site, OK);
                }
            }
            return Utility.response(site, ERROR);
        }
    };

    /**
     * abstract function that implements the effect of each action that a user can execute
     * @param action the action info to be checked and applied to the site database
     * @return an ObjectNode representing the response from the database which can be a successful
     * output or an error output
     */
    public abstract ObjectNode executeAction(ActionInput action, Database site);
}
