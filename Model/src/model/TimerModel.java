package model;

import eventmanagement.Event;
import eventmanagement.Observable;
import eventmanagement.ObservableManager;
import eventmanagement.Observer;
import model.events.DurationRemainingUpdateEvent;
import model.events.TimerResetEvent;
import model.events.TimerStartingEvent;

import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import static model.TimerModelState.*;


public class TimerModel implements Observable {


    private static final int ONE_SECOND = 1;
    private static final int MINUTES = 1;

    private boolean playing = false;
    private final ScheduledExecutorService scheduler =
            Executors.newSingleThreadScheduledExecutor(new TomatoThreadFactory());
    private ScheduledFuture<?> scheduledFuture;
    private ObservableManager observableManager = new ObservableManager();
    private Duration startingTimerDuration = Duration.ofMinutes(MINUTES);
    private Duration durationRemaining;
    private TimerModelState state = RESET;

    public TimerModel() {
        durationRemaining = startingTimerDuration;
    }



    public void play() {
        if (canPlay()) {
            observableManager.notify(new TimerStartingEvent(startingTimerDuration));
            state = PLAYING;
            scheduledFuture = scheduler.scheduleAtFixedRate(this::fireSecondElapsedEvent, 1, 1, TimeUnit.SECONDS);
        }
    }

    private boolean canPlay() {
        return !state.equals(PLAYING) && !state.equals(STOPPED);
    }

    private void fireSecondElapsedEvent() {
        durationRemaining = durationRemaining.minusSeconds(ONE_SECOND);
        if(durationRemaining.isZero()){
            stop();
        }
        observableManager.notify(new DurationRemainingUpdateEvent(durationRemaining));

    }

    private void stop() {
        if(state.equals(PLAYING)){
            state = STOPPED;
            cancelFuture();
        }
    }

    public void pause() {
        if(state.equals(PLAYING)){
            state = PAUSED;
            cancelFuture();
        }
    }

    private void cancelFuture() {
        scheduledFuture.cancel(false);
    }


    @Override
    public <T extends Event> void registerFor(Class<T> eventClass, Observer<T> observer) {
        observableManager.registerFor(eventClass, observer);
    }

    @Override
    public <T extends Event> void deregisterFor(Class<T> eventClass, Observer<T> observer) {
        observableManager.deregisterFor(eventClass, observer);

    }

    public void reset() {
        if(!state.equals(RESET)){
            stop();
            durationRemaining = startingTimerDuration;
            observableManager.notify(new TimerResetEvent());
            state = RESET;
        }
    }

    public Duration getTimerDuration() {
        return startingTimerDuration;
    }
}
