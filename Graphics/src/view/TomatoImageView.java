package view;

import eventmanagement.Event;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.TimerModel;
import viewmodel.TomatoImageViewModel;
import viewmodel.events.TomatoGrowEvent;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class TomatoImageView {

    private static final int START_SIZE = 200;
    private static final String TOMATO_URL = "resources/images/tomato-640.png";
    private final TomatoImageViewModel viewModel;
    private ImageView tomato;

    TomatoImageView(TimerModel timerModel) {
        FileInputStream inputTomato = null;
        try {
            inputTomato = new FileInputStream(TOMATO_URL);
            tomato = new ImageView(new Image(inputTomato));
            tomato.setPreserveRatio(true);
            tomato.setFitHeight(START_SIZE);
            tomato.setFitWidth(START_SIZE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.viewModel = new TomatoImageViewModel(timerModel);
        this.viewModel.registerFor(TomatoGrowEvent.class, this::handleGrow);
    }

    private <T extends Event> void handleGrow(TomatoGrowEvent growEvent) {
        double newSize = START_SIZE * growEvent.getGrowthFraction();
        tomato.setFitWidth(newSize);
        tomato.setFitHeight(newSize);

    }


    public Node getNode(){
        return tomato;
    }
}
