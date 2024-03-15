package org.example.demo.views;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.example.demo.Homework;
import org.example.demo.data.city.City;

public class CityController {
    private City city;
    @FXML
    private TextField nameField;
    @FXML
    private TextField countryCodeField;
    @FXML
    private TextField districtField;
    @FXML
    private TextField populationField;
    private Runnable onBackClicked;

    @FXML
    private void onBackClicked() {
        this.onBackClicked.run();
    }

    @FXML
    private void onDeleteClicked() {
        City.getService().delete(this.city);
        this.onBackClicked.run();
    }

    @FXML
    private void onSaveClicked() {
        this.city.setName(this.nameField.getText());
        this.city.setDistrict(this.districtField.getText());
        this.city.setPopulation(Integer.parseInt(this.populationField.getText()));
        this.city.setCountryCode(this.countryCodeField.getText());
        City.getService().merge(this.city);
    }

    public void setOnBackClicked(Runnable onBackClicked) {
        this.onBackClicked = onBackClicked;
    }

    public void setCity(City city) {
        this.city = city;
        this.nameField.setText(city.getName());
        this.countryCodeField.setText(city.getCountryCode());
        this.districtField.setText(city.getDistrict());
        this.populationField.setText(String.valueOf(city.getPopulation()));
    }

}
