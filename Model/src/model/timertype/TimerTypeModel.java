package model.timertype;

import model.timertype.TimerType;

import java.time.Duration;
import java.util.Map;

public class TimerTypeModel {
    private final Map<TimerType, Duration> typeDurationMap;
    private TimerType type;

    public TimerTypeModel() {
        type = TimerType.TOMATO;
        typeDurationMap = new TimerTypeDefaults().getDefaultMap();
    }

    public Duration getStartingTimerDuration() {
        return typeDurationMap.get(type);
    }
}
