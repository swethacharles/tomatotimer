package model.timertype;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class TimerTypeDefaults {

    private final Map<TimerType, Duration> defaultMap;

    public TimerTypeDefaults() {
        defaultMap = new HashMap<>();
        defaultMap.put(TimerType.TOMATO, Duration.ofMinutes(25));
        defaultMap.put(TimerType.REST, Duration.ofMinutes(5));
    }

    public Map<TimerType, Duration> getDefaultMap() {
        return new HashMap<>(defaultMap);
    }
}
