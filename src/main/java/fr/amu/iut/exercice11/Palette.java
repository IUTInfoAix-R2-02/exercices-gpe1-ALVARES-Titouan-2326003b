package fr.amu.iut.exercice11;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

@SuppressWarnings("Duplicates")
public class Palette extends Application {

    private int nbVert = 0;
    private int nbRouge = 0;
    private int nbBleu = 0;

    private Label texteDuHaut;

    private Button vert;
    private Button rouge;
    private Button bleu;

    private BorderPane root;
    private Pane panneau;
    private HBox boutons;

    private Label texteDuBas;

    private IntegerProperty nbFois;
    private StringProperty message;
    private StringProperty couleurPanneau;


    @Override
    public void start(Stage primaryStage) {
        new Palette();

        root = new BorderPane();

        texteDuHaut = new Label();
        texteDuHaut.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        BorderPane.setAlignment(texteDuHaut, Pos.CENTER);

        panneau = new Pane();
        panneau.setPrefSize(400, 200);

        VBox bas = new VBox();
        boutons = new HBox(10);
        boutons.setAlignment(Pos.CENTER);
        boutons.setPadding(new Insets(10, 5, 10, 5));
        texteDuBas = new Label();
        bas.setAlignment(Pos.CENTER_RIGHT);
        bas.getChildren().addAll(boutons, texteDuBas);

        vert = new Button("Vert");
        rouge = new Button("Rouge");
        bleu = new Button("Bleu");

        /* VOTRE CODE ICI */

        createBindings();

        vert.setOnAction(event -> {
            couleurPanneau.set("#008000");
            nbFois.set(++nbVert);
            message.set("Vert");
        });
        rouge.setOnAction(event -> {
            couleurPanneau.set("#ff0000");
            nbFois.set(++nbRouge);
            message.set("Rouge");
        });
        bleu.setOnAction(event -> {
            couleurPanneau.set("#0000ff");
            nbFois.set(++nbBleu);
            message.set("Bleu");
        });

        /* VOTRE CODE ICI */

        boutons.getChildren().addAll(vert, rouge, bleu);

        root.setCenter(panneau);
        root.setTop(texteDuHaut);
        root.setBottom(bas);

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public Palette() {
        nbFois = new SimpleIntegerProperty();
        message = new SimpleStringProperty();
        couleurPanneau = new SimpleStringProperty("#000000");
    }

    private void createBindings() {
        panneau.styleProperty().bind(Bindings.concat("-fx-background-color:", couleurPanneau));
        BooleanProperty pasEncoreDeClic = new SimpleBooleanProperty();
        pasEncoreDeClic.bind(nbFois.isEqualTo(0));
        texteDuHaut.textProperty().bind(Bindings.when(pasEncoreDeClic).then("Cliquez sur un bouton").otherwise(Bindings.concat(message, " choisi ", nbFois, " fois")));
        texteDuBas.textProperty().bind(Bindings.when(pasEncoreDeClic).then("").otherwise(Bindings.concat("Le ", message, " est une jolie couleur !")));
        texteDuBas.styleProperty().bind(Bindings.when(pasEncoreDeClic).then("").otherwise(Bindings.concat("-fx-text-fill:", couleurPanneau)));
    }
}

