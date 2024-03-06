package net.kikatsuru.homework;

import net.kikatsuru.homework.api.Api;
import net.kikatsuru.homework.api.ClientApi;
import net.kikatsuru.homework.themes.KikaTheme;
import net.kikatsuru.homework.themes.Theme;

/**
 * Class for store global variables.
 */
public class Environment {
    /**
     * True if Windows system.
     */
    public static final boolean IS_WINDOWS = System.getProperty("os.name").startsWith("Windows");

    /**
     * Path to user token file.
     */
    public static String TOKEN_PATH = IS_WINDOWS ? System.getenv("LOCALAPPDATA") + "/.mlkit/tk.json" :
            System.getProperty("user.home") + "/.mlkit/token.tk";

    /**
     * Default app width.
     */
    public static final double width = 800;

    /**
     * Default app height.
     */
    public static final double height = 600;

    /**
     * Theme with default value.
     */
    public static Theme theme = new KikaTheme();

    /**
     * App server(client) side api.
     */
    public static Api api = new ClientApi();

    /**
     * Username.
     */
    public static String name;

    /**
     * Token that can be used for api access.
     */
    public static String token;
}
