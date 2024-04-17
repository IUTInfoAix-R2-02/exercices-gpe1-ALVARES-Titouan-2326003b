package fr.amu.iut.exercice6;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;

public class IHMPendu extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Jeu du pendu");
        primaryStage.setWidth(500);
        primaryStage.setHeight(550);

        Dico dico = new Dico();
        ArrayList<String> listeClavier1 = new ArrayList<String>(Arrays.asList("a", "e", "i", "o", "u", "y"));
        ArrayList<String> listeClavier2 = new ArrayList<String>(Arrays.asList("b", "c", "d", "f", "g", "h", "j", "k", "l", "m"));
        ArrayList<String> listeClavier3 = new ArrayList<String>(Arrays.asList("n", "p", "q", "r", "s", "t", "v", "w", "x", "z"));
        int nbVies = 0;
        String mot = dico.getMot();

        VBox vBox = new VBox();

        HBox affTop = new HBox();
        affTop.setAlignment(Pos.CENTER);
        VBox pendu = new VBox();
        pendu.setAlignment(Pos.CENTER);
        Label imgPendu = new Label();
        imgPendu.setGraphic(new ImageView("exercice6/pendu7.png"));
        Label vie = new Label("Nombre de vies : 7");
        String motRevele = "";
        for (int i = 0; i < mot.length(); ++i) motRevele += "*";
        Label motCache = new Label(motRevele);
        pendu.getChildren().addAll(imgPendu, vie, motCache);
        affTop.getChildren().add(pendu);

        VBox boutons = new VBox();
        boutons.setAlignment(Pos.CENTER);

        VBox clavier = new VBox();
        clavier.setAlignment(Pos.CENTER);

        HBox ligne1 = new HBox();
        ligne1.setAlignment(Pos.CENTER);
        for (int i = 0; i < listeClavier1.size(); ++i) ligne1.getChildren().add(new Button(listeClavier1.get(i)));

        HBox ligne2 = new HBox();
        ligne2.setAlignment(Pos.CENTER);
        for (int i = 0; i < listeClavier2.size(); ++i) ligne2.getChildren().add(new Button(listeClavier2.get(i)));

        HBox ligne3 = new HBox();
        ligne3.setAlignment(Pos.CENTER);
        for (int i = 0; i < listeClavier3.size(); ++i) ligne3.getChildren().add(new Button(listeClavier3.get(i)));

        Button rejouer = new Button("Rejouer");
        Rectangle formeBouton = new Rectangle(40.0, 20.0);
        formeBouton.setArcWidth(7.0);
        formeBouton.setArcHeight(7.0);
        rejouer.setShape(formeBouton);
        rejouer.setStyle("-fx-background-color: lightcyan ;" +
                "-fx-border-color: darkcyan ;" +
                "-fx-text-fill: orange ;");
        rejouer.setAlignment(Pos.CENTER);
        clavier.getChildren().addAll(ligne1, ligne2, ligne3);
        boutons.getChildren().addAll(clavier, rejouer);



        vBox.getChildren().addAll(affTop, clavier, boutons);
        VBox.setVgrow(affTop, Priority.ALWAYS);
        VBox.setMargin(affTop, new Insets(10.0));
        VBox.setMargin(vie, new Insets(10.0, 0.0, 10.0, 0.0));
        VBox.setMargin(motCache, new Insets(10.0, 0.0, 10.0, 0.0));
        VBox.setMargin(boutons, new Insets(5.0, 0.0, 10.0, 0.0));
        vBox.setStyle("-fx-background-color: lightcyan");
        Scene scene = new Scene(vBox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
