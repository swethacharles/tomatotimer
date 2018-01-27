package eventmanagement;


/**
 * Created by swetha on 27/11/17.
 */
public interface Observable {

    <T extends Event> void registerFor(Class<T> eventClass, Observer<T> observer);

    <T extends Event> void deregisterFor(Class<T> eventClass, Observer<T> observer);
}
