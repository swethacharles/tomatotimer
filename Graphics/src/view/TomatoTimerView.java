package view;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import model.TimerModel;

public class TomatoTimerView {

    private BorderPane view;


    TomatoTimerView() {
        this.view = new BorderPane();
        TimerModel timerModel = new TimerModel();

        VBox controlsAndTimer = new ControlsAndTimer(timerModel).getView();
        TomatoImageView imageView = new TomatoImageView(timerModel);

        GridPane imageAndControls = new GridPane();
        imageAndControls.add(imageView.getView(), 0, 0);
        imageAndControls.add(controlsAndTimer, 0, 1);

        imageAndControls.setAlignment(Pos.CENTER);
        imageAndControls.getColumnConstraints().add(new ColumnConstraints(imageView.getView().getFitWidth() * 2));
        imageAndControls.getRowConstraints().add(new RowConstraints(imageView.getView().getFitHeight() * 2));
        imageAndControls.getRowConstraints().add(new RowConstraints(controlsAndTimer.getPrefHeight()));
        GridPane.setVgrow(imageView.getView(), Priority.ALWAYS);
        GridPane.setHalignment(imageView.getView(), HPos.CENTER);
        GridPane.setValignment(imageView.getView(), VPos.BOTTOM);
        GridPane.setHalignment(controlsAndTimer, HPos.CENTER);
        GridPane.setMargin(controlsAndTimer, new Insets(0, 0, 10, 0));


        view.setCenter(imageAndControls);
        view.setBackground(new Background(new BackgroundImage(
                new Image(getClass().getResourceAsStream("grass_blade.jpg"), view.getWidth(), view.getHeight(), false, false),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT)));

    }

    private ImageView createImageView(String pathToImage) {
        Image playImage = new Image(getClass().getResourceAsStream(pathToImage));
        return new ImageView(playImage);
    }


    public Node getView() {
        return view;
    }
}
