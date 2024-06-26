package fr.amu.iut.exercice2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import java.util.Random;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TicTacToe extends Application {

    @Override
    public void start(Stage primaryStage) {
        // objet de Random pour choisir l'image à afficher
        Random random = new Random();
        // objet de GridPane servant de grille de jeu
        GridPane grille = new GridPane();
        grille.setGridLinesVisible(true);
        for (int i = 0; i<9; ++i) {
            switch (random.nextInt(3)) {
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

        // création de la scène avec la grille
        Scene scene = new Scene(grille);
        // ajout de la scène à Stage
        primaryStage.setScene(scene);
        // paramètrage du Stage
        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.setResizable(false);
        // affichage de la page
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);

    }
}

