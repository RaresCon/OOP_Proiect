package site.notifications;

import site.Database;

public abstract class Observer {
    protected Database site;
    public abstract void update(Notification notification);
}
