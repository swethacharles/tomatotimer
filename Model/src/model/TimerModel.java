package model;

import eventmanagement.Event;
import eventmanagement.Observable;
import eventmanagement.ObservableManager;
import eventmanagement.Observer;
import model.events.DurationRemainingUpdateEvent;
import model.events.TimerResetEvent;
import model.events.TimerStartingEvent;
import model.events.TimerTypeChangeEvent;
import model.timertype.TimerType;
import model.timertype.TimerTypeModel;

import javax.swing.event.ChangeListener;
import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import static model.TimerState.*;


public class TimerModel implements Observable {


    private static final int ONE_SECOND = 1;

    private final ScheduledExecutorService scheduler =
            Executors.newSingleThreadScheduledExecutor(new TomatoThreadFactory());

    private boolean playing = false;
    private ScheduledFuture<?> scheduledFuture;
    private ObservableManager observableManager = new ObservableManager();
    private TimerState state = RESET;
    private TimerTypeModel typeModel;
    private Duration durationRemaining;

    public TimerModel() {
        typeModel = new TimerTypeModel();
        typeModel.typeProperty().addListener((obs, oldVal, newVal) -> observableManager.notify(new TimerTypeChangeEvent(newVal)));
    }


    public void play() {
        if (canPlay()) {
            durationRemaining = typeModel.getStartingTimerDuration();
            observableManager.notify(new TimerStartingEvent(durationRemaining));
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
            durationRemaining = Duration.ZERO;
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


    public void reset() {
        if(!state.equals(RESET)){
            stop();
            observableManager.notify(new TimerResetEvent());
            state = RESET;
        }
    }

    public void setType(TimerType type){
        typeModel.setType(type);
    }

    public Duration getStartingTimerDuration() {
        return typeModel.getStartingTimerDuration();
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
