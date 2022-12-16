package site.pages;

import com.fasterxml.jackson.databind.node.ObjectNode;
import input.ActionInput;
import site.SiteStructure;

public class LogoutPage extends Page {
    public LogoutPage(PageTypes pageType) {
        super(pageType);
    }

    @Override
    public void linkToPages() {
        accessiblePages.put("homepage", PageTypes.HOMEPAGE_NOAUTH);
    }

    @Override
    public ObjectNode setState(ActionInput action, SiteStructure site) {
        site.setCurrentPage(site.getPageStructure().get(PageTypes.HOMEPAGE_NOAUTH));
        site.setCurrentUser(null);
        site.getCurrentMoviesList().clear();

        return null;
    }
}
