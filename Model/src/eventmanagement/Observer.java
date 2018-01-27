package eventmanagement;

public interface Observer<T> {

    void handleDurationRemainingUpdate(T event);
}
