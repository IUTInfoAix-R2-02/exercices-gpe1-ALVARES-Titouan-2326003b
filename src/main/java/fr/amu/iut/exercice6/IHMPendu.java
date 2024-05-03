package fr.amu.iut.exercice6;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;

public class IHMPendu extends Application {
    // création d'attributs afin de pouvoir les utiliser en dehors de la méthode start
    int nbVies;     // nombre de vies restantes du joueur à chaque instant de la partie
    int nbVictoires;    // nombre de parties gagnées depuis le dernier lancement de l'application
    int nbDefaites;     // nombre de parties perdues depuis le dernier lancement de l'application
    Label vie;      // Label affichant le nombre de vies restnates
    Label victoire; // Label affichant le nombre de victoires
    Label defaite;  // Label affichant le nombre de défaites
    Label imgPendu;     // Label affichant l'image du pendu et étant mis à jour au cours de la partie
    Label motCache;     // Label affichant le mot à trouver, au départ composé uniquement de *
    Label etatDeJeu;    // Label affichant les différents états du jeu
    StringBuilder mot;      // variable qui stock le mot à trouver
    StringBuilder motRevele;    // variable qui stock le mot affiché en remplaçant les lettres inconnues par *
    Dico dico;                  // variable de type Dico servant à récupérer les mots
    ArrayList<Button> boutonsUtilises;      // liste qui stock les boutons utilisés à chaque partie

    @Override
    public void start(Stage primaryStage) throws Exception {
        // paramétrage de la fenêtre
        primaryStage.setTitle("Jeu du pendu");
        primaryStage.setWidth(500);
        primaryStage.setHeight(622);

        // initialisation de boutonsUtilises
        boutonsUtilises = new ArrayList<>();
        // initilisation de dico
        dico = new Dico();
        // initialisation du mot à trouver
        mot = new StringBuilder(dico.getMot());
        // initialisation du nombre de vies à 7
        nbVies = 7;
        // initialisation du nombre de victoires
        nbVictoires = 0;
        // initialisation du nombre de défaites
        nbDefaites = 0;

        // listes qui stockent les lettres de chaque bouton par ligne du clavier
        ArrayList<String> listeClavier1 = new ArrayList<>(Arrays.asList("a", "e", "i", "o", "u", "y"));
        ArrayList<String> listeClavier2 = new ArrayList<>(Arrays.asList("b", "c", "d", "f", "g", "h", "j", "k", "l", "m"));
        ArrayList<String> listeClavier3 = new ArrayList<>(Arrays.asList("n", "p", "q", "r", "s", "t", "v", "w", "x", "z"));


        // initialisation du conteneur principal
        VBox vBox = new VBox();

        // initialisation du conteneur affichant le nombre de victoires, de défaites et l'état du jeu
        VBox affTop = new VBox();
        affTop.setAlignment(Pos.CENTER);

        // initialisation du label affichant le nombre de victoires, au départ à 0
        victoire = new Label("Nombre de victoires : 0");
        victoire.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 15;");

        // initialisation du label affichant le nombre de défaites, au départ à 0
        defaite = new Label("Nombre de défaites : 0");
        defaite.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 15;");

        // initialisation de etatDeJeu
        etatDeJeu = new Label("Bonne chance!");
        etatDeJeu.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 25;");

        // ajout des noeuds précédents au conteneur affTop
        affTop.getChildren().addAll(victoire, defaite, etatDeJeu);


        // initialisation du conteneur affichant le pendu, le mot et le nombre de vies
        VBox pendu = new VBox();
        pendu.setAlignment(Pos.CENTER);

        // initialisation de l'image du pendu, correspondant au départ au nombre de vies maximum
        imgPendu = new Label();
        imgPendu.setGraphic(new ImageView("exercice6/pendu7.png"));

        // initialisation du label affichant le nombre de vies restantes, au départ à 7
        vie = new Label("Nombre de vies : 7");
        vie.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 20;");

        // initialisation de l'affichage du mot, au départ composé uniquement de *
        motRevele = new StringBuilder(mot.length());
        for (int i = 0; i < mot.length(); ++i) motRevele.insert(0, "*");

        // initialisation du label affichant le mot
        motCache = new Label(motRevele.toString());
        motCache.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 30;");

        // ajout des noeuds précédents au conteneur pendu
        pendu.getChildren().addAll(imgPendu, vie, motCache);


        // initialisation du conteneur affichant les boutons
        VBox boutons = new VBox();
        boutons.setAlignment(Pos.CENTER);

        // initialisation du conteneur affichant la première ligne du clavier
        HBox ligne1 = new HBox();
        ligne1.setAlignment(Pos.CENTER);
        // chaque lettre est associée à un bouton de la première ligne du clavier
        for (String lettre : listeClavier1) {
            Button touche = new Button(lettre);
            // méthode attribuant un style et un eventHandler à chaque bouton
            initButton(touche);
            ligne1.getChildren().add(touche);
        }

        // initialisation du conteneur affichant la deuxième ligne du clavier
        HBox ligne2 = new HBox();
        ligne2.setAlignment(Pos.CENTER);
        // chaque lettre est associée à un bouton de la deuxième ligne du clavier
        for (String lettre : listeClavier2) {
            Button touche = new Button(lettre);
            // méthode attribuant un style et un eventHandler à chaque bouton
            initButton(touche);
            ligne2.getChildren().add(touche);
        }

        // initialisation du conteneur affichant la troisième ligne du clavier
        HBox ligne3 = new HBox();
        ligne3.setAlignment(Pos.CENTER);
        // chaque lettre est associée à un bouton de la troisième ligne du clavier
        for (String lettre : listeClavier3) {
            Button touche = new Button(lettre);
            // méthode attribuant un style et un eventHandler à chaque bouton
            initButton(touche);
            ligne3.getChildren().add(touche);
        }

        // initialisation d'un bouton rejouer
        Button rejouer = new Button("Rejouer");

        // attribution d'une forme de rectangle avec les coins arrondis
        Rectangle formeBouton = new Rectangle(3, 1);
        formeBouton.setArcWidth(1.5);
        formeBouton.setArcHeight(1.0);
        rejouer.setShape(formeBouton);
        rejouer.setStyle("-fx-background-color: lightcyan ;" +
                "-fx-border-color: darkcyan ;" +
                "-fx-border-width: 2;" +
                "-fx-text-fill: orange ;" +
                "-fx-font-weight: bold;" +
                "-fx-font-size: 15.0");
        rejouer.setAlignment(Pos.BOTTOM_CENTER);

        // attribution de l'évènement rejouerEvent au bouton rejouer quand il est cliqué
        rejouer.addEventHandler(MouseEvent.MOUSE_CLICKED, actionEvent -> rejouerEvent());

        // ajout des noeuds précédents au conteneur boutons
        boutons.getChildren().addAll(ligne1, ligne2, ligne3, rejouer);


        // ajout de pendu et boutons au conteneur principal
        vBox.getChildren().addAll(affTop, pendu, boutons);


        // la position des conteneurs affTop, pendu et boutons dépendra de la hauteur de la fenêtre
        VBox.setVgrow(affTop, Priority.ALWAYS);
        VBox.setVgrow(pendu, Priority.ALWAYS);
        VBox.setVgrow(boutons, Priority.ALWAYS);

        // ajout de marges au différents noeuds
        VBox.setMargin(affTop, new Insets(10.0, 0.0, 10.0, 0.0));
        VBox.setMargin(etatDeJeu, new Insets(5.0, 0.0, 5.0, 0.0));
        VBox.setMargin(vie, new Insets(10.0, 0.0, 10.0, 0.0));
        VBox.setMargin(motCache, new Insets(5.0, 0.0, 10.0, 0.0));
        VBox.setMargin(rejouer, new Insets(15.0, 0.0, 10.0, 0.0));
        VBox.setMargin(boutons, new Insets(15.0, 0.0, 10.0, 0.0));

        // le fond de la page est cyan clair
        vBox.setStyle("-fx-background-color: lightcyan");

        // initialisation de la scène avec le conteneur principal
        Scene scene = new Scene(vBox);
        // ajout d'un gestionnaire d'évènement à scene quand une touche du clavier physique est appuyée
        scene.setOnKeyPressed((KeyEvent event) -> {
            String carac = event.getText();
            if (listeClavier1.contains(carac)) {            // si la touche fait partie de la première ligne du clavier affiché, alors lettreEvent est appelé avec en paramètre la touche de la première ligne du clavier virtuel correspondant à celle de event
                for (Node bouton1 : ligne1.getChildren()) if (bouton1 instanceof Button && ((Button) bouton1).getText().equals(carac)) {
                    lettreEvent((Button) bouton1);
                    break;      // si la touche a été trouvée, alors on arrête la recherche
                }
            } else if (listeClavier2.contains(carac)) {     // si la touche fait partie de la deuxième ligne du clavier affiché, alors lettreEvent est appelé avec en paramètre la touche de la deuxième ligne du clavier virtuel correspondant à celle de event
                for (Node bouton2 : ligne2.getChildren()) if (bouton2 instanceof Button && ((Button) bouton2).getText().equals(carac)) {
                    lettreEvent((Button) bouton2);
                    break;      // si la touche a été trouvée, alors on arrête la recherche
                }
            } else if (listeClavier3.contains(carac)) {     // si la touche fait partie de la troisième ligne du clavier affiché, alors lettreEvent est appelé avec en paramètre la touche de la troisième ligne du clavier virtuel correspondant à celle de event
                for (Node bouton3 : ligne3.getChildren()) if (bouton3 instanceof Button && ((Button) bouton3).getText().equals(carac)) {
                    lettreEvent((Button) bouton3);
                    break;      // si la touche a été trouvée, alors on arrête la recherche
                }
            } else if (event.getCode() == KeyCode.BACK_SPACE) rejouerEvent();       // si la touche pressée correspond à backspace, alors rejouerEvent est appelé et une nouvelle partie commence
            else if (event.getCode() == KeyCode.DELETE) primaryStage.close();       // si la touche pressée correspond à la touche suppr, alors la fenêtre est fermée et le jeu se termine
        });

        // ajout de la scène
        primaryStage.setScene(scene);
        // affichage du stage
        primaryStage.show();
    }

    // méthode attribuant un style et un eventHandler au bouton passé en paramètre (bouton:Button)
    public void initButton(Button bouton) {
        // attribution d'une forme de carré avec les coins arrondis
        Rectangle formeBouton = new Rectangle(2.5, 2.5);
        formeBouton.setArcHeight(1.0);
        formeBouton.setArcWidth(1.0);
        bouton.setShape(formeBouton);
        bouton.setMinSize(48.0, 48.0);

        // méthode attribuant un style au bouton
        initStyleButton(bouton);

        // attribution de l'évènement lettreEvent au bouton quand il est cliqué
        bouton.addEventHandler(MouseEvent.MOUSE_CLICKED, actionEvent -> lettreEvent(bouton));
    }

    // méthode attribuant un style au bouton passé en paramètre (bouton:Button)
    public void initStyleButton(Button bouton) {
        bouton.setStyle("-fx-background-color: lightcyan ;" +
                "-fx-border-color: orange ;" +
                "-fx-text-fill: darkcyan ;" +
                "-fx-font-weight: bold;" +
                "-fx-font-size: 20.0");
    }

    // méthode gérant les évènements provoqués par les touches du clavier
    public void lettreEvent(Button bouton) {
        // si le nombre de vie est à 0, ou que le bouton a déjà été utilisé, ou que le mot a déjà été trouvé, alors la méthode ne fait rien et se termine
        if (nbVies < 1
                || boutonsUtilises.contains(bouton)
                || !motRevele.toString().contains("*")) return;

        // si le bouton cliqué est celui d'une lettre présente dans le mot, alors toutes ses occurrences sont révélées à l'affichage
        if (mot.toString().contains(bouton.getText())) {
            // initialisation d'une variable contenant la première occurrence du caractère dans le mot
            int index = mot.indexOf(bouton.getText());

            // tant qu'il y a des occurrences, la boucle continue de tourner
            while (index > -1) {
                // on remplace l'occurrence du caractère à l'indice index du mot affiché
                motRevele.setCharAt(index, bouton.getText().charAt(0));
                // si index correspond au dernier caractère du mot, alors on sait que c'est la dernière occurrence donc on sort de la boucle
                if (index == mot.length()-1) break;
                // on met à jour index en lui attribuant l'indice de la prochaine occurrence du caractère
                index = mot.indexOf(bouton.getText(), index+1);
            }

            // on met à jour l'affichage
            motCache.setText(motRevele.toString());

            // on affiche un message indiquant au joueur qu'il a gagné si l'affichage du mot est complet puis on incrémente le compteur de victoires et on met à jour son affichage
            if (!motRevele.toString().contains("*")) {
                etatDeJeu.setText("Gagné!");
                imgPendu.setGraphic(new ImageView("exercice6/penduWin.png"));
                victoire.setText("Nombre de victoires : " + ++nbVictoires);
            }
        }

        // si le bouton cliqué n'est pas celui d'une lettre présente dans le mot, alors le joueur perd une vie
        else {
            // décrémentaion du nombre de vie, si le nombre de vies atteint 0, alors un affichage spécial apparaît puis on incrémente le compteur de défaite et on met à jour son affichage
            if (--nbVies == 0) {
                vie.setText("Perdu");
                etatDeJeu.setText("Le mot était " + mot);
                defaite.setText("Nombre de défaites : " + ++nbDefaites);
            }

            // sinon on met à jour l'affichage du nombre de vies
            else vie.setText("Nombre de vies : " + nbVies);

            // mise à jour de l'image du pendu
            imgPendu.setGraphic(new ImageView("exercice6/pendu" + nbVies + ".png"));
        }

        // mise à jour de l'affichage du bouton maintenant qu'il a été utilisé
        bouton.setStyle("-fx-background-color: lightcyan ;" +
                "-fx-border-color: rgba(255,165,0,0.5) ;" +
                "-fx-text-fill: rgba(0,139,139,0.5) ;" +
                "-fx-font-weight: bold;" +
                "-fx-font-size: 20.0");

        // ajout du bouton à la liste des boutons utilisés durant la partie
        boutonsUtilises.add(bouton);
    }

    // méthode gérant l'évènement provoqué par le bouton rejouer
    public void rejouerEvent() {
        if (nbVies > 0 && motRevele.toString().contains("*")) defaite.setText("Nombre de défaites : " + ++nbDefaites);      // si le joueur abandonne la partie, alors on incrémente le compteur de défaite et on met à jour son affichage
        nbVies = 7;                             // le nombre de vies est réinitialisé
        vie.setText("Nombre de vies : 7");      // l'affichage du nombre de vies est réinitialisé
        imgPendu.setGraphic(new ImageView("exercice6/pendu7.png"));     // l'affichage du pendu est réinitialisé
        etatDeJeu.setText("Le mot précédent était " + mot);         // affichage spécial : affichage du mot de la partie venant de se terminer
        motRevele.delete(0, mot.length());      // le stringbuilder contenant le mot affiché est vidé
        mot.delete(0, mot.length());    // le stringbuilder contenant le mot à trouver est vidé
        mot.append(dico.getMot());      // puis re-rempli avec un autre mot pour la partie suivante
        for (int i = 0; i < mot.length(); ++i) motRevele.insert(0, "*");    // le stringbuilder contenant le mot affiché est re-rempli avec des *
        motCache.setText(motRevele.toString());     // l'affichage du mot est mis à jour
        for (Button boutonUtilise : boutonsUtilises) initStyleButton(boutonUtilise);    // le style des boutons utilisés lors de la partie est réinitialisé
        boutonsUtilises.clear();        // la liste des boutons utilisés est vidée pour la partie suivante
    }

    public static void main(String[] args) {
        launch(args);       // lancement de l'application
    }
}
