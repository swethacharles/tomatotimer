package eventmanagement;

import java.util.*;


public class ObservableManager implements Observable{

    private Map<Class<? extends Event>, Set<Observer<? extends Event>>> allObservers = new HashMap<>();


    @Override
    public <T extends Event> void registerFor(Class<T> eventClass, Observer<T> observer) {
        if(allObservers.containsKey(eventClass)){
            allObservers.get(eventClass).add(observer);
        } else {
            Set<Observer<? extends Event>> observerSet = new HashSet<>();
            observerSet.add(observer);
            allObservers.put(eventClass, observerSet);
        }
    }

    @Override
    public <T extends Event> void deregisterFor(Class<T> eventClass, Observer<T> observer) {
        if(allObservers.containsKey(eventClass)){
            Set<Observer<? extends Event>> observersForEvent = allObservers.get(eventClass);
            observersForEvent.remove(observer);
            if(observersForEvent.isEmpty()){
                allObservers.remove(eventClass);
            }
        }
    }

    public <T extends Event> void notify(T event){
        Optional<Set<Observer<? extends Event>>> observers = Optional.ofNullable(allObservers.get(event.getClass()));
        Set<Observer<? extends Event>> observersForEvent = observers.orElse(new HashSet<>());
            for (Observer<? extends Event> observer : observersForEvent) {
                Observer<T> castObserver = (Observer<T>) observer;
                castObserver.handleDurationRemainingUpdate(event);
            }

    }
}
