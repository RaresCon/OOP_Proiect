package site;

import com.fasterxml.jackson.databind.node.ArrayNode;
import input.ActionInput;
import input.Input;

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


        for (ActionInput action : input.getActions()) {
            if (action.getType().equals("change page")) {
                output.add(site.changePage(action));
            } else {
                output.add(site.executeAction(action));
            }
        }
    }
}
