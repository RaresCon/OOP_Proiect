package site.notifications;

import java.util.ArrayList;
import java.util.List;

public abstract class Observable {
    protected List<Observer> observers = new ArrayList<>();

    /**
     * method to add an observer to an observable object
     * @param observer the observer that will be added to the list of observers
     */
    public void addObserver(final Observer observer) {
        observers.add(observer);
    }

    /**
     * method to notify all observers of this observable object
     * @param notification any object that may affect the observable object at update
     *        it is optional and may carry any type of information
     */
    public void notifyObservers(final Object notification) {
        for (Observer observer : observers) {
            observer.update(notification);
        }
    }
}
