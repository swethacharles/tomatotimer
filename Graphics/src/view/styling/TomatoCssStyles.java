package view.styling;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Holds the Tomato CSS styles.
 */
public class TomatoCssStyles {

    private List<String> styleSheetPaths;

    public TomatoCssStyles() {
        styleSheetPaths = new ArrayList<>();
        styleSheetPaths.add("/style/view/ChoicePanelCssSheet.css");
    }


    public List<String> getStyleSheetPaths() {
        return styleSheetPaths.stream().map(this::validatePath).collect(Collectors.toList());
    }

    private String validatePath(String styleSheetPath) {
        return getClass().getResource(styleSheetPath).toExternalForm();
    }

}
