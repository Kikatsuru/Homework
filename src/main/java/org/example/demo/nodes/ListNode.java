package org.example.demo.nodes;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class ListNode extends BorderPane {
    private static final double CORNER_RADIUS = 30;
    private static final Color BACKGROUND_COLOR = Color.rgb(24, 24, 24, 1.0);
    private static final Background BACKGROUND =
            new Background(new BackgroundFill(BACKGROUND_COLOR, CornerRadii.EMPTY, Insets.EMPTY));
    private static final Color PANELS_COLOR = Color.rgb(33, 33, 33, 1.0);
    private static final Background PANELS_BACKGROUND =
            new Background(new BackgroundFill(PANELS_COLOR, CornerRadii.EMPTY, Insets.EMPTY));
    private static final Color BUTTONS_COLOR = Color.rgb(106, 82, 218, 1.0);
    private final Background BUTTON_BACKGROUND =
            new Background(new BackgroundFill(BUTTONS_COLOR, new CornerRadii(CORNER_RADIUS), Insets.EMPTY));
    private VBox currentBox;
    private final ScrollPane scrollPane = new ScrollPane();
    private final VBox listBox = new VBox();
    private final Button createButton = new Button("Создать");

    public ListNode() {
        super.setPadding(new Insets(20));

        this.scrollPane.setStyle(this.scrollPane.getStyle() +
                "-fx-background: #212121; " +
                "-fx-background-color: #212121;");
        this.scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        this.scrollPane.setContent(this.listBox);

        HBox buttons = new HBox();
        buttons.setPadding(new Insets(0, 0, 20, 0));
        this.createButton.setFont(new Font("Inter", 15));
        this.createButton.setCursor(Cursor.HAND);
        this.createButton.setBackground(BUTTON_BACKGROUND);
        this.createButton.setStyle(this.createButton.getStyle() +
                "-fx-text-fill: white;");
        buttons.getChildren().add(this.createButton);

        super.setTop(buttons);
        super.setCenter(this.scrollPane);
    }

    public ListNode addListElement(String str, EventHandler<MouseEvent> clickHandler) {
        VBox vBox = new VBox();
        vBox.setCursor(Cursor.HAND);
        vBox.minHeightProperty().bind(this.scrollPane.heightProperty().multiply(0.1));
        vBox.minWidthProperty().bind(this.scrollPane.widthProperty());
        vBox.setPadding(new Insets(20));

        Text title = new Text(this.truncateString(str, 32));
        title.setFont(Font.font("Inter", FontWeight.BOLD, 15));
        title.setFill(Color.WHITE);
        vBox.getChildren().add(title);

        vBox.setOnMouseClicked(event -> {
            if (this.currentBox != null) {
                this.currentBox.setBackground(PANELS_BACKGROUND);
            }
            this.currentBox = vBox;
            clickHandler.handle(event);
        });
        vBox.setOnMouseEntered(event -> vBox.setBackground(BACKGROUND));
        vBox.setOnMouseExited(event -> {
            if (this.currentBox != vBox) {
                vBox.setBackground(PANELS_BACKGROUND);
            }
        });
        this.listBox.getChildren().add(vBox);
        return this;
    }

    public void setOnCreateListener(EventHandler<MouseEvent> clickHandler) {
        this.createButton.setOnMouseClicked(clickHandler);
    }

    private String truncateString(String string, int size) {
        if (string == null || string.length() <= size) {
            return string;
        } else {
            int halfSize = (size) / 2;
            String leftHalf = string.substring(0, halfSize);
            String rightHalf = string.substring(string.length() - halfSize);
            return leftHalf + "..." + rightHalf;
        }
    }

}
