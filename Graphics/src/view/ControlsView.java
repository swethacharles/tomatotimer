package view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import model.TimerStateModel;

/**
 * Responsible for showing the controls of the timer to the user.
 */
public class ControlsView {

    private final HBox controlsHolder;
    private final Button playButton;
    private final Button pauseButton;
    private final Button resetButton;
    private final TimerStateModel timerStateModel;

    public ControlsView(TimerStateModel timerStateModel) {
        this(timerStateModel, new HBox(), new Button(), new Button(), new Button());
    }

    private ControlsView(TimerStateModel timerStateModel, HBox controlsHolder, Button playButton, Button pauseButton, Button resetButton) {
        this.playButton = playButton;
        playButton.setGraphic(createImageView("play_image.png"));
        this.pauseButton = pauseButton;
        pauseButton.setGraphic(createImageView("pause_image.png"));
        this.resetButton = resetButton;
        resetButton.setGraphic(createImageView("refresh_image.png"));
        this.controlsHolder = controlsHolder;
        this.timerStateModel = timerStateModel;
        controlsHolder.setSpacing(5.0);

        playButton.setOnAction(event -> timerStateModel.play());
        pauseButton.setOnAction(event -> timerStateModel.pause());
        resetButton.setOnAction(event -> timerStateModel.reset());
        controlsHolder.getChildren().addAll(playButton, pauseButton, resetButton);
        controlsHolder.setAlignment(Pos.CENTER);

    }

    private ImageView createImageView(String pathToImage) {
        Image playImage = new Image(getClass().getResourceAsStream(pathToImage));
        return new ImageView(playImage);
    }

    HBox getNode() {
        return controlsHolder;
    }
}
