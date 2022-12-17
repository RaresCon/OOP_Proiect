package site.pages;

import com.fasterxml.jackson.databind.node.ObjectNode;
import input.ActionInput;
import site.Database;

public interface PageFunctions {
    /**
     * function to link a page to other pages
     */
    void linkToPages();

    /**
     * function to move on another page, it is almost the same for all pages
     * so this function has a standard in Page abstract class
     * @param action the action that gives the next page
     * @param site the site database
     * @return true if the change was possible, false otherwise
     */
    boolean setNextPage(ActionInput action, Database site);

    /**
     * function to set the state of the site database
     * @param input action that sets the state
     * @param site the site database
     * @return output
     */
    ObjectNode setState(ActionInput input, Database site);
}
