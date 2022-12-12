package site;

import com.fasterxml.jackson.databind.node.ArrayNode;
import site.account.Account;
import site.pages.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public final class SiteStructure {
    private Account currentUser = null;
    private Page currentPage;
    private final HashMap<PageTypes, Page> pageStructure = new HashMap<>();

    public SiteStructure() {
        PageFactory factory = new PageFactory();
        pageStructure.put(PageTypes.HOMEPAGE_NOAUTH, factory.getPage(PageTypes.HOMEPAGE_NOAUTH));
        pageStructure.put(PageTypes.HOMEPAGE_AUTH, factory.getPage(PageTypes.HOMEPAGE_AUTH));
        pageStructure.put(PageTypes.REGISTERPAGE, factory.getPage(PageTypes.REGISTERPAGE));
        pageStructure.put(PageTypes.LOGINPAGE, factory.getPage(PageTypes.LOGINPAGE));
        pageStructure.put(PageTypes.UPGRADESPAGE, factory.getPage(PageTypes.UPGRADESPAGE));
        pageStructure.put(PageTypes.MOVIESPAGE, factory.getPage(PageTypes.MOVIESPAGE));
        pageStructure.put(PageTypes.DETAILSPAGE, factory.getPage(PageTypes.DETAILSPAGE));
        pageStructure.put(PageTypes.LOGOUTPAGE, factory.getPage(PageTypes.LOGOUTPAGE));

        linkPagesNoAuth();
    }

    public void changePage(String nextPage) {
        PageTypes nextPageType = currentPage.nextPage(nextPage);

        if (nextPageType != null) {
            currentPage = pageStructure.get(nextPageType);
        }
    }

    private void linkPagesNoAuth() {
        pageStructure.get(PageTypes.HOMEPAGE_NOAUTH).linkToPages();
        currentPage = pageStructure.get(PageTypes.HOMEPAGE_NOAUTH);
    }

    public void linkPagesAuth() {
        for (Map.Entry<PageTypes, Page> page : pageStructure.entrySet()) {
            page.getValue().linkToPages();
        }
    }

    public HashMap<PageTypes, Page> getPageStructure() {
        return pageStructure;
    }
}
