package site;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import input.ActionInput;
import input.Input;

public final class Session {
    private static Session instance = null;

    /**
     * default private constructor for Singleton
     */
    private Session() {
    }

    /**
     * getter
     * @return site connection
     */
    public static Session getSiteConnection() {
        if (instance == null) {
            instance = new Session();
        }

        return instance;
    }

    /**
     * function to process input from the user
     * @param input the input given by the user
     * @param output the output for the user
     */
    public void processInput(final Input input, final ArrayNode output) {
        Database site = new Database();
        site.loadDataBase(input);

        ObjectNode response = null;
        for (ActionInput action : input.getActions()) {
            if (action.getType().equals("change page")) {
                response = site.changePage(action);
            } else if (action.getType().equals("on page")) {
                response = site.executeAction(action);
            }

            if (response != null) {
                output.add(response);
            }
        }
    }
}
