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
    private ImageView tomato;
    private HBox box;

    TomatoImageView(TimerModel timerModel) {
        tomato = new ImageView(new Image(getClass().getResourceAsStream(TOMATO_URL)));
        tomato.setPreserveRatio(true);
        tomato.setFitHeight(START_SIZE);
        tomato.setFitWidth(START_SIZE);
        this.viewModel = new TomatoImageViewModel(timerModel);
        this.viewModel.registerFor(TomatoResizeEvent.class, this::handleGrow);

        box = new HBox();
        box.getChildren().add(tomato);
        box.setAlignment(Pos.CENTER);
    }

    private <T extends Event> void handleGrow(TomatoResizeEvent growEvent) {
        double newSize = START_SIZE * growEvent.getResizeFraction();
        tomato.setFitWidth(newSize);
        tomato.setFitHeight(newSize);
    }


    public HBox getView(){
        return box;
    }

    public double imageFitWidth() {
        return tomato.fitWidthProperty().get();
    }

    public double imageFitHeight(){
        return tomato.fitHeightProperty().get();
    }

    public double getMaxGrowthFactor(){
        return viewModel.getGrowthFraction();
    }
}
