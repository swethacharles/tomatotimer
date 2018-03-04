package view;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.TimerStateModel;

public class ControlsAndTimer {

    private final VBox controlsAndTimer;

    public ControlsAndTimer(TimerStateModel timerStateModel) {
        TimerView timerView = new TimerView(timerStateModel);
        ControlsView controlsView = new ControlsView(timerStateModel);
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
