package eventmanagement;

import java.util.LinkedHashSet;
import java.util.Set;


public class ObservableManager implements Observable{

    private Set<Observer> observers = new LinkedHashSet<>();

    @Override
    public void register(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void deregister(Observer observer) {
        observers.remove(observer);
    }

    public void notifyEvent(Event event){
        for(Observer observer : observers){
            observer.notifyEvent(event);
        }
    }
}
