package view;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

import static style.view.ChoicePanelCssClass.CHOICE_BUTTON;


/**
 * Box for placing an option to choose.
 */
public class ChoiceBox {

    private final Button roundButton;


    public ChoiceBox(ImageView imageView){
        roundButton = new Button();
        roundButton.getStyleClass().add(CHOICE_BUTTON);

        imageView.setPreserveRatio(true);
        imageView.fitWidthProperty().bind(roundButton.prefWidthProperty().multiply(0.85));

        roundButton.setGraphic(imageView);

    }

    public Button getView() {
        return roundButton;
    }
}
