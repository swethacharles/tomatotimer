package viewmodel;

import eventmanagement.Event;
import eventmanagement.Observable;
import eventmanagement.ObservableManager;
import eventmanagement.Observer;
import model.TimerModel;
import model.events.DurationRemainingUpdateEvent;
import model.events.TimerStartingEvent;
import viewmodel.events.TomatoGrowEvent;

import java.time.Duration;

public class TomatoImageViewModel implements Observable{

    private final ObservableManager observableManager;
    public TimerModel timerModel;
    private Duration startDuration;

    public TomatoImageViewModel(TimerModel timerModel) {
        observableManager = new ObservableManager();
        this.timerModel = timerModel;
        timerModel.registerFor(TimerStartingEvent.class, this::handleTimerStarted);
        timerModel.registerFor(DurationRemainingUpdateEvent.class, this::handleDurationRemainingUpdate);
    }

    private <T extends Event> void handleDurationRemainingUpdate(DurationRemainingUpdateEvent updateEvent) {
        Duration durationRemaining = updateEvent.getDuration();
        long elapsedSeconds = startDuration.getSeconds() - durationRemaining.getSeconds();
        double progress = elapsedSeconds * 1.0 / startDuration.getSeconds() * 1.0;
        double growthFraction = progress + 1;
        observableManager.notify(new TomatoGrowEvent(growthFraction));
    }

    private <T extends Event> void handleTimerStarted(TimerStartingEvent timerStartingEvent) {
        startDuration = timerStartingEvent.getStartDuration();
    }

    @Override
    public <T extends Event> void registerFor(Class<T> eventClass, Observer<T> observer) {
        observableManager.registerFor(eventClass, observer);
    }

    @Override
    public <T extends Event> void deregisterFor(Class<T> eventClass, Observer<T> observer) {
        observableManager.deregisterFor(eventClass, observer);
    }
}
