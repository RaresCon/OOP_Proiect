package site;

import Account.Account;
import com.fasterxml.jackson.databind.node.ArrayNode;
import pages.Homepage;
import pages.Page;

public final class SiteConnection {
    private static SiteConnection instance = null;
    private Account currentUser = null;
    private Page currentPage = new Homepage();

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
