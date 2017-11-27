package eventmanagement;


/**
 * Created by swetha on 27/11/17.
 */
public interface Observable {

    void register(Observer observer);

    void deregister(Observer observer);
}
