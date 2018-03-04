package view;


import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

import static style.view.ChoicePanelCssClass.CHOICE_PANEL;

public class ChoicePanel {


    private final VBox view;

    public ChoicePanel() {
        view = new VBox();
        ChoiceBox playBox = new ChoiceBox(new ImageView(new Image(getClass().getResourceAsStream("red_tomato_button.png"))));
        ChoiceBox restBox = new ChoiceBox(new ImageView(new Image(getClass().getResourceAsStream("cucumber_button.png"))));


        view.getStyleClass().add(CHOICE_PANEL);
        view.getChildren().addAll(playBox.getView(), restBox.getView());

    }

    public VBox getView() {
        return view;
    }
}
