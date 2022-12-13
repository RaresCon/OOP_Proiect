package site.pages;

import input.ActionInput;
import site.SiteStructure;
import site.account.Account;
import site.account.PremiumAccount;
import site.account.StandardAccount;
import site.movies.Movie;

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
                || action.getCredentials().getPasswordHash().isEmpty()
                || action.getCredentials().getCountry().isEmpty())
                return false;

            for (Account user : site.getUsers()) {
                if (user.getCreds().getName().equals(action.getCredentials().getName())) {
                    site.setCurrentPage(site.getPageStructure().get(PageTypes.HOMEPAGE_NOAUTH));
                    return false;
                }
            }

            Account newUser;
            if (action.getCredentials().getAccountType().equals("standard")) {
                newUser = new StandardAccount(action.getCredentials());
            } else {
                newUser = new PremiumAccount(action.getCredentials());
            }
            site.getUsers().add(newUser);

            site.setCurrentPage(site.getPageStructure().get(PageTypes.HOMEPAGE_AUTH));
            return true;
        }
    },

    SEARCH {
        @Override
        public boolean executeAction(ActionInput action, SiteStructure site) {
            List<Movie> currentMovies = site.getCurrentPage().getCurrentMovies();
            currentMovies.removeIf(movie -> !movie.getName().startsWith(action.getStartsWith()));

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

                return true;
            }

            return false;
        }
    },

    BUY_MOVIE {
        @Override
        public boolean executeAction(ActionInput action, SiteStructure site) {
            if (2 <= site.getCurrentUser().getTokensCount()
                || site.getCurrentUser().getNumFreePremiumMovies() > 0) {
                site.getCurrentUser().subTokens(2);
                //site.getCurrentPage().g
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
