package viewmodel;

import eventmanagement.Event;
import eventmanagement.Observable;
import eventmanagement.ObservableManager;
import eventmanagement.Observer;
import model.TimerStateModel;
import model.events.DurationRemainingUpdateEvent;
import model.events.TimerResetEvent;
import model.events.TimerStartingEvent;
import viewmodel.events.TomatoResizeEvent;

import java.time.Duration;

public class TomatoImageViewModel implements Observable{


    private static final double CURRENT_SIZE = 1.0;
    private static final double PROGRESS_MULTIPLIER = 1.0;
    private final ObservableManager observableManager;
    private Duration startDuration;

    public TomatoImageViewModel(TimerStateModel timerStateModel) {
        observableManager = new ObservableManager();
        timerStateModel.registerFor(TimerStartingEvent.class, this::handleTimerStarted);
        timerStateModel.registerFor(DurationRemainingUpdateEvent.class, this::handleDurationRemainingUpdate);
        timerStateModel.registerFor(TimerResetEvent.class, resetEvent -> handleTimerRest());
    }

    private <T extends Event> void handleDurationRemainingUpdate(DurationRemainingUpdateEvent updateEvent) {
        Duration durationRemaining = updateEvent.getDuration();
        long elapsedSeconds = startDuration.getSeconds() - durationRemaining.getSeconds();
        double progress = (double) elapsedSeconds / startDuration.getSeconds() * PROGRESS_MULTIPLIER;
        double resizeFraction = progress + CURRENT_SIZE;
        observableManager.notify(new TomatoResizeEvent(resizeFraction));
    }

    private void handleTimerRest() {
        observableManager.notify(new TomatoResizeEvent(CURRENT_SIZE));
    }

    private void handleTimerStarted(TimerStartingEvent timerStartingEvent) {
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
        return CURRENT_SIZE +PROGRESS_MULTIPLIER;
    }
}
