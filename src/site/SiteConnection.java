package site;

import com.fasterxml.jackson.databind.node.ArrayNode;
import input.Input;
import site.account.Account;
import site.pages.Page;
import site.pages.PageFactory;

import java.util.ArrayList;
import java.util.Arrays;

import static site.pages.PageTypes.DETAILSPAGE;
import static site.pages.PageTypes.HOMEPAGE_AUTH;
import static site.pages.PageTypes.HOMEPAGE_NOAUTH;
import static site.pages.PageTypes.LOGINPAGE;
import static site.pages.PageTypes.MOVIESPAGE;
import static site.pages.PageTypes.REGISTERPAGE;
import static site.pages.PageTypes.UPGRADESPAGE;

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


    }
}
