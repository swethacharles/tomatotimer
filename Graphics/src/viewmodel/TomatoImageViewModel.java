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
    private double progressMultiplier = 1.0;
    private double growthFraction = progressMultiplier * 2;
    private Duration startDuration;

    public TomatoImageViewModel(TimerModel timerModel) {
        observableManager = new ObservableManager();
        timerModel.registerFor(TimerStartingEvent.class, this::handleTimerStarted);
        timerModel.registerFor(DurationRemainingUpdateEvent.class, this::handleDurationRemainingUpdate);
    }

    private <T extends Event> void handleDurationRemainingUpdate(DurationRemainingUpdateEvent updateEvent) {
        Duration durationRemaining = updateEvent.getDuration();
        long elapsedSeconds = startDuration.getSeconds() - durationRemaining.getSeconds();
        double progress = (double) elapsedSeconds / startDuration.getSeconds();
        double growthFraction = progress + 1.0;
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

    public double getGrowthFraction() {
        return growthFraction;
    }
}
