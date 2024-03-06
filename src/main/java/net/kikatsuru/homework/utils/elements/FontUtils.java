package net.kikatsuru.homework.utils.elements;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * Fonts manage class.
 */
public class FontUtils {

    /**
     * Create default font.
     *
     * @return Font object.
     */
    public static Font createFont() {
        return createFont(15);
    }

    /**
     * Create font.
     *
     * @param size Font size.
     *
     * @return Font object.
     */
    public static Font createFont(double size) {
        return createFont("Inter", size);
    }

    /**
     * Create font.
     *
     * @param family Font family.
     * @param size Font size.
     *
     * @return Font object.
     */
    public static Font createFont(String family, double size) {
        return createFont(family, FontWeight.NORMAL, size);
    }

    /**
     * Create font.
     *
     * @param weight Font weight.
     * @param size Font size.
     *
     * @return Font object.
     */
    public static Font createFont(FontWeight weight, double size) {
        return Font.font("Inter", weight, size);
    }

    /**
     * Create font.
     *
     * @param family Font family.
     * @param fontWeight Font weight.
     * @param size Font size.
     *
     * @return Font object.
     */
    public static Font createFont(String family, FontWeight fontWeight, double size) {
        return Font.font(family, fontWeight, size);
    }

}
