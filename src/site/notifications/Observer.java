package site.notifications;

import site.Database;

public abstract class Observer {
    protected Database site;

    /**
     * abstract method that any observer class needs to implement
     * @param notification any object that may affect the observable object at update
     *        it is optional and may carry any type of information
     */
    public abstract void update(Object notification);
}
