package view;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import model.TimerModel;

public class TomatoTimerView {

    private BorderPane view;


    TomatoTimerView() {
        this.view = new BorderPane();
        TimerModel timerModel = new TimerModel();
        ImageAndControls imageAndControls = new ImageAndControls(timerModel);

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
