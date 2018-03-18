package model.timertype;

import javafx.beans.property.*;

import java.time.Duration;
import java.util.Map;

import static model.timertype.TimerType.TOMATO;

public class TimerTypeModel {
    private final Map<TimerType, Duration> typeDurationMap;
    private ReadOnlyObjectWrapper<TimerType> type;


    public TimerTypeModel() {
        type = new ReadOnlyObjectWrapper<>(TOMATO);
        typeDurationMap = new TimerTypeDefaults().getDefaultMap();
    }

    public Duration getStartingTimerDuration() {
        return typeDurationMap.get(type.get());
    }

    public ReadOnlyObjectProperty<TimerType> typeProperty() {
        return type.getReadOnlyProperty();
    }

    public void setType(TimerType type) {
        this.type.setValue(type);
    }
}
