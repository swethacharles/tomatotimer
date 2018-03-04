package view;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.TimerModel;

public class ControlsAndTimer {

    private final VBox controlsAndTimer;

    public ControlsAndTimer(TimerModel timerModel) {
        TimerView timerView = new TimerView(timerModel);
        ControlsView controlsView = new ControlsView(timerModel);
        controlsAndTimer = new VBox();
        controlsAndTimer.getChildren().addAll(timerView.getNode(), controlsView.getNode());
        controlsAndTimer.setSpacing(5.0);
        Background background = new Background(new BackgroundFill(Color.rgb(255, 255, 255, 0.5), new CornerRadii(5), null));

        controlsAndTimer.setBackground(background);
    }

    public VBox getView() {
        return controlsAndTimer;
    }
}
