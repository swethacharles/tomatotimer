package model;

import eventmanagement.Observable;
import eventmanagement.ObservableManager;
import eventmanagement.Observer;

import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;


public class TimerModel implements Observable {

    private boolean playing = false;
    private final ScheduledExecutorService scheduler =
            Executors.newSingleThreadScheduledExecutor(new TomatoThreadFactory());
    private ScheduledFuture<?> scheduledFuture;
    private ObservableManager observableManager = new ObservableManager();

    public void play(Duration timerDuration){
        if(!playing){
            playing = true;
            scheduledFuture = scheduler.scheduleAtFixedRate(this::fireSecondElapsedEvent, 1, 1, TimeUnit.SECONDS);
        }
    }

    private void fireSecondElapsedEvent() {

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
