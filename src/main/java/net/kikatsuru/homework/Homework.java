package net.kikatsuru.homework;

import javafx.application.Application;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import net.kikatsuru.homework.screens.authorization.LoginRegisterScreen;
import net.kikatsuru.homework.screens.menu.city.CityElement;
import net.kikatsuru.homework.screens.menu.country.CountryElement;
import net.kikatsuru.homework.screens.menu.MenuScreen;
import net.kikatsuru.homework.screens.menu.language.CountryLanguageElement;
import net.kikatsuru.homework.themes.Theme;
import org.jetbrains.annotations.NotNull;

import java.io.*;

/**
 * Main application class.
 */
public class Homework extends Application {
    /**
     * Current app theme.
     */
    private static final Theme THEME = Environment.theme;

    /**
     * File that contains user token.
     */
    private static final File TOKEN_FILE = new File(Environment.TOKEN_PATH);

    /**
     * Class instance.
     */
    private static Homework instance;

    /**
     * Current stage.
     */
    private static Stage stage;

    /**
     * Default app constructor that specifies instance.
     */
    public Homework() {
        if (Homework.instance != null) {
            throw new RuntimeException(":?");
        }
        Homework.instance = this;
    }

    /**
     * FX default start method.
     *
     * @param stage Current app screen.
     */
    @Override
    public void start(@NotNull Stage stage) {
        Homework.stage = stage;

        InputStream imageStream = Homework.class.getResourceAsStream("/assets/icons/logo.png");
        if (imageStream != null) {
            stage.getIcons().add(new Image(imageStream));
        }

        stage.setResizable(false);
        stage.setMinHeight(Environment.height);
        stage.setMinWidth(Environment.width);
        stage.setTitle("Homework");

        if (Homework.TOKEN_FILE.exists()) {
            this.loadUserProfile();
        } else {
            this.setScreen(new LoginRegisterScreen());
        }
        stage.show();
    }

    /**
     * Creates new user profile.
     */
    public void saveUserProfile(String name, String token) {
        try {
            if (!Homework.TOKEN_FILE.exists()) {
                Homework.TOKEN_FILE.getParentFile().mkdirs();
                Homework.TOKEN_FILE.createNewFile();
            }

            try (BufferedWriter inputStream = new BufferedWriter(new FileWriter(Homework.TOKEN_FILE))) {
                inputStream.write(String.format("%s|%s", name, token));
                Environment.name = name;
                Environment.token = token;

                MenuScreen listScreen = new MenuScreen()
                        .addListElement(new CountryElement())
                        .addListElement(new CityElement())
                        .addListElement(new CountryLanguageElement());
                this.setScreen(listScreen);
            }
        } catch (IOException ex) {
            LoginRegisterScreen screen = new LoginRegisterScreen();
            screen.setErrorText("Что-то пошло не так. Войдите снова.");
            this.setScreen(screen);
        }
    }

    /**
     * Launch with user profile.
     */
    public void loadUserProfile() {
        try (BufferedReader inputStream = new BufferedReader(new FileReader(Homework.TOKEN_FILE))) {
            String[] values = inputStream.readLine().split("\\|");
            Environment.name = values[0];
            Environment.token = values[1];

            if (!Environment.api.isTokenValid(Environment.token)) {
                this.deleteUserProfile();
                return;
            }

            MenuScreen listScreen = new MenuScreen()
                    .addListElement(new CountryElement())
                    .addListElement(new CityElement())
                    .addListElement(new CountryLanguageElement());
            this.setScreen(listScreen);
        } catch (IOException ex) {
            this.deleteUserProfile();
        }
    }

    /**
     *  Delete user profile & return to login screen.
     */
    public void deleteUserProfile() {
        if (Homework.TOKEN_FILE.exists()) {
            Homework.TOKEN_FILE.delete();
        }
        this.setScreen(new LoginRegisterScreen());
    }

    /**
     * Change current screen.
     *
     * @param screen Screen.
     */
    public void setScreen(Pane screen) {
        Homework.stage.setScene(new Scene(screen, Environment.width, Environment.height));
        Homework.stage.getScene().addEventFilter(ContextMenuEvent.CONTEXT_MENU_REQUESTED, Event::consume);
        screen.setBackground(THEME.getGlobalBackground());
    }

    /**
     * App instance.
     *
     * @return Homework object.
     */
    public static Homework getInstance() {
        return Homework.instance;
    }
}
