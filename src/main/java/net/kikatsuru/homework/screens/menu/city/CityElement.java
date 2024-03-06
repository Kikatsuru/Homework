package net.kikatsuru.homework.screens.menu.city;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
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
import net.kikatsuru.homework.data.country.city.City;
import net.kikatsuru.homework.nodes.ListNode;
import net.kikatsuru.homework.screens.menu.AbstractMenuElement;
import net.kikatsuru.homework.utils.elements.ButtonUtils;
import net.kikatsuru.homework.utils.elements.FieldUtils;
import net.kikatsuru.homework.utils.elements.FontUtils;
import net.kikatsuru.homework.utils.elements.TextUtils;

import java.util.LinkedList;
import java.util.List;

public class CityElement extends AbstractMenuElement {
    /**
     * API.
     */
    private static final Api API = Environment.api;

    /**
     * City list.
     */
    private final List<City> cities = new LinkedList<>();

    /**
     * Default constructor.
     */
    public CityElement() {
        super("Города");
        this.draw();
    }

    /**
     * Draw element.
     */
    protected void draw() {
        Button button = ButtonUtils.createButton("Создать город", FontUtils.createFont());
        button.setOnMouseClicked(event -> this.drawCityEditor(new City()));
        super.setTop(button);

        if (this.cities.isEmpty()) {
            this.cities.addAll(API.getCityList(0, -1));
        }

        ListNode listNode = new ListNode();
        for (City city : this.cities) {
            listNode.addListElement(city.getName(), event -> this.drawCityEditor(city));
        }

        listNode.minWidthProperty().bind(super.widthProperty().subtract(40));
        listNode.maxWidthProperty().bind(super.widthProperty().subtract(40));
        listNode.maxHeightProperty().bind(super.heightProperty().divide(1.2));
        super.setCenter(listNode);
    }

    /**
     * Draw element editor.
     *
     * @param city City to draw.
     */
    private void drawCityEditor(City city) {
        super.setTop(null);

        VBox vBox = new VBox();
        BorderPane controls = new BorderPane();
        Font font = FontUtils.createFont();

        TextField code = FieldUtils.createTextField(city.getCountryCode(), "Код страны");
        FieldUtils.setMaxTextLength(code, 3);
        if (city.getCountryCode() != null) {
            code.setDisable(true);
        }
        TextField name = FieldUtils.createTextField(city.getName(), "Название");
        FieldUtils.setMaxTextLength(name, 35);
        TextField district = FieldUtils.createTextField(city.getDistrict(), "Округ");
        FieldUtils.setMaxTextLength(district, 20);
        TextField population = FieldUtils.createTextField(String.valueOf(city.getPopulation()), "Население");
        FieldUtils.applyOnlyIntegersFilter(population);

        FlowPane fields = new FlowPane();
        fields.setPadding(new Insets(20));
        fields.setHgap(5.0);
        fields.setVgap(5.0);
        fields.getChildren().addAll(
                FieldUtils.createLayoutWithLabel(name),
                FieldUtils.createLayoutWithLabel(code),
                FieldUtils.createLayoutWithLabel(district),
                FieldUtils.createLayoutWithLabel(population)
        );

        Text text = TextUtils.createText("Ошибка! Заполните все поля.", font, Color.RED);
        text.setVisible(false);

        HBox rightButtons = new HBox();
        rightButtons.setSpacing(5.0);
        Button saveButton = ButtonUtils.createButton("Сохранить", font);
        saveButton.setOnMouseClicked(event -> {
            city
                .setName(name.getText())
                .setDistrict(district.getText())
                .setPopulation(Integer.parseInt(population.getText()))
                .setCountryCode(code.getText());

            try {
                API.updateCity(city);
                if (!this.cities.contains(city)) {
                    this.cities.add(0, city);
                }
                text.setVisible(false);
            } catch (Exception e) {
                text.setVisible(true);
            }
        });

        Button deleteButton = ButtonUtils.createButton("Удалить", font);
        deleteButton.setOnMouseClicked(event -> {
            API.deleteCity(city);
            this.cities.remove(city);
            this.draw();
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
