package net.kikatsuru.homework.screens.menu.country;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import net.kikatsuru.homework.Environment;
import net.kikatsuru.homework.api.Api;
import net.kikatsuru.homework.data.country.Country;
import net.kikatsuru.homework.screens.menu.AbstractMenuElement;
import net.kikatsuru.homework.nodes.ListNode;
import net.kikatsuru.homework.themes.Theme;
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
public class CountryElement extends AbstractMenuElement {
    /**
     * THEME.
     */
    private static final Theme THEME =Environment.theme;

    /**
     * API.
     */
    private static final Api API = Environment.api;

    /**
     * Country list.
     */
    private final List<Country> countries = new LinkedList<>();

    /**
     * Default constructor.
     */
    public CountryElement() {
        super("Страны");
        this.draw();
    }

    /**
     * Draw element.
     */
    public void draw() {
        Button button = ButtonUtils.createButton("Создать страну", FontUtils.createFont());
        button.setOnMouseClicked(event -> this.drawCountryEditor(new Country()));
        super.setTop(button);

        if (this.countries.isEmpty()) {
            this.countries.addAll(API.getCountryList(0, -1));
        }

        ListNode listNode = new ListNode();
        for (Country country : this.countries) {
            listNode.addListElement(country.getName(), event -> this.drawCountryEditor(country));
        }

        listNode.minWidthProperty().bind(super.widthProperty().subtract(40));
        listNode.maxWidthProperty().bind(super.widthProperty().subtract(40));
        listNode.maxHeightProperty().bind(super.heightProperty().divide(1.2));
        super.setCenter(listNode);
    }

    /**
     * Draw element editor.
     *
     * @param country Country to draw.
     */
    private void drawCountryEditor(Country country) {
        super.setTop(null);

        VBox vBox = new VBox();
        BorderPane controls = new BorderPane();
        Font font = FontUtils.createFont();

        TextField code = FieldUtils.createTextField(country.getCode(), "Код страны", font);
        FieldUtils.setMaxTextLength(code, 3);
        if (country.getCode() != null) {
            code.setDisable(true);
        }
        TextField secondCode = FieldUtils.createTextField(country.getCode2(), "Второй код страны", font);
        TextField name = FieldUtils.createTextField(country.getName(), "Название страны", font);
        FieldUtils.setMaxTextLength(name, 52);
        TextField continent = FieldUtils.createTextField(country.getContinent(), "Континент", font);
        TextField region = FieldUtils.createTextField(country.getRegion(), "Регион", font);
        FieldUtils.setMaxTextLength(region, 26);
        TextField surface = FieldUtils.createTextField(country.getSurfaceArea().toString(), "Размер", font);
        FieldUtils.applyOnlyIntegersFilter(surface);
        TextField indep = FieldUtils.createTextField(String.valueOf(country.getIndependenceYear()), "Какой-то год", font);
        FieldUtils.applyOnlyIntegersFilter(surface);
        TextField population = FieldUtils.createTextField(String.valueOf(country.getPopulation()), "Население", font);
        FieldUtils.applyOnlyIntegersFilter(surface);
        TextField lifeExpect = FieldUtils.createTextField(String.valueOf(country.getLifeExpectancy()), "Продолжительность жизни", font);
        FieldUtils.applyOnlyIntegersFilter(surface);
        TextField gnp = FieldUtils.createTextField(String.valueOf(country.getGnp()), "Доход", font);
        FieldUtils.applyOnlyIntegersFilter(gnp);
        TextField gnpOld = FieldUtils.createTextField(String.valueOf(country.getGnpOld()), "Прошлый доход", font);
        FieldUtils.applyOnlyIntegersFilter(gnpOld);
        TextField localName = FieldUtils.createTextField(country.getLocalName(), "Второе название", font);
        FieldUtils.setMaxTextLength(localName, 45);
        TextField government = FieldUtils.createTextField(country.getGovernmentForm(), "Форма правительства", font);
        FieldUtils.setMaxTextLength(government, 45);
        TextField head = FieldUtils.createTextField(country.getHeadOfState(), "Глава", font);
        FieldUtils.setMaxTextLength(head, 60);
        TextField capital = FieldUtils.createTextField(String.valueOf(country.getCapital()), "Капитал", font);
        FieldUtils.applyOnlyIntegersFilter(capital);

        FlowPane fields = new FlowPane();
        fields.setPadding(new Insets(20));
        fields.setHgap(5.0);
        fields.setVgap(5.0);
        fields.getChildren().addAll(
                FieldUtils.createLayoutWithLabel(code),
                FieldUtils.createLayoutWithLabel(secondCode),
                FieldUtils.createLayoutWithLabel(name),
                FieldUtils.createLayoutWithLabel(continent),
                FieldUtils.createLayoutWithLabel(region),
                FieldUtils.createLayoutWithLabel(surface),
                FieldUtils.createLayoutWithLabel(indep),
                FieldUtils.createLayoutWithLabel(population),
                FieldUtils.createLayoutWithLabel(lifeExpect),
                FieldUtils.createLayoutWithLabel(gnp),
                FieldUtils.createLayoutWithLabel(gnpOld),
                FieldUtils.createLayoutWithLabel(localName),
                FieldUtils.createLayoutWithLabel(government),
                FieldUtils.createLayoutWithLabel(head),
                FieldUtils.createLayoutWithLabel(capital)
        );

        HBox rightButtons = new HBox();
        rightButtons.setSpacing(5.0);
        Button deleteButton = ButtonUtils.createButton("Удалить", font);
        deleteButton.setOnMouseClicked(event -> {
            API.deleteCountry(country);
            this.countries.remove(country);
            this.draw();
        });

        Text text = TextUtils.createText("Ошибка! Заполните все поля.", font, Color.RED);
        text.setVisible(false);
        Button saveButton = ButtonUtils.createButton("Сохранить", font);
        saveButton.setOnMouseClicked(event -> {
            country
                    .setCode(code.getText())
                    .setCode2(secondCode.getText())
                    .setName(name.getText())
                    .setContinent(continent.getText())
                    .setRegion(region.getText())
                    .setSurfaceArea(BigDecimal.valueOf(Double.parseDouble(surface.getText())))
                    .setIndependenceYear(Integer.parseInt(indep.getText()))
                    .setPopulation(Integer.parseInt(population.getText()))
                    .setLifeExpectancy(BigDecimal.valueOf(Double.parseDouble(lifeExpect.getText())))
                    .setGnp(BigDecimal.valueOf(Double.parseDouble(gnp.getText())))
                    .setGnpOld(BigDecimal.valueOf(Double.parseDouble(gnpOld.getText())))
                    .setLocalName(localName.getText())
                    .setGovernmentForm(government.getText())
                    .setHeadOfState(head.getText())
                    .setCapital(Integer.parseInt(capital.getText()));
            try {
                API.updateCountry(country);
                if (!this.countries.contains(country)) {
                    this.countries.add(0, country);
                }
                text.setVisible(false);
            } catch (Exception e) {
                text.setVisible(true);
            }
        });
        rightButtons.getChildren().addAll(saveButton, deleteButton);

        Button goBack = ButtonUtils.createButton("Вернуться", font);
        goBack.setOnMouseClicked(event -> this.draw());
        controls.setRight(rightButtons);
        controls.setLeft(goBack);

        vBox.getChildren().addAll(controls, fields, text);
        super.setCenter(vBox);
    }

}
