package fr.amu.iut.exercice5;

import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Iterator;

class Personnage extends Group {
    protected final static double LARGEUR_MOITIE_PERSONNAGE = 10;
    protected final static double LARGEUR_PERSONNAGE = LARGEUR_MOITIE_PERSONNAGE * 2;
    private final Circle corps;
    private String direction;

    public Personnage(String direction, Color couleurContour, Color couleurRemplissage) {
        this.direction = direction;
        corps = new Circle(10, 10, LARGEUR_MOITIE_PERSONNAGE, couleurContour);
        corps.setFill(couleurRemplissage);
        getChildren().add(corps);
    }

    public void deplacerAGauche() {
        //    ****
        //   *    *
        //  *---   *
        //   *    *
        //    ****

        //déplacement <----
        if (getLayoutX() >= LARGEUR_PERSONNAGE) {
            double coordonneesX = getLayoutX();
            setLayoutX(getLayoutX() - LARGEUR_PERSONNAGE);
            if (collisionObstacle()) setLayoutX(coordonneesX);
        }
        if (!direction.equals("gauche")) {
            direction = "gauche";
        }
    }

    public void deplacerADroite(double largeurJeu) {
        //    ****
        //   *    *
        //  *   ---*
        //   *    *
        //    ****
        //déplacement ---->
        if (getLayoutX() < largeurJeu - LARGEUR_PERSONNAGE*2) {
            double coordonneesX = getLayoutX();
            setLayoutX(getLayoutX() + LARGEUR_PERSONNAGE);
            if (collisionObstacle()) setLayoutX(coordonneesX);
        }
        if (!direction.equals("droite")) {
            direction = "droite";
        }
        collisionObstacle();
    }

    public void deplacerEnBas(double hauteurJeu) {
        //    *****
        //   *     *
        //  *   |   *
        //   *  |  *
        //    *****
        if (getLayoutY() < hauteurJeu - LARGEUR_PERSONNAGE*2) {
            double coordonneesY = getLayoutY();
            setLayoutY(getLayoutY() + LARGEUR_PERSONNAGE);
            if (collisionObstacle()) setLayoutY(coordonneesY);
        }
        if (!direction.equals("bas")) direction = "bas";
    }

    public void deplacerEnHaut() {
        //    *****
        //   *  |  *
        //  *   |   *
        //   *     *
        //    *****
        if (getLayoutY() >= LARGEUR_PERSONNAGE) {
            double coordonneesY = getLayoutY();
            setLayoutY(getLayoutY() - LARGEUR_PERSONNAGE);
            if (collisionObstacle()) setLayoutY(coordonneesY);
        }
        if (!direction.equals("haut")) direction = "haut";
    }

    boolean estEnCollision(Personnage autrePersonnage) {
        return getBoundsInParent().contains(autrePersonnage.getBoundsInParent())
                || autrePersonnage.getBoundsInParent().contains(getBoundsInParent());
    }

    public boolean collisionObstacle() {
        Iterator<Obstacle> it = JeuMain.obstacles.iterator();
        Bounds coordonneesPersonnage = getBoundsInParent();
        while(it.hasNext()) {
            Bounds coordonneesObstcale = it.next().getBoundsInParent();
            if (coordonneesObstcale.getMinX() <= coordonneesPersonnage.getMinX()
            && coordonneesObstcale.getMaxX() >= coordonneesPersonnage.getMaxX()
            && coordonneesObstcale.getMinY() <= coordonneesPersonnage.getMinY()
            && coordonneesObstcale.getMaxY() >= coordonneesPersonnage.getMaxY()) {
                return true;
            }
        }
        return false;
    }
}
