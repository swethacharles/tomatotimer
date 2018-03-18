package viewmodel;

import eventmanagement.Event;
import eventmanagement.Observable;
import eventmanagement.ObservableManager;
import eventmanagement.Observer;
import model.TimerModel;
import model.events.DurationRemainingUpdateEvent;
import model.events.TimerResetEvent;
import model.events.TimerStartingEvent;
import model.events.TimerTypeChangeEvent;
import viewmodel.events.TomatoResizeEvent;

import java.time.Duration;

public class TomatoImageViewModel implements Observable{


    private static final double STARTING_SIZE = 1.0;
    private static final double PROGRESS_MULTIPLIER = 1.0;
    private final ObservableManager observableManager;
    private Duration startDuration;

    public TomatoImageViewModel(TimerModel timerModel) {
        observableManager = new ObservableManager();

        timerModel.registerFor(TimerStartingEvent.class, this::handleTimerStarted);
        timerModel.registerFor(DurationRemainingUpdateEvent.class, this::handleDurationRemainingUpdate);
        timerModel.registerFor(TimerResetEvent.class, resetEvent -> handleTimerRest());
        timerModel.registerFor(TimerTypeChangeEvent.class, this::handleTypeChange);
    }

    private void handleTypeChange(TimerTypeChangeEvent timerTypeChangeEvent) {

    }

    private void handleDurationRemainingUpdate(DurationRemainingUpdateEvent updateEvent) {
        Duration durationRemaining = updateEvent.getDuration();
        long elapsedSeconds = startDuration.getSeconds() - durationRemaining.getSeconds();
        double progress = (double) elapsedSeconds / startDuration.getSeconds() * PROGRESS_MULTIPLIER;
        double resizeFraction = progress + STARTING_SIZE;
        observableManager.notify(new TomatoResizeEvent(resizeFraction));
    }

    private void handleTimerRest() {
        observableManager.notify(new TomatoResizeEvent(STARTING_SIZE));
        startDuration = Duration.ZERO;
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
        return STARTING_SIZE +PROGRESS_MULTIPLIER;
    }
}
