package net.kikatsuru.homework.screens.authorization;

import jakarta.persistence.Entity;
import net.kikatsuru.homework.Environment;
import net.kikatsuru.homework.Homework;
import net.kikatsuru.homework.api.Api;
import net.kikatsuru.homework.data.user.User;

import java.io.IOException;
import java.util.regex.Pattern;

/**
 * Controller for login/register screen.
 */
public class LoginRegisterController {
    /**
     * Login pattern.
     */
    private static final Pattern LOGIN_PATTERN = Pattern.compile("^[A-Za-z0-9-_]+$");

    /**
     * Login min length.
     */
    private static final int MIN_LOGIN_LENGTH = 4;

    /**
     * Login max length.
     */
    private static final int MAX_LOGIN_LENGTH = 32;

    /**
     * Password min length.
     */
    private static final int MIN_PASSWORD_LENGTH = 6;

    /**
     * Login/Register screen instance.
     */
    private final LoginRegisterScreen screen;

    /**
     * Main controller constructor.
     *
     * @param screen Login/Register screen instance.
     */
    public LoginRegisterController(LoginRegisterScreen screen) {
        this.screen = screen;
    }

    /**
     * Submit user login/register form.
     *
     * @param formType Form type.
     * @param login User login.
     * @param password User password.
     * @param repeatPassword User repeated password.
     */
    public void submit(LoginRegisterScreen.FormType formType, String login, String password, String repeatPassword) {
        try {
            if (formType == LoginRegisterScreen.FormType.REGISTER) {
                this.register(login, password, repeatPassword);
            } else {
                this.login(login, password);
            }
        } catch (IOException ex) {
            this.screen.setErrorText("Ошибка соединения.");
        }
    }

    /**
     * Submit user register form.
     *
     * @param login User login.
     * @param password User password.
     * @param repeatPassword User repeated password.
     *
     * @throws IOException Something wrong with the network.
     */
    public void register(String login, String password, String repeatPassword) throws IOException {
        if (login == null || login.length() < MIN_LOGIN_LENGTH || login.length() > MAX_LOGIN_LENGTH
                || !LOGIN_PATTERN.matcher(login).matches()) {
            this.screen.setErrorText("Логин должен быть длиной от 4 до 32 символов и состоять из латинских букв и цифр");
            return;
        } else if (password == null || password.length() < MIN_PASSWORD_LENGTH) {
            this.screen.setErrorText("Длина пароля должна быть больше 6 символов");
            return;
        } else if (!password.equals(repeatPassword)) {
            this.screen.setErrorText("Пароли не совпадают.");
            return;
        }

        String token = Environment.api.register(login, password);
        if (token == null) {
            this.screen.setErrorText("Пользователь уже существует.");
            return;
        }

        Homework.getInstance().saveUserProfile(login, token);
    }

    /**
     * Submit user login form.
     *
     * @param login User login.
     * @param password User password.
     *
     * @throws IOException Something wrong with the network.
     */
    public void login(String login, String password) throws IOException {
        if (login == null) {
            this.screen.setErrorText("Введите логин");
            return;
        } else if (password == null) {
            this.screen.setErrorText("Введите пароль");
            return;
        }

        String token = Environment.api.login(login, password);
        if (token == null) {
            this.screen.setErrorText("Неверный логин или пароль.");
            return;
        }

        Homework.getInstance().saveUserProfile(login, token);
    }

}
