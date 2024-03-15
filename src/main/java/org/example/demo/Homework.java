package org.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Homework extends Application {
    private static boolean authorized;
    private static Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        Homework.stage = stage;

        FXMLLoader loader = new FXMLLoader(Homework.class.getResource("auth.fxml"));
        Scene scene = new Scene(loader.load(), 800, 600);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static boolean isAuthorized() {
        return Homework.authorized;
    }

    public static void setAuthorized(boolean authorized) {
        Homework.authorized = authorized;
        FXMLLoader loader;

        if (authorized) {
            loader = new FXMLLoader(Homework.class.getResource("menu.fxml"));
        } else {
            loader = new FXMLLoader(Homework.class.getResource("auth.fxml"));
        }
        try {
            Scene scene = new Scene(loader.load(), 800, 600);
            stage.setScene(scene);
        } catch (Exception ignored) { }
    }
}
