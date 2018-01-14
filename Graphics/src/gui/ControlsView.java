package gui;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

/**
 * Responsible for showing the controls of the timer to the user.
 */
public class ControlsView {

    private final HBox controlsHolder;
    private final Button playButton;
    private final Button pauseButton;
    private final Button stopButton;

    public ControlsView() {
        this(new HBox(), new Button(), new Button(), new Button());
    }

    private ControlsView(HBox controlsHolder, Button playButton, Button pauseButton, Button stopButton) {
        this.controlsHolder = controlsHolder;
        this.playButton = playButton;
        this.pauseButton = pauseButton;
        this.stopButton = stopButton;
    }


}
