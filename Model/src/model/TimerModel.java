package model;

import eventmanagement.Observable;
import eventmanagement.ObservableManager;
import eventmanagement.Observer;
import model.events.DurationRemainingEvent;

import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;


public class TimerModel implements Observable {


    public static final int ONE_SECOND_ELAPSED = 1;
    public static final String DURATION_UPDATE = "DURATION_UPDATE";

    private boolean playing = false;
    private final ScheduledExecutorService scheduler =
            Executors.newSingleThreadScheduledExecutor(new TomatoThreadFactory());
    private ScheduledFuture<?> scheduledFuture;
    private ObservableManager observableManager = new ObservableManager();
    private Duration timerDuration = Duration.ofMinutes(25);
    private Duration durationRemaining;

    public void play() {
        if (!playing) {
            playing = true;
            this.durationRemaining = timerDuration;
            scheduledFuture = scheduler.scheduleAtFixedRate(this::fireSecondElapsedEvent, 1, 1, TimeUnit.SECONDS);
        }
    }

    private void fireSecondElapsedEvent() {
        durationRemaining = durationRemaining.minusSeconds(ONE_SECOND_ELAPSED);
        observableManager.notifyEvent(new DurationRemainingEvent(durationRemaining));
    }


    @Override
    public void register(Observer observer) {
        observableManager.register(observer);
    }

    @Override
    public void deregister(Observer observer) {
        observableManager.deregister(observer);
    }
}
