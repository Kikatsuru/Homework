package net.kikatsuru.homework.nodes;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import net.kikatsuru.homework.Environment;
import net.kikatsuru.homework.themes.Theme;
import net.kikatsuru.homework.utils.StyleUtils;
import net.kikatsuru.homework.utils.elements.FontUtils;
import net.kikatsuru.homework.utils.elements.TextUtils;

/**
 * Node that represents a list.
 */
public class ListNode extends ScrollPane {
    /**
     * Current theme.
     */
    private static final Theme THEME = Environment.theme;

    /**
     * Current chose box.
     */
    private VBox currentBox;

    /**
     * List box.
     */
    private final VBox listBox = new VBox();

    /**
     * Default constructor.
     */
    public ListNode() {
        StyleUtils.setBackground(this, THEME.getGlobalColor());
        super.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        super.setContent(this.listBox);
    }

    /**
     * Add element to list.
     *
     * @param str String to add.
     *
     * @return ListNode instance.
     */
    public ListNode addListElement(String str) {
        return this.addListElement(str, mouseEvent -> { });
    }

    /**
     * Add element to list.
     *
     * @param str String to add.
     * @param clickHandler Click handler.
     *
     * @return ListNode instance.
     */
    public ListNode addListElement(String str, EventHandler<MouseEvent> clickHandler) {
        VBox vBox = new VBox();
        vBox.setCursor(Cursor.HAND);
        vBox.minHeightProperty().bind(this.heightProperty().multiply(0.1));
        vBox.minWidthProperty().bind(this.widthProperty());
        vBox.setPadding(new Insets(20));

        Text title = TextUtils.createText(TextUtils.truncateString(str, 32), FontUtils.createFont(FontWeight.BOLD, 15));
        vBox.getChildren().add(title);

        vBox.setOnMouseClicked(event -> {
            if (this.currentBox != null) {
                this.currentBox.setBackground(THEME.getGlobalBackground());
            }
            this.currentBox = vBox;
            clickHandler.handle(event);
        });
        vBox.setOnMouseEntered(event -> vBox.setBackground(THEME.getPanelsBackground()));
        vBox.setOnMouseExited(event -> {
            if (this.currentBox != vBox) {
                vBox.setBackground(THEME.getGlobalBackground());
            }
        });
        this.listBox.getChildren().add(vBox);
        return this;
    }

}
