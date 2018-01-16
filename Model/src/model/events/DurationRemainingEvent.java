package model.events;


import eventmanagement.Event;

import java.time.Duration;

public class DurationRemainingEvent implements Event{


    private final Duration durationRemaining;

    public DurationRemainingEvent(Duration durationRemaining) {
        this.durationRemaining = durationRemaining;
    }

    public Duration getDuration() {
        return durationRemaining;
    }
}
