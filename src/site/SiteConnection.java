package site;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import input.ActionInput;
import input.Input;
import site.account.Account;
import site.account.AccountFactory;
import site.pages.PageTypes;

import javax.swing.*;

public final class SiteConnection {
    private static SiteConnection instance = null;

    private SiteConnection() {
    }

    /**
     * getter
     * @return site connection
     */
    public static SiteConnection getSiteConnection() {
        if (instance == null) {
            instance = new SiteConnection();
        }

        return instance;
    }

    public void processInput(final Input input, final ArrayNode output){
        SiteStructure site = new SiteStructure();
        loadDataBase(site, input);

        ObjectNode error;
        for (ActionInput action : input.getActions()) {
            if (action.getType().equals("change page")) {
                error = site.changePage(action);
            } else {
                error = site.executeAction(action);
            }

            if (error != null) {
                output.add(error);
            }
        }
    }

    private void loadDataBase(SiteStructure site, Input input) {
        site.getMoviesDataBase().addAll(input.getMovies());

        AccountFactory factory = new AccountFactory();
        System.out.println(input.getUsers().get(0).getCredentials().getAccountType());
        System.out.println(input.getUsers().get(1).getCredentials().getAccountType());
        input.getUsers().forEach((user -> site.getUsers()
                                              .add(factory.getAccount(user.getCredentials()
                                                                          .getAccountType(),
                                                                      user.getCredentials()))));
    }
}
