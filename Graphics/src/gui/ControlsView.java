package gui;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import model.TimerModel;

/**
 * Responsible for showing the controls of the timer to the user.
 */
public class ControlsView {

    private final HBox controlsHolder;
    private final Button playButton;
    private final Button pauseButton;
    private final Button stopButton;
    private final TimerModel timerModel;

    public ControlsView(TimerModel timerModel) {
        this(timerModel, new HBox(), new Button(), new Button(), new Button());
    }

    private ControlsView(TimerModel timerModel, HBox controlsHolder, Button playButton, Button pauseButton, Button stopButton) {
        this.controlsHolder = controlsHolder;
        this.playButton = playButton;
        this.pauseButton = pauseButton;
        this.stopButton = stopButton;
        this.timerModel = timerModel;
        controlsHolder.getChildren().addAll(playButton, pauseButton, stopButton);
        playButton.setOnAction(event -> timerModel.play());
    }

    HBox getNode() {
        return controlsHolder;
    }
}
