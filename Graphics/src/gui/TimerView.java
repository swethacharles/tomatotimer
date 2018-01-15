package gui;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class TimerView {

    private HBox timerHolder;
    private Label timeElapsed;

    TimerView() {
        this(new HBox(), new Label("25:00"));
    }

    private TimerView(HBox timerHolder, Label timeElapsed) {
        this.timerHolder = timerHolder;
        this.timeElapsed = timeElapsed;
        timerHolder.getChildren().add(timeElapsed);
    }


    public HBox getNode() {
        return timerHolder;
    }
}
