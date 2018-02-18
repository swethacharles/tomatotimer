package view;


import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.VBox;

public class ChoicePanel {

    private final Button restButton;
    private final Button playButton;
    private final Button settingsButton;
    private final VBox view;

    public ChoicePanel() {
        restButton = new Button();
        restButton.setGraphic(new ButtonTomatoImageView().getView());
//        restButton.setBackground(new Background(new BackgroundImage(new ButtonTomatoImageView().getImage()))
        restButton.setStyle("-fx-background-color: transparent;");
        restButton.getStyleClass().add("-fx-background-color: transparent;");
        restButton.getStyleClass().add("-fx-border-color: transparent;");

        playButton = new Button();
        settingsButton = new Button();

        view = new VBox();
        view.getChildren().addAll(restButton, playButton, settingsButton);
    }

    public VBox getView() {
        return view;
    }
}
