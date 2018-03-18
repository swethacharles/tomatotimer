package model.events;

import eventmanagement.Event;
import model.timertype.TimerType;

public class TimerTypeChangeEvent implements Event{
    private final TimerType timerType;

    public TimerTypeChangeEvent(TimerType timerType) {
        this.timerType = timerType;
    }

    public TimerType getTimerType() {
        return timerType;
    }
}
