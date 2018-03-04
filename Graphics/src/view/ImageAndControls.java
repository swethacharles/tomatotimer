package view;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.layout.*;
import model.TimerStateModel;

public class ImageAndControls {

    private final GridPane imageAndControls;

    public ImageAndControls(TimerStateModel timerStateModel) {

        VBox controlsAndTimer = new ControlsAndTimer(timerStateModel).getView();
        TomatoImageView imageView = new TomatoImageView(timerStateModel);

        imageAndControls = new GridPane();
        imageAndControls.add(new ChoicePanel().getView(), 0, 0);
        imageAndControls.add(imageView.getView(), 0, 1);
        imageAndControls.add(controlsAndTimer, 0, 2);


        imageAndControls.getColumnConstraints().add(new ColumnConstraints(imageView.imageFitWidth() * imageView.getMaxGrowthFactor()));
        imageAndControls.getRowConstraints().add(new RowConstraints());
        imageAndControls.getRowConstraints().add(new RowConstraints(imageView.imageFitHeight() *imageView.getMaxGrowthFactor()));
        imageAndControls.getRowConstraints().add(new RowConstraints(controlsAndTimer.getPrefHeight()));

        GridPane.setVgrow(imageView.getView(), Priority.ALWAYS);
        GridPane.setHalignment(imageView.getView(), HPos.CENTER);
        GridPane.setValignment(imageView.getView(), VPos.BOTTOM);

        GridPane.setMargin(controlsAndTimer, new Insets(0, 0, 10, 0));
    }

    public GridPane getView() {
        return imageAndControls;
    }
}
