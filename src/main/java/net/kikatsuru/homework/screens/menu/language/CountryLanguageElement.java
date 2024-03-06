package net.kikatsuru.homework.screens.menu.language;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import net.kikatsuru.homework.Environment;
import net.kikatsuru.homework.api.Api;
import net.kikatsuru.homework.data.country.language.CountryLanguage;
import net.kikatsuru.homework.data.country.language.columns.CountryLanguageId;
import net.kikatsuru.homework.nodes.ListNode;
import net.kikatsuru.homework.screens.menu.AbstractMenuElement;
import net.kikatsuru.homework.utils.elements.ButtonUtils;
import net.kikatsuru.homework.utils.elements.FieldUtils;
import net.kikatsuru.homework.utils.elements.FontUtils;
import net.kikatsuru.homework.utils.elements.TextUtils;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

/**
 * Menu element implementation.
 */
public class CountryLanguageElement extends AbstractMenuElement {
    /**
     * API.
     */
    private static final Api API = Environment.api;

    /**
     * Languages list.
     */
    private final List<CountryLanguage> languages = new LinkedList<>();

    /**
     * Default constructor.
     */
    public CountryLanguageElement() {
        super("Языки");
        this.draw();
    }

    /**
     * Draw element.
     */
    protected void draw() {
        Button button = ButtonUtils.createButton("Создать язык", FontUtils.createFont());
        button.setOnMouseClicked(event -> this.drawLanguageEditor(new CountryLanguage()));
        super.setTop(button);

        if (this.languages.isEmpty()) {
            this.languages.addAll(CountryLanguage.getService().findAll());
        }

        ListNode listNode = new ListNode();
        for (CountryLanguage language : this.languages) {
            listNode.addListElement(language.getCountryLanguageId().getLanguage(), event -> this.drawLanguageEditor(language));
        }

        listNode.minWidthProperty().bind(super.widthProperty().subtract(40));
        listNode.maxWidthProperty().bind(super.widthProperty().subtract(40));
        listNode.maxHeightProperty().bind(super.heightProperty().divide(1.2));
        super.setCenter(listNode);
    }

    /**
     * Draw element editor.
     *
     * @param language Language to draw.
     */
    private void drawLanguageEditor(CountryLanguage language) {
        super.setTop(null);

        VBox vBox = new VBox();
        BorderPane controls = new BorderPane();
        Font font = FontUtils.createFont();

        CountryLanguageId id = language.getCountryLanguageId();
        TextField code = FieldUtils.createTextField(id == null ? null : id.getCountryCode(), "Код страны");
        TextField lang = FieldUtils.createTextField(id == null ? null : id.getLanguage(), "Язык");
        if (id != null) {
            code.setDisable(true);
            lang.setDisable(true);
        }
        CheckBox checkBox = new CheckBox("Официальный");
        checkBox.setSelected(language.isOfficial());
        TextField percent = FieldUtils.createTextField(language.getPercentage().toString(), "Процент");

        FlowPane fields = new FlowPane();
        fields.setPadding(new Insets(20));
        fields.setHgap(5.0);
        fields.setVgap(5.0);
        fields.getChildren().addAll(
                FieldUtils.createLayoutWithLabel(code),
                FieldUtils.createLayoutWithLabel(lang),
                checkBox,
                FieldUtils.createLayoutWithLabel(percent)
        );

        Text text = TextUtils.createText("Ошибка! Заполните все поля.", font, Color.RED);
        text.setVisible(false);

        Button saveButton = ButtonUtils.createButton("Сохранить", font);
        saveButton.setOnMouseClicked(event -> {
            language
                    .setCountryLanguageId(code.getText(), lang.getText())
                    .setPercentage(BigDecimal.valueOf(Double.parseDouble(percent.getText())))
                    .setIsOfficial(checkBox.isSelected());

            try {
                API.updateLanguage(language);
                if (!this.languages.contains(language)) {
                    this.languages.add(0, language);
                }
                text.setVisible(false);
            } catch (Exception e) {
                text.setVisible(true);
            }
        });

        Button deleteButton = ButtonUtils.createButton("Удалить", font);
        deleteButton.setOnMouseClicked(event -> {
            API.deleteLanguage(language);
            this.languages.remove(language);
            this.draw();
        });

        HBox rightButtons = new HBox();
        rightButtons.setSpacing(5.0);
        rightButtons.getChildren().addAll(saveButton,  deleteButton);

        Button goBack = ButtonUtils.createButton("Вернуться", font);
        goBack.setOnMouseClicked(event -> this.draw());

        controls.setRight(rightButtons);
        controls.setLeft(goBack);
        vBox.getChildren().addAll(controls, fields, text);
        super.setCenter(vBox);
    }

}
