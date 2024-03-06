package net.kikatsuru.homework.utils.elements;

import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import net.kikatsuru.homework.Environment;
import net.kikatsuru.homework.themes.Theme;
import net.kikatsuru.homework.utils.StyleUtils;

/**
 * Buttons manage class.
 */
public class ButtonUtils {
    /**
     * Current app theme.
     */
    private static final Theme THEME = Environment.theme;

    /**
     * Create button.
     *
     * @param text Button text.
     * @param font Button font.
     *
     * @return Button object.
     */
    public static Button createButton(String text, Font font) {
        return createButton(text, font, THEME.getButtonsBackground());
    }

    /**
     * Create button.
     *
     * @param text Button text.
     * @param font Button font.
     * @param background Button background.
     *
     * @return Button object.
     */
    public static Button createButton(String text, Font font, Background background) {
        Button button = new Button(text);
        button.setFont(font);
        button.setCursor(Cursor.HAND);
        button.setBackground(background);
        StyleUtils.setTextColor(button, Color.WHITE);
        return button;
    }

}
