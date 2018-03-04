package view;

import eventmanagement.Observer;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import model.TimerStateModel;
import model.events.DurationRemainingUpdateEvent;
import model.events.TimerResetEvent;
import utility.TimeFormatter;

import java.time.Duration;

public class TimerView implements Observer<DurationRemainingUpdateEvent>{

    private final TimerStateModel timerStateModel;
    private HBox timerHolder;
    private Label timeElapsed;

    TimerView(TimerStateModel timerStateModel) {
        this(timerStateModel, new HBox());
    }

    private TimerView(TimerStateModel timerStateModel, HBox timerHolder) {
        this.timerHolder = timerHolder;
        this.timeElapsed = new Label(new TimeFormatter().formatDuration(timerStateModel.getTimerDuration()));
        this.timerStateModel = timerStateModel;
        timerStateModel.registerFor(DurationRemainingUpdateEvent.class, this::handleDurationRemainingUpdate);
        timerStateModel.registerFor(TimerResetEvent.class, this::handleReset);
        timerHolder.getChildren().add(timeElapsed);
        timerHolder.setAlignment(Pos.CENTER);
    }

    private  void handleReset(TimerResetEvent resetEvent) {
        Duration duration = timerStateModel.getTimerDuration();
        updateDisplayText(duration);
    }

    public HBox getNode() {
        return timerHolder;
    }

    @Override
    public void handleDurationRemainingUpdate(DurationRemainingUpdateEvent event) {
        updateDisplayText(event.getDuration());
    }

    private void updateDisplayText(Duration duration) {
        String durationText = new TimeFormatter().formatDuration(duration);
        Platform.runLater(() -> timeElapsed.setText(durationText));
    }
}
