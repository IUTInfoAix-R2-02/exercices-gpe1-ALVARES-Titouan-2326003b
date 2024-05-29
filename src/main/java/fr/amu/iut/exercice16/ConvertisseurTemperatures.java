package fr.amu.iut.exercice16;

import javafx.application.Application;
import javafx.beans.binding.DoubleBinding;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ConvertisseurTemperatures extends Application {
    private Slider celsius;

    private Slider fahrenheit;

    private TextField fieldCelsius;

    private TextField fieldFahrenheit;

    @Override
    public void start(Stage primaryStage) {
        VBox panneauCelsius = new VBox(30);
        panneauCelsius.setAlignment(Pos.CENTER);
        VBox panneauFahrenheit = new VBox(30);
        panneauFahrenheit.setAlignment(Pos.CENTER);

        Label labelCelsius = new Label("°C");
        labelCelsius.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 20");

        celsius = new Slider(0, 100, 0);
        celsius.setOrientation(Orientation.VERTICAL);
        celsius.setShowTickMarks(true);
        celsius.setShowTickLabels(true);
        celsius.setMajorTickUnit(10);
        celsius.setPrefHeight(800);

        fieldCelsius = new TextField("0,00");
        fieldCelsius.setMaxWidth(60);

        panneauCelsius.getChildren().addAll(labelCelsius, celsius, fieldCelsius);


        Label labelFahrenheit = new Label("°F");
        labelFahrenheit.setAlignment(Pos.CENTER);
        labelFahrenheit.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 20");

        fahrenheit = new Slider(0, 212, 32);
        fahrenheit.setOrientation(Orientation.VERTICAL);
        fahrenheit.setShowTickMarks(true);
        fahrenheit.setShowTickLabels(true);
        fahrenheit.setMajorTickUnit(10);
        fahrenheit.setPrefHeight(800);

        fieldFahrenheit = new TextField("32,00");
        fieldFahrenheit.setMaxWidth(60);

        panneauFahrenheit.getChildren().addAll(labelFahrenheit, fahrenheit, fieldFahrenheit);

        HBox root = new HBox(30, panneauCelsius, panneauFahrenheit);
        root.setPadding(new Insets(20));

        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        createBindings();
    }

    private void createBindings() {
        DoubleBinding celsiusToFahrenheit = new DoubleBinding() {
            { this.bind(celsius.valueProperty()); }
            @Override
            protected double computeValue() {
                return (celsius.getValue()*9)/5+32;
            }
        };

        DoubleBinding fahrenheitToCelsius = new DoubleBinding() {
            { this.bind(fahrenheit.valueProperty()); }
            @Override
            protected double computeValue() {
                return ((fahrenheit.getValue()-32)*5)/9 ;
            }
        };

        fahrenheit.valueProperty().bind(celsiusToFahrenheit);
    }

}