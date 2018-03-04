package view.styling;

import javafx.scene.Scene;

import java.util.List;

public class TomatoSceneCssStyler {

   public void applyStyleSheets(TomatoCssStyles styles, Scene scene){
         scene.getStylesheets().addAll(styles.getStyleSheetPaths());
   }


}
