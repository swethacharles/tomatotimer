package model.events;

import eventmanagement.Event;

import java.time.Duration;

public class TimerStartingEvent implements Event{
    private Duration startDuration;

    public TimerStartingEvent(Duration startDuration) {
        this.startDuration = startDuration;
    }

    public Duration getStartDuration() {
        return startDuration;
    }
}
