package fr.amu.iut.exercice15;

import javafx.beans.InvalidationListener;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableBooleanValue;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class LoginControl extends GridPane {
    @FXML
    private TextField userId;

    @FXML
    private PasswordField pwd;

    @FXML
    private Button okBtn;

    @FXML
    private Button cancelBtn;

    public void initialize() { createBindings(); }

    private void createBindings() {
        IntegerProperty idLength = new SimpleIntegerProperty(0);
        idLength.bind(userId.lengthProperty());
        IntegerProperty pwdLength = new SimpleIntegerProperty(0);
        pwdLength.bind(pwd.lengthProperty());
        StringProperty pwdText = new SimpleStringProperty("");
        pwdText.bind(pwd.textProperty());

        pwd.editableProperty().bind(Bindings.when(idLength.lessThan(6)).then(false).otherwise(true));

        cancelBtn.disableProperty().bind(Bindings.when(idLength.isEqualTo(0).and(pwdLength.isEqualTo(0))).then(true).otherwise(false));

        BooleanBinding pwdConditions = new BooleanBinding() {
            { this.bind(pwd.textProperty()); }
            @Override
            protected boolean computeValue() {
                boolean cap = false;
                boolean number = false;
                for (char c : pwd.getText().toCharArray()) {
                    if (Character.isUpperCase(c)) cap = true;
                    if (Character.isDigit(c)) number = true;
                    if (cap && number) return true;
                }
                return false;
            }
        };
        okBtn.disableProperty().bind(Bindings.when(pwdLength.greaterThanOrEqualTo(8).and(pwdConditions)).then(false).otherwise(true));
    }

    @FXML
    private void okClicked() {
        System.out.print(userId.getText() + " ");
        for (char c : pwd.getText().toCharArray()) {
            System.out.print("*");
        }
        System.out.println();
    }

    @FXML
    private void cancelClicked() {
        userId.setText("");
        pwd.setText("");
    }
}