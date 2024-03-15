package org.example.demo.views;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import org.example.demo.Homework;
import org.example.demo.data.city.City;
import org.example.demo.nodes.ListNode;

import java.io.IOException;
import java.util.List;

public class MenuController {
    @FXML
    private BorderPane mainPain;

    @FXML
    private void openCity() {
        List<City> cityList = City.getService().findAll();
        ListNode list = new ListNode();

        list.setOnCreateListener(e -> openCityEditor(new City()));
        cityList.forEach(city -> list.addListElement(city.getName(), e -> openCityEditor(city)));

        this.mainPain.setCenter(list);
    }

    private void openCityEditor(City city) {
        try {
            FXMLLoader loader = new FXMLLoader(MenuController.class.getResource("city.fxml"));
            BorderPane pane = loader.load();
            CityController controller = loader.getController();
            controller.setCity(city);
            controller.setOnBackClicked(this::openCity);
            this.mainPain.setCenter(pane);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @FXML
    private void logout() {
        Homework.setAuthorized(false);
    }

}
