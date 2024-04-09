package fr.amu.iut.exercice1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class FenetreLogiciel extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Conteneur de la page
        VBox vbox = new VBox();

        // Conteneur en haut de page pour le menu
        HBox topControls = new HBox();
        // Menu File et ses sous-items
        Menu menuFile = new Menu("File");
        MenuItem itemNew = new MenuItem("New");
        MenuItem itemOpen = new MenuItem("Open");
        MenuItem itemSave = new MenuItem("Save");
        MenuItem itemClose = new MenuItem("Close");
        menuFile.getItems().addAll(itemNew, itemOpen, itemSave, itemClose);
        // Menu Edit et ses sous-items
        Menu menuEdit = new Menu("Edit");
        MenuItem itemCut = new MenuItem("Cut");
        MenuItem itemCopy = new MenuItem("Copy");
        MenuItem itemPaste = new MenuItem("Paste");
        menuEdit.getItems().addAll(itemCut, itemCopy, itemPaste);
        // Menu Help
        Menu menuHelp = new Menu("Help");
        // Ajout du menu complet en haut de page
        MenuBar menu = new MenuBar(menuFile, menuEdit, menuHelp);
        topControls.getChildren().add(menu);

        // Séparateur horizontal entre le menu et le reste de la page
        Separator hSep1 = new Separator();

        // Conteneur des boutons et du formulaire
        HBox centerControls = new HBox();
        VBox.setVgrow(centerControls, Priority.ALWAYS);

        // Conteneur des boutons à droite
        VBox buttons = new VBox();
        buttons.setAlignment(Pos.CENTER);
        Label boutons = new Label("Boutons :");
        Button button1 = new Button("Bouton 1");
        Button button2 = new Button("Bouton 2");
        Button button3 = new Button("Bouton 3");
        buttons.getChildren().addAll(boutons, button1, button2, button3);
        buttons.setSpacing(10);

        // Séparateur vertical des boutons et du formulaire
        Separator vSep = new Separator(Orientation.VERTICAL);

        // Conteneur du formulaire et des boutons Submit et Cancel
        VBox formulaire = new VBox();
        HBox.setHgrow(formulaire, Priority.ALWAYS);
        formulaire.setAlignment(Pos.CENTER);

        // Formulaire
        GridPane gridpane = new GridPane();
        gridpane.setAlignment(Pos.CENTER);
        // Case "Name:"
        Label nameLabel = new Label("Name:");
        GridPane.setConstraints(nameLabel, 0, 0);
        // Case "Email:"
        Label emailLabel = new Label("Email:");
        GridPane.setConstraints(emailLabel, 0, 1);
        // Case "Password:"
        Label passwordLabel = new Label("Password:");
        GridPane.setConstraints(passwordLabel, 0, 2);

        // Textfield de name
        TextField nameText = new TextField();
        GridPane.setConstraints(nameText, 1, 0);
        // Textfield de email
        TextField emailText = new TextField();
        GridPane.setConstraints(emailText, 1, 1);
        // textfield de password
        TextField passwordText = new TextField();
        GridPane.setConstraints(passwordText, 1, 2);

        // Remplissage du formulaire
        gridpane.getChildren().addAll(nameLabel, emailLabel, passwordLabel, nameText, emailText, passwordText);
        // Ajout des margin des éléments qui sont dans le formulaire
        for (int i=0; i<gridpane.getChildren().size(); ++i) GridPane.setMargin(gridpane.getChildren().get(i), new Insets(5));

        // Conteneur des boutons Submit et Cancel
        HBox formButtons = new HBox();
        formButtons.setAlignment(Pos.CENTER);
        Button submit = new Button("Submit");
        Button cancel = new Button("Cancel");
        formButtons.getChildren().addAll(submit, cancel);
        formButtons.setSpacing(10);

        // Remplissage du conteneur du formulaire et des boutons Submit et Cancel
        formulaire.getChildren().addAll(gridpane, formButtons);
        formulaire.setSpacing(5);

        // Remplissage du conteneur des boutons et du formulaire
        centerControls.getChildren().addAll(buttons, vSep, formulaire);

        // Séparateur horizontal entre le bas de page et le reste
        Separator hSep2 = new Separator();

        // Conteneur du bas de page
        HBox bottomControls = new HBox();
        bottomControls.setAlignment(Pos.CENTER);
        bottomControls.getChildren().add(new Label("Ceci est un label de bas de page"));

        // Remplissage de la page
        vbox.getChildren().addAll(topControls, hSep1, centerControls, hSep2, bottomControls);

        // Paramétrage de la scène
        Scene scene = new Scene(vbox);
        primaryStage.setScene(scene);
        primaryStage.setWidth(800);
        primaryStage.setHeight(600);
        primaryStage.setTitle("Premier exemple manipulant les conteneurs");

        // Affichage de la page
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);

    }
}