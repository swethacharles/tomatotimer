package viewmodel.events;

import eventmanagement.Event;

public class TomatoResizeEvent implements Event{
    private final double resizeFraction;

    public TomatoResizeEvent(double resizeFraction) {
        this.resizeFraction = resizeFraction;
    }

    public double getResizeFraction() {
        return resizeFraction;
    }
}
