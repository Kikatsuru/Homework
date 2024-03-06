package net.kikatsuru.homework.themes;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

/**
 * Kika theme implementation.
 */
public class KikaTheme implements Theme {
    private final double CORNER_RADIUS = 30;

    private final Color PANELS_COLOR = Color.rgb(33, 33, 33, 1.0);

    private final Color BUTTONS_COLOR = Color.rgb(106, 82, 218, 1.0);

    private final Color GLOBAL_COLOR = Color.rgb(24, 24, 24, 1.0);

    private final Background BUTTON_BACKGROUND =
            new Background(new BackgroundFill(this.BUTTONS_COLOR, new CornerRadii(this.CORNER_RADIUS), Insets.EMPTY));

    private final Background PANELS_BACKGROUND =
            new Background(new BackgroundFill(this.PANELS_COLOR, CornerRadii.EMPTY, Insets.EMPTY));

    private final Background PANELS_BACKGROUND_ROUNDED =
            new Background(new BackgroundFill(this.PANELS_COLOR, new CornerRadii(this.CORNER_RADIUS), Insets.EMPTY));

    private final Background BACKGROUND =
            new Background(new BackgroundFill(this.GLOBAL_COLOR, CornerRadii.EMPTY, Insets.EMPTY));

    private final Background BACKGROUND_ROUNDED =
            new Background(new BackgroundFill(this.GLOBAL_COLOR, new CornerRadii(this.CORNER_RADIUS), Insets.EMPTY));

    /**
     * Color for all panels.
     *
     * @return Color.
     */
    @Override
    public Color getPanelsColor() {
        return this.PANELS_COLOR;
    }

    /**
     * Color for all buttons.
     *
     * @return Color.
     */
    @Override
    public Color getButtonsColor() {
        return this.BUTTONS_COLOR;
    }

    /**
     * Color for another interface elements.
     *
     * @return Color.
     */
    @Override
    public Color getGlobalColor() {
        return this.GLOBAL_COLOR;
    }

    /**
     * Rounded background for all panels.
     *
     * @return Background.
     */
    @Override
    public Background getPanelsBackground() {
        return this.PANELS_BACKGROUND;
    }

    /**
     * Background for all panels
     *
     * @return Background.
     */
    @Override
    public Background getPanelsBackgroundRounded() {
        return PANELS_BACKGROUND_ROUNDED;
    }

    /**
     * Background for all buttons.
     *
     * @return Background.
     */
    @Override
    public Background getButtonsBackground() {
        return this.BUTTON_BACKGROUND;
    }

    /**
     * Background for another interface elements.
     *
     * @return Background.
     */
    @Override
    public Background getGlobalBackground() {
        return this.BACKGROUND;
    }

    /**
     * Rounded background.
     *
     * @return Background.
     */
    @Override
    public Background getGlobalBackgroundRounded() {
        return this.BACKGROUND_ROUNDED;
    }
}
