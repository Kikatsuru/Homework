package net.kikatsuru.homework.themes;

import javafx.scene.layout.Background;
import javafx.scene.paint.Color;

/**
 * Themes interface.
 */
public interface Theme {

    /**
     * Color for all panels.
     *
     * @return Color.
     */
    Color getPanelsColor();

    /**
     * Color for all buttons.
     *
     * @return Color.
     */
    Color getButtonsColor();

    /**
     * Color for another interface elements.
     *
     * @return Color.
     */
    Color getGlobalColor();

    /**
     * Rounded background for all panels.
     * IDK why I add this
     *
     * @return Background.
     */
    Background getPanelsBackgroundRounded();

    /**
     * Background for all panels
     *
     * @return Background.
     */
    Background getPanelsBackground();

    /**
     * Background for all buttons.
     *
     * @return Background.
     */
    Background getButtonsBackground();

    /**
     * Background for another interface elements.
     *
     * @return Background.
     */
    Background getGlobalBackground();

    /**
     * Rounded background.
     * IDK why I added it.
     *
     * @return Background.
     */
    Background getGlobalBackgroundRounded();
}
