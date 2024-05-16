package fr.amu.iut.exercice7;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class CounterController implements Initializable {
    @FXML
    Label counterLabel;
    @FXML
    Button decrementButton = new Button();
    @FXML
    Button incrementButton = new Button();

    int counter = 0;

    public void increment() {
        ++counter;
        counterLabel.setText(counter+"");
    }

    public void decrement() {
        --counter;
        counterLabel.setText(counter+"");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Initializing CounterController...");
   }
}
