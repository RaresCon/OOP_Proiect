package site;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import input.ActionInput;
import input.Input;
import jdk.jshell.spi.ExecutionControl;

public final class SiteConnection {
    private static SiteConnection instance = null;

    /**
     * default private constructor for Singleton
     */
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

    /**
     * method to process input from the user
     * @param input the input given by the user
     * @param output the output for the user
     */
    public void processInput(final Input input, final ArrayNode output)
           throws ExecutionControl.InternalException {
        Database site = new Database();
        site.loadDataBase(input);

        ObjectNode response;
        for (ActionInput action : input.getActions()) {
            switch (action.getType()) {
                case "change page" -> response = site.changePage(action);
                case "on page" -> response = site.executeAction(action);
                case "back" -> response = site.backPage();
                case "database" -> response = site.modifyDatabase(action);
                default ->
                throw new ExecutionControl.InternalException("Command not recognized");
            }

            if (response != null) {
                output.add(response);
            }
        }

        ObjectNode lastResponse = site.getRecommendation();
        if (lastResponse != null) {
            output.add(lastResponse);
        }
    }
}
