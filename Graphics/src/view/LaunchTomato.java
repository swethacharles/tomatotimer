package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import view.styling.TomatoCssStyles;
import view.styling.TomatoSceneCssStyler;



/**
 * Created by swetha on 13/11/17.
 */
public class LaunchTomato extends Application
{

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Hello Tomato!");

        StackPane root = new StackPane();
        root.getChildren().add(new TomatoTimerView().getView());

        Scene scene = new Scene(root);
        new TomatoSceneCssStyler().applyStyleSheets(new TomatoCssStyles(), scene);

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
