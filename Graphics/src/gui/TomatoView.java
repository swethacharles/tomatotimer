package gui;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.TimerModel;

public class TomatoView {

    private BorderPane view;


    TomatoView() {
        this.view = new BorderPane();
        TimerModel timerModel = new TimerModel();
        HBox imageAndControls = new HBox();
        VBox controlsAndTimer = createControlsAndTimerBox(timerModel);
        TomatoImageView imageView = new TomatoImageView(timerModel);

        imageAndControls.getChildren().addAll(imageView.getNode(), controlsAndTimer);
        imageAndControls.setAlignment(Pos.CENTER);
        view.setCenter(imageAndControls);

    }

    private VBox createControlsAndTimerBox(TimerModel timerModel) {
        TimerView timerView = new TimerView(timerModel);
        ControlsView controlsView = new ControlsView(timerModel);
        VBox controlsAndTimer = new VBox();
        controlsAndTimer.getChildren().addAll(timerView.getNode(), controlsView.getNode());
        controlsAndTimer.setAlignment(Pos.CENTER);
        return controlsAndTimer;
    }

    public Node getNode() {
        return view;
    }
}
