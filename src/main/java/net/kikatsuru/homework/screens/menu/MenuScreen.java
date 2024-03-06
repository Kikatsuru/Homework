package net.kikatsuru.homework.screens.menu;

import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import net.kikatsuru.homework.Environment;
import net.kikatsuru.homework.themes.Theme;
import net.kikatsuru.homework.utils.StyleUtils;
import net.kikatsuru.homework.utils.elements.FontUtils;
import net.kikatsuru.homework.utils.elements.TextUtils;

/**
 * Menu screen.
 */
public class MenuScreen extends BorderPane {
    /**
     * Current theme
     */
    private static final Theme THEME = Environment.theme;

    /**
     * Menu elements.
     */
    private final ScrollPane elementsPane = new ScrollPane();

    /**
     * Current chose element.
     */
    private VBox currentBox;

    /**
     * Default constructor.
     */
    public MenuScreen() {
        this.drawScreen();
    }

    /**
     * Draw menu screen.
     */
    private void drawScreen() {
        StyleUtils.setBackground(this.elementsPane, THEME.getPanelsColor());
        this.elementsPane.prefHeightProperty().bind(super.heightProperty().multiply(0.77));
        this.elementsPane.prefWidthProperty().bind(super.widthProperty().divide(4));
        this.elementsPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        this.elementsPane.setContent(new VBox());
        super.setLeft(this.elementsPane);
    }

    /**
     * Add element to list.
     *
     * @param element Menu element.
     *
     * @return MenuScreen.
     */
    public MenuScreen addListElement(AbstractMenuElement element) {
        VBox elementBox = new VBox();
        elementBox.setCursor(Cursor.HAND);
        elementBox.minHeightProperty().bind(this.elementsPane.heightProperty().multiply(0.1));
        elementBox.minWidthProperty().bind(this.elementsPane.widthProperty());
        elementBox.setPadding(new Insets(20));

        Text title = TextUtils.createText(TextUtils.truncateString(element.getTitle(), 16), FontUtils.createFont(FontWeight.BOLD, 15));
        elementBox.getChildren().add(title);

        elementBox.setOnMouseClicked(event -> {
            if (this.currentBox != null) {
                this.currentBox.setBackground(THEME.getPanelsBackground());
            }
            elementBox.setBackground(THEME.getGlobalBackground());

            VBox parent = new VBox();
            parent.setPadding(new Insets(20));
            element.minHeightProperty().bind(super.heightProperty().subtract(40));
            parent.getChildren().add(element);
            super.setCenter(parent);
            this.currentBox = elementBox;
        });
        elementBox.setOnMouseEntered(event -> elementBox.setBackground(THEME.getGlobalBackground()));
        elementBox.setOnMouseExited(event -> {
            if (this.currentBox != elementBox) {
                elementBox.setBackground(THEME.getPanelsBackground());
            }
        });

        ((Pane) this.elementsPane.getContent()).getChildren().add(elementBox);
        return this;
    }

}
