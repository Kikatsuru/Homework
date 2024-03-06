package net.kikatsuru.homework.utils.elements;

import javafx.geometry.Pos;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import net.kikatsuru.homework.Environment;
import net.kikatsuru.homework.themes.Theme;
import net.kikatsuru.homework.utils.StyleUtils;

/**
 * Fields manage class.
 */
public class FieldUtils {
    /**
     * Current app theme.
     */
    private static final Theme THEME = Environment.theme;

    /**
     * Create text field.
     *
     * @param defaultText Default field text.
     * @param placeholder Field placeholder.
     *
     * @return TextField object.
     */
    public static TextField createTextField(String defaultText, String placeholder) {
        return createTextField(defaultText, placeholder, FontUtils.createFont(), THEME.getGlobalBackgroundRounded());
    }

    /**
     * Create text field.
     *
     * @param defaultText Default field text.
     * @param placeholder Field placeholder.
     * @param font Field font.
     *
     * @return TextField object.
     */
    public static TextField createTextField(String defaultText, String placeholder, Font font) {
        return createTextField(defaultText, placeholder, font, THEME.getGlobalBackgroundRounded());
    }

    /**
     * Create text field.
     *
     * @param defaultText Default field text.
     * @param placeholder Field placeholder.
     * @param font Field font.
     * @param background Field background.
     *
     * @return TextField object.
     */
    public static TextField createTextField(String defaultText, String placeholder, Font font, Background background) {
        TextField newField = new TextField(defaultText);
        newField.setPromptText(placeholder);
        newField.setBackground(background);
        newField.setFont(font);
        StyleUtils.setTextColor(newField, Color.WHITE);

        return newField;
    }

    /**
     * Create password field.
     *
     * @param placeholder Field placeholder.
     * @param font Field font.
     *
     * @return PasswordField object.
     */
    public static PasswordField createPasswordField(String placeholder, Font font) {
        return createPasswordField(placeholder, font, THEME.getGlobalBackgroundRounded());
    }

    /**
     * Create password field.
     *
     * @param placeholder Field placeholder.
     * @param font Field font.
     * @param background Field background.
     *
     * @return PasswordField object.
     */
    public static PasswordField createPasswordField(String placeholder, Font font, Background background) {
        PasswordField newField = new PasswordField();
        newField.setPromptText(placeholder);
        newField.setBackground(background);
        newField.setFont(font);
        StyleUtils.setTextColor(newField, Color.WHITE);

        return newField;
    }

    /**
     * Create text area field.
     *
     * @param defaultText Default field text.
     * @param placeholder Field placeholder.
     * @param font Field font.
     * @param color Field color.
     *
     * @return TextArea object.
     */
    public static TextArea createTextArea(String defaultText, String placeholder, Font font, Color color) {
        TextArea textArea = new TextArea(defaultText);
        textArea.setPromptText(placeholder);
        textArea.setFont(font);
        StyleUtils.setTextColor(textArea, Color.WHITE);
        StyleUtils.setBackground(textArea, color);

        return textArea;
    }

    /**
     * Set max length for input control.
     *
     * @param field Field.
     * @param max Max length.
     *
     * @return Field object.
     * @param <T> Any TextInputControl object.
     */
    public static <T extends TextInputControl> T setMaxTextLength(T field, int max) {
        field.textProperty().addListener((observableValue, string, t1) -> {
            if (field.getText().length() > max) {
                field.setText(string);
            }
        });
        return field;
    }

    /**
     * Set field characters to integers only.
     *
     * @param field Field.
     *
     * @return Field object.
     * @param <T> Any TextInputControl object.
     */
    public static <T extends TextInputControl> T applyOnlyIntegersFilter(T field) {
        field.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                field.setText(newValue.replaceAll("\\D", ""));
            }
        });
        return field;
    }

    /**
     * Create label for text field by prompt.
     *
     * @param field Field.
     *
     * @return HBox with text field & label text.
     */
    public static HBox createLayoutWithLabel(TextField field) {
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(5.0);
        Text text = TextUtils.createText(field.getPromptText());

        hBox.getChildren().addAll(text, field);
        return hBox;
    }

}
