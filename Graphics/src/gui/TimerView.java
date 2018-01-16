package gui;

import eventmanagement.Event;
import eventmanagement.Observer;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import model.TimerModel;
import model.events.DurationRemainingEvent;

public class TimerView implements Observer{

    private final TimerModel timerModel;
    private HBox timerHolder;
    private Label timeElapsed;

    TimerView(TimerModel timerModel) {
        this(timerModel, new HBox(), new Label("25:00"));
    }

    private TimerView(TimerModel timerModel, HBox timerHolder, Label timeElapsed) {
        this.timerHolder = timerHolder;
        this.timeElapsed = timeElapsed;
        this.timerModel = timerModel;
        timerModel.register(this);
        timerHolder.getChildren().add(timeElapsed);
    }


    public HBox getNode() {
        return timerHolder;
    }

    @Override
    public void notifyEvent(Event event) {
        if(event instanceof DurationRemainingEvent){
            long totalSeconds = DurationRemainingEvent.class.cast(event).getDuration().getSeconds();
            long minutes = totalSeconds / 60;
            long seconds = totalSeconds % 60;
            Platform.runLater(() -> timeElapsed.setText(minutes + ":" + seconds));
        }
    }
}
