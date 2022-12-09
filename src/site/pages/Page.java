package site.pages;

import java.util.ArrayList;
import java.util.List;

public abstract class Page implements ActionsOnPage {
    protected List<Page> accessiblePages = new ArrayList<>();
    protected List<ActionsOnPage> allowedActions = new ArrayList<>();
}
