package fr.amu.iut.exercice4;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Palette extends Application {

    private int nbVert = 0;
    private int nbRouge = 0;
    private int nbBleu = 0;

    private Button vert;
    private Button rouge;
    private Button bleu;

    private BorderPane root;
    private Label label;
    private Pane panneau;
    private HBox bas;

    @Override
    public void start(Stage primaryStage) throws Exception {
        root = new BorderPane();
        HBox top = new HBox();
        top.setAlignment(Pos.CENTER);
        label = new Label();
        label.setFont(new Font(20));
        top.getChildren().add(label);
        panneau = new Pane();
        panneau.setPrefSize(400, 200);
        bas = new HBox();
        bas.setSpacing(10);

        vert = new Button("Vert");
        rouge = new Button("Rouge");
        bleu = new Button("Bleu");

        bas.setAlignment(Pos.CENTER);
        bas.getChildren().addAll(vert, rouge, bleu);

        vert.addEventHandler(MouseEvent.MOUSE_CLICKED, actionEvent -> {
            ++nbVert;
            if (nbVert == 3) panneau.setStyle("-fx-background-color: #24a49d;");
            else panneau.setStyle("-fx-background-color: green");
            label.setText("Vert choisi " + nbVert + " fois");
        });

        rouge.addEventHandler(MouseEvent.MOUSE_CLICKED, actionEvent -> {
            ++nbRouge;
            panneau.setStyle("-fx-background-color: red;");
            label.setText("Rouge choisi " + nbRouge + " fois");
        });

        bleu.addEventHandler(MouseEvent.MOUSE_CLICKED, actionEvent -> {
            ++nbBleu;
            panneau.setStyle("-fx-background-color: blue;");
            label.setText("Bleu choisi " + nbBleu + " fois");
        });

        root.setTop(top);
        root.setCenter(panneau);
        root.setBottom(bas);
        BorderPane.setMargin(bas, new Insets(10));

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setHeight(400);
        primaryStage.setWidth(600);
        primaryStage.show();
    }
}

