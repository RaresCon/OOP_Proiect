package site.actions;

import com.fasterxml.jackson.databind.node.ObjectNode;
import input.ActionInput;
import site.Database;

public interface Action {
    /**
     * abstract method that implements the effect of each action that a user can execute
     * @param action the action info to be checked and applied to the site database
     * @return an ObjectNode representing the response from the database which can be a successful
     * output or an error output
     */
    ObjectNode executeAction(ActionInput action, Database site);
}
