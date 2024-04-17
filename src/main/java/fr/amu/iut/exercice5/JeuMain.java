package fr.amu.iut.exercice5;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

public class JeuMain extends Application {

    private Scene scene;
    private BorderPane root;
    public static ArrayList<Obstacle> obstacles;
    private boolean collision = false;

    @Override
    public void start(Stage primaryStage) {

        root = new BorderPane();

        //Acteurs du jeu
        Personnage pacman = new Pacman();
        Personnage fantome = new Fantome();

        // on positionne le fantôme dans le coin en bas à droite
        fantome.setLayoutX(48 * 10);
        fantome.setLayoutY(64 * 10);
        //obstacles
        obstacles = new ArrayList<>();
        obstacles.add(new Obstacle(10 * 20, 11 * 20, 4 * 20, 10 * 20, Color.DARKRED));

        //panneau du jeu
        Pane jeu = new Pane();
        jeu.setPrefSize(640, 480);
        jeu.getChildren().add(pacman);
        jeu.getChildren().add(fantome);
        jeu.getChildren().addAll(obstacles);
        root.setCenter(jeu);

        //on construit une scene 640 * 480 pixels
        scene = new Scene(root);

        //Gestion du déplacement du personnage
        deplacer(pacman, fantome);

        primaryStage.setTitle("... Pac Man ...");

        primaryStage.setScene(scene);
        primaryStage.setHeight(695);
        primaryStage.setWidth(502);
        primaryStage.show();
    }

    /**
     * Permet de gérer les événements de type clavier, pression des touches
     * pour le j1(up,down, right, left), pour le j2( z,q,s,d)
     *
     * @param j1
     * @param j2
     */
    private void deplacer(Personnage j1, Personnage j2) {
        scene.setOnKeyPressed((KeyEvent event) -> {
            if (!collision) {
                switch (event.getCode()) {
                    case LEFT:
                        j1.deplacerAGauche();
                        break;
                    case RIGHT:
                        j1.deplacerADroite(scene.getWidth());
                        break;
                    case UP:
                        j1.deplacerEnHaut();
                        break;
                    case DOWN:
                        j1.deplacerEnBas(scene.getHeight());
                        break;
                    case Q:
                        j2.deplacerAGauche();
                        break;
                    case D:
                        j2.deplacerADroite(scene.getWidth());
                        break;
                    case Z:
                        j2.deplacerEnHaut();
                        break;
                    case S:
                        j2.deplacerEnBas(scene.getHeight());
                        break;
                }
                if (j1.estEnCollision(j2)) collision = true;
            } else if (event.getCode() == KeyCode.ENTER) System.exit(0);
        });
    }


}
