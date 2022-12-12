package site.pages;

import java.util.HashMap;


public abstract class Page {
    protected PageTypes pageType;
    protected HashMap<PageTypes, Page> accessiblePages = new HashMap<>();

    public Page(PageTypes pageType) {
        this.pageType = pageType;
    }

    public Page changePage(PageTypes nextPage) {

    }

    public abstract void linkPages();
}
