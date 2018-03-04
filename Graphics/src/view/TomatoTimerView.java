package view;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import model.TimerStateModel;

public class TomatoTimerView {

    private BorderPane view;


    TomatoTimerView() {
        this.view = new BorderPane();
        TimerStateModel timerStateModel = new TimerStateModel();
        ImageAndControls imageAndControls = new ImageAndControls(timerStateModel);

        view.setCenter(imageAndControls.getView());
        view.setBackground(new Background(new BackgroundImage(
                new Image(getClass().getResourceAsStream("grass_blade.jpg"), view.getWidth(), view.getHeight(), false, false),
                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT)));

    }

    public Node getView() {
        return view;
    }
}
