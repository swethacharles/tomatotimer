package view;

import eventmanagement.Event;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import model.TimerModel;
import viewmodel.TomatoImageViewModel;
import viewmodel.events.TomatoResizeEvent;

public class TomatoImageView {

    private static final int START_SIZE = 200;
    private static final String TOMATO_URL = "tomato-640.png";
    private final TomatoImageViewModel viewModel;
    private ImageView imageView;

    TomatoImageView(TimerModel timerModel) {
        this.viewModel = new TomatoImageViewModel(timerModel);
        this.viewModel.registerFor(TomatoResizeEvent.class, this::handleGrow);

        imageView = new ImageView(new Image(getClass().getResourceAsStream(TOMATO_URL)));
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(START_SIZE);
        imageView.setFitWidth(START_SIZE);

        HBox box = new HBox();
        box.getChildren().add(imageView);
        box.setAlignment(Pos.CENTER);
    }

    private <T extends Event> void handleGrow(TomatoResizeEvent growEvent) {
        double newSize = START_SIZE * growEvent.getResizeFraction();
        imageView.setFitWidth(newSize);
        imageView.setFitHeight(newSize);
    }


    public ImageView getView(){
        return imageView;
    }

    public double imageFitWidth() {
        return imageView.fitWidthProperty().get();
    }

    public double imageFitHeight(){
        return imageView.fitHeightProperty().get();
    }

    public double getMaxGrowthFactor(){
        return viewModel.getGrowthFraction();
    }
}
