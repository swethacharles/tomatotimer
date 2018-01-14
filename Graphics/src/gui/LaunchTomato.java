package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.FileInputStream;


/**
 * Created by swetha on 13/11/17.
 */
public class LaunchTomato extends Application
{

    private static final String TOMATO_URL = "resources/images/tomato-640.png";

    public static void launchView(){
        launch();
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Hello Tomato!");
        FileInputStream inputTomato = new FileInputStream(TOMATO_URL);
        ImageView tomato = new ImageView(new Image(inputTomato));

        StackPane root = new StackPane();
        root.getChildren().add(tomato);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
