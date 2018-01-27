package model.events;


import eventmanagement.Event;

import java.time.Duration;

public class DurationRemainingUpdateEvent implements Event{


    private final Duration durationRemaining;

    public DurationRemainingUpdateEvent(Duration durationRemaining) {
        this.durationRemaining = durationRemaining;
    }

    public Duration getDuration() {
        return durationRemaining;
    }
}
