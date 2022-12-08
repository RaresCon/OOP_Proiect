package pages;

import java.util.ArrayList;
import java.util.List;

public abstract class Page implements ActionsOnPage {
    protected List<Page> accessPages;
    protected List<ActionsOnPage> allowedActions;
}
