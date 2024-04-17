package fr.amu.iut.exercice2;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import java.util.Random;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TicTacToe extends Application {

    @Override
    public void start(Stage primaryStage) {
        // conteneur principal
        VBox vBox = new VBox();

        // objet de Random pour choisir l'image à afficher
        Random random = new Random();
        // objet de GridPane servant de grille de jeu
        GridPane grille = new GridPane();
        grille.setGridLinesVisible(true);
        grille.setAlignment(Pos.CENTER);
        for (int i = 0; i<9; ++i) {
            int nombre = random.nextInt(3);
            switch (nombre) {
                // selon la valeur de i on ajoute l'image dans une cellule différente
                case 0:
                    grille.add(new Label("", new ImageView("exercice2/Croix.png")), i/3, i%3);
                    break;
                case 1:
                    grille.add(new Label("", new ImageView("exercice2/Rond.png")), i/3, i%3);
                    break;
                case 2:
                    grille.add(new Label("", new ImageView("exercice2/Vide.png")), i/3, i%3);
                    break;
            }
        }

        // ajout de la grille au conteneur principal
        vBox.getChildren().add(grille);

        // création de la scène avec le conteneur principal
        Scene scene = new Scene(vBox);
        // ajout de la scène à Stage
        primaryStage.setScene(scene);
        // paramètrage du Stage
        primaryStage.setHeight(178);
        primaryStage.setWidth(133);
        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.setResizable(false);
        // affichage de la page
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);

    }
}

