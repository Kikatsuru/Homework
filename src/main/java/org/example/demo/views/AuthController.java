package org.example.demo.views;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.example.demo.Homework;
import org.example.demo.data.user.User;

public class AuthController {
    private enum Screen {REGISTER, LOGIN}

    private Screen currentScreen = Screen.LOGIN;

    @FXML
    private Label headerText;
    @FXML
    private TextField loginField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button submitButton;
    @FXML
    private Label changeText;
    @FXML
    private Label errorText;

    @FXML
    private void onSubmit() {
        String login = this.loginField.getText();
        String password = this.passwordField.getText();

        if (login == null || password == null) {
            this.errorText.setText("Введите логин или пароль.");
            return;
        }

        if (this.currentScreen == Screen.LOGIN) {
            this.login(login, password);
        } else {
            this.register(login, password);
        }
    }

    private void login(String login, String password) {
        if (User.getService().findByLoginAndPassword(login, password) == null) {
            this.errorText.setText("Пользователя не существует.");
        }
        Homework.setAuthorized(true);
    }

    private void register(String login, String password) {
        try {
            User user = new User(login, password);
            User.getService().persist(user);
            Homework.setAuthorized(true);
        } catch (Exception ex) {
            this.errorText.setText("Пользователь уже существует.");
        }
    }

    @FXML
    private void onChange() {
        this.currentScreen = this.currentScreen == Screen.LOGIN ? Screen.REGISTER : Screen.LOGIN;

        if (this.currentScreen == Screen.LOGIN) {
            this.headerText.setText("Авторизация");
            this.submitButton.setText("Войти");
            this.changeText.setText("Регистрация");
        } else {
            this.headerText.setText("Регистрация");
            this.submitButton.setText("Зарегистрироваться");
            this.changeText.setText("Вход");
        }
    }
}
