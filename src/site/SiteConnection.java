package site;

import com.fasterxml.jackson.databind.node.ArrayNode;

public final class SiteConnection {
    private static SiteConnection instance = null;
    private Account currentUser = null;

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

    public ArrayNode processInput(){
        return null;
    }
}
