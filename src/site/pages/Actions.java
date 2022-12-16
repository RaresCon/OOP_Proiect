package site.pages;

import input.ActionInput;
import site.SiteStructure;
import site.account.Account;
import site.account.AccountFactory;
import site.account.PremiumAccount;
import site.account.StandardAccount;
import site.movies.Filter;
import site.movies.Movie;

import java.util.ArrayList;
import java.util.List;

public enum Actions {
    LOGIN {
        @Override
        public boolean executeAction(ActionInput action, SiteStructure site) {
            for (Account user : site.getUsers()) {
                if (user.checkCreds(action.getCredentials())) {
                    site.setCurrentUser(user);
                    site.setCurrentPage(site.getPageStructure().get(PageTypes.HOMEPAGE_AUTH));
                    return true;
                }
            }
            site.setCurrentPage(site.getPageStructure().get(PageTypes.HOMEPAGE_NOAUTH));

            return false;
        }
    },

    REGISTER {
        @Override
        public boolean executeAction(ActionInput action, SiteStructure site) {
            if (action.getCredentials().getName().isEmpty()
                || action.getCredentials().getPassword().isEmpty()
                || action.getCredentials().getCountry().isEmpty())
                return false;

            for (Account user : site.getUsers()) {
                if (user.getCreds().getName().equals(action.getCredentials().getName())) {
                    site.setCurrentPage(site.getPageStructure().get(PageTypes.HOMEPAGE_NOAUTH));
                    return false;
                }
            }

            AccountFactory factory = new AccountFactory();
            Account newUser = factory.getAccount(action.getCredentials().getAccountType(),
                                                 action.getCredentials());

            site.getUsers().add(newUser);
            site.setCurrentUser(newUser);

            site.setCurrentPage(site.getPageStructure().get(PageTypes.HOMEPAGE_AUTH));
            return true;
        }
    },

    SEARCH {
        @Override
        public boolean executeAction(ActionInput action, SiteStructure site) {
            List<Movie> currentMovies = site.getCurrentMoviesList();
            currentMovies.removeIf(movie -> !movie.getName().startsWith(action.getStartsWith()));

            return true;
        }
    },

    FILTER {
        @Override
        public boolean executeAction(ActionInput action, SiteStructure site) {
            site.getCurrentPage().setState(null, site);

            if (action.getFilters().getSort() != null)
                Filter.sortMovies(site.getCurrentMoviesList(),
                                  action.getFilters().getSort());

            if (action.getFilters().getContains() != null)
                Filter.filterMovies(site.getCurrentMoviesList(),
                                    action.getFilters().getContains());
            return true;
        }
    },

    BUY_TOKENS {
        @Override
        public boolean executeAction(ActionInput action, SiteStructure site) {
            if (action.getCount() <= site.getCurrentUser().getCreds().getBalance()) {
                site.getCurrentUser().getCreds().subBalance(action.getCount());
                site.getCurrentUser().addTokens(action.getCount());

                return true;
            }

            return false;
        }
    },

    BUY_PREMIUM {
        @Override
        public boolean executeAction(ActionInput action, SiteStructure site) {
            if (10 <= site.getCurrentUser().getTokensCount()) {
                site.getCurrentUser().subTokens(10);
                site.getCurrentUser().getCreds().setAccountType("premium");

                return true;
            }

            return false;
        }
    },

    BUY_MOVIE {
        @Override
        public boolean executeAction(ActionInput action, SiteStructure site) {
            if (site.getCurrentUser().getNumFreePremiumMovies() > 0
                && site.getCurrentUser().getCreds().getAccountType().equals("premium")) {
                site.getCurrentUser().subNumFreePremiumMovies();
                site.getCurrentUser().getPurchasedMovies()
                                     .addAll(site.getCurrentMoviesList());

                return true;
            } else if (2 <= site.getCurrentUser().getTokensCount()) {
                site.getCurrentUser().getPurchasedMovies()
                                     .addAll(site.getCurrentMoviesList());
                site.getCurrentUser().subTokens(2);

                return true;
            }

            return false;
        }
    },

    WATCH_MOVIE {
        @Override
        public boolean executeAction(ActionInput action, SiteStructure site) {
            if (site.getCurrentUser().getPurchasedMovies()
                                     .containsAll(site.getCurrentMoviesList())) {
                site.getCurrentUser().getWatchedMovies()
                        .addAll(site.getCurrentMoviesList());
                return true;
            }

            return false;
        }
    },

    LIKE_MOVIE {
        @Override
        public boolean executeAction(ActionInput action, SiteStructure site) {
            Account currentUser = site.getCurrentUser();
            for (Movie movie : site.getCurrentMoviesList()) {
                if (currentUser.getWatchedMovies().contains(movie)) {
                    currentUser.getLikedMovies().add(movie);
                    movie.incNumLikes();
                    return true;
                }
            }
            return false;
        }
    },

    RATE_MOVIE {
        @Override
        public boolean executeAction(ActionInput action, SiteStructure site) {
            Account currentUser = site.getCurrentUser();

            if (action.getRate() > 5 || action.getRate() < 0) {
                return false;
            }

            for (Movie movie : site.getCurrentMoviesList()) {
                if (currentUser.getWatchedMovies().contains(movie)) {
                    currentUser.getRatedMovies().add(movie);
                    movie.getRatings().add((double) action.getRate());

                    return true;
                }
            }
            return false;
        }
    };

    /**
     *
     * @param
     * @return
     */
    public abstract boolean executeAction(ActionInput action, SiteStructure site);
}
