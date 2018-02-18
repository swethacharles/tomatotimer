package view;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.layout.*;
import model.TimerModel;

public class ImageAndControls {

    public static final int GROWTH_FACTOR = 2;
    private final GridPane imageAndControls;

    public ImageAndControls(TimerModel timerModel) {

        VBox controlsAndTimer = new ControlsAndTimer(timerModel).getView();
        TomatoImageView imageView = new TomatoImageView(timerModel);

        imageAndControls = new GridPane();
        imageAndControls.add(new ChoicePanel().getView(), 0, 0);
        imageAndControls.add(imageView.getView(), 1, 0);
        imageAndControls.add(controlsAndTimer, 1, 1);

        imageAndControls.setAlignment(Pos.CENTER);
        imageAndControls.getColumnConstraints().add(new ColumnConstraints());
        imageAndControls.getColumnConstraints().add(new ColumnConstraints(imageView.getView().getFitWidth() * imageView.getGrowthFactor()));
        imageAndControls.getRowConstraints().add(new RowConstraints(imageView.getView().getFitHeight() *imageView.getGrowthFactor()));
        imageAndControls.getRowConstraints().add(new RowConstraints(controlsAndTimer.getPrefHeight()));

        GridPane.setVgrow(imageView.getView(), Priority.ALWAYS);
        GridPane.setHalignment(imageView.getView(), HPos.CENTER);
        GridPane.setValignment(imageView.getView(), VPos.BOTTOM);

        GridPane.setHalignment(controlsAndTimer, HPos.CENTER);
        GridPane.setHgrow(controlsAndTimer, Priority.ALWAYS);
        GridPane.setMargin(controlsAndTimer, new Insets(0, 0, 10, 0));
    }

    public GridPane getView() {
        return imageAndControls;
    }
}
