package gui;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.TimerModel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class TomatoImageView {

    private static final String TOMATO_URL = "resources/images/tomato-640.png";
    private final TimerModel timerModel;
    private ImageView tomato;

    TomatoImageView(TimerModel timerModel) {
        FileInputStream inputTomato = null;
        this.timerModel = timerModel;
        try {
            inputTomato = new FileInputStream(TOMATO_URL);
            tomato = new ImageView(new Image(inputTomato));
            tomato.setFitHeight(200);
            tomato.setFitWidth(200);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }



    public Node getNode(){
        return tomato;
    }
}
