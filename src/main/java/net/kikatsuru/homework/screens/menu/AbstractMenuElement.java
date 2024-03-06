package net.kikatsuru.homework.screens.menu;

import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import net.kikatsuru.homework.Environment;
import net.kikatsuru.homework.themes.Theme;

/**
 * Class for menu elements.
 */
public abstract class AbstractMenuElement extends BorderPane {
    /**
     * Current theme.
     */
    private static final Theme THEME = Environment.theme;

    /**
     * Element title.
     */
    private final String title;

    /**
     * Default constructor.
     *
     * @param title Element ttile.
     */
    public AbstractMenuElement(String title) {
        super.setBackground(THEME.getPanelsBackground());
        super.setPadding(new Insets(20));
        this.title = title;
    }

    /**
     * Get title.
     *
     * @return Title.
     */
    public String getTitle()  {
        return this.title;
    }

}
