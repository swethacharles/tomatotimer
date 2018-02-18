package view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class ButtonTomatoImageView {

    private final ImageView view;
    private final Image image;

    public ButtonTomatoImageView() {
        image = new Image(getClass().getResourceAsStream("tomato-640.png"));
        view = new ImageView(image);
        view.setPreserveRatio(true);
        view.setFitHeight(30);
    }

    public ImageView getView() {
        return this.view;
    }


    public Image getImage() {
        return this.image;
    }
}
