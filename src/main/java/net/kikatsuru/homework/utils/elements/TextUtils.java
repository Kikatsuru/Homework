package net.kikatsuru.homework.utils.elements;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Text manage class.
 */
public class TextUtils {

    /**
     * Create text.
     *
     * @param content Text.
     *
     * @return Text object.
     */
    public static Text createText(String content) {
        return createText(content, FontUtils.createFont());
    }

    /**
     * Create text.
     *
     * @param content Text.
     * @param font Text font.
     *
     * @return Text object.
     */
    public static Text createText(String content, Font font) {
        return createText(content, font, Color.WHITE);
    }

    /**
     * Create text.
     *
     * @param content Text.
     * @param font Text font.
     * @param color Text color.
     *
     * @return Text object.
     */
    public static Text createText(String content, Font font, Color color) {
        Text text = new Text(content);
        text.setFont(font);
        text.setFill(color);
        return text;
    }

    /**
     * Truncate string to size.
     *
     * @param string String.
     * @param size Size.
     *
     * @return Truncated string.
     */
    public static String truncateString(String string, int size) {
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
