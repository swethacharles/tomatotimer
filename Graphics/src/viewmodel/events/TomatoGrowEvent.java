package viewmodel.events;

import eventmanagement.Event;

public class TomatoGrowEvent implements Event{
    private final double growthFraction;

    public TomatoGrowEvent(double growthFraction) {
        this.growthFraction = growthFraction;
    }

    public double getGrowthFraction() {
        return growthFraction;
    }
}
