package gui;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TomatoView {

    private BorderPane view;


    TomatoView() {
        this.view = new BorderPane();

        HBox imageAndControls = new HBox();
        VBox controlsAndTimer = createControlsAndTimerBox();
        TomatoImageView imageView = new TomatoImageView();

        imageAndControls.getChildren().addAll(imageView.getNode(), controlsAndTimer);
        imageAndControls.setAlignment(Pos.CENTER);
        view.setCenter(imageAndControls);

    }

    private VBox createControlsAndTimerBox() {
        TimerView timerView = new TimerView();
        ControlsView controlsView = new ControlsView();
        VBox controlsAndTimer = new VBox();
        controlsAndTimer.getChildren().addAll(timerView.getNode(), controlsView.getNode());
        controlsAndTimer.setAlignment(Pos.CENTER);
        return controlsAndTimer;
    }

    public Node getNode() {
        return view;
    }
}
