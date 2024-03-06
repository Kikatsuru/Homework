package net.kikatsuru.homework.screens.authorization;

import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import net.kikatsuru.homework.Environment;
import net.kikatsuru.homework.themes.Theme;
import net.kikatsuru.homework.utils.elements.ButtonUtils;
import net.kikatsuru.homework.utils.elements.FieldUtils;
import net.kikatsuru.homework.utils.elements.FontUtils;
import net.kikatsuru.homework.utils.elements.TextUtils;

/**
 * Screen for user login/register.
 */
public class LoginRegisterScreen extends BorderPane {

    /**
     * Type for current form.
     */
    public enum FormType { LOGIN, REGISTER }

    /**
     * Current app theme.
     */
    private static final Theme THEME = Environment.theme;

    /**
     * Text for displaying errors.
     */
    private final Text errorText = TextUtils.createText(null, FontUtils.createFont(), Color.RED);

    /**
     * Screen controller.
     */
    private final LoginRegisterController controller;

    /**
     * Default screen constructor.
     */
    public LoginRegisterScreen() {
        this.controller = new LoginRegisterController(this);
        this.drawScreen(FormType.LOGIN);
    }

    /**
     * Display the screen.
     *
     * @param formType Type of form to display.
     */
    private void drawScreen(FormType formType) {
        VBox mainBox = new VBox();
        mainBox.spacingProperty().bind(mainBox.heightProperty().multiply(0.04));
        mainBox.maxWidthProperty().bind(super.widthProperty().multiply(0.5));
        mainBox.maxHeightProperty().bind(super.heightProperty().multiply(0.7));
        mainBox.prefWidthProperty().bind(super.widthProperty());
        mainBox.prefHeightProperty().bind(super.heightProperty());
        mainBox.setBackground(THEME.getPanelsBackground());
        mainBox.setAlignment(Pos.CENTER);

        this.errorText.wrappingWidthProperty().bind(mainBox.widthProperty().multiply(0.8));
        this.errorText.setTextAlignment(TextAlignment.CENTER);
        this.setErrorText(null);

        Font fieldFont = FontUtils.createFont();

        Text test = TextUtils.createText(formType == FormType.LOGIN ? "Авторизация" : "Регистрация",
                FontUtils.createFont(30), Color.WHITE);

        TextField login = FieldUtils.createTextField(null, "Логин", fieldFont);
        login.maxWidthProperty().bind(mainBox.widthProperty().multiply(0.5));
        login.minHeightProperty().bind(mainBox.heightProperty().multiply(0.1));
        FieldUtils.setMaxTextLength(login, 32);

        PasswordField password = FieldUtils.createPasswordField("Пароль", fieldFont);
        password.maxWidthProperty().bind(mainBox.widthProperty().multiply(0.5));
        password.minHeightProperty().bind(mainBox.heightProperty().multiply(0.1));

        PasswordField repeatPassword = FieldUtils.createPasswordField("Повтор пароля", fieldFont);
        repeatPassword.maxWidthProperty().bind(mainBox.widthProperty().multiply(0.5));
        repeatPassword.minHeightProperty().bind(mainBox.heightProperty().multiply(0.1));

        Button submit = ButtonUtils.createButton(formType == FormType.LOGIN ? "Войти" : "Зарегистрироваться",
                fieldFont);
        submit.maxWidthProperty().bind(mainBox.widthProperty().multiply(0.5));
        submit.minHeightProperty().bind(mainBox.heightProperty().multiply(0.1));
        submit.setOnMouseClicked((e) -> this.controller.submit(formType, login.getText(),
                password.getText(), repeatPassword.getText()));

        Text changeText = TextUtils.createText(formType == FormType.LOGIN ? "Регистрация" : "Вход", fieldFont,
                THEME.getButtonsColor());
        changeText.setCursor(Cursor.HAND);
        changeText.setOnMouseClicked((e) -> {
            this.drawScreen(formType == FormType.LOGIN ? FormType.REGISTER : FormType.LOGIN);
        });

        mainBox.getChildren().addAll(test, login, password, submit, changeText, this.errorText);

        if (formType == FormType.REGISTER) {
            mainBox.getChildren().add(3, repeatPassword);
        }
        super.setOnKeyPressed((e) -> {
            if (e.getCode() == KeyCode.ENTER) {
                this.controller.submit(formType, login.getText(), password.getText(), repeatPassword.getText());
            }
        });

        super.setCenter(mainBox);
    }

    /**
     * Set the error text.
     *
     * @param text Text.
     */
    public void setErrorText(String text) {
        this.errorText.setText(text);
    }

}
