package fr.amu.iut.exercice5;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
public class Obstacle extends Rectangle{
    public Obstacle(double xPos, double yPos, double width, double height, Color color) {
        super(xPos, yPos, width, height);
        super.setFill(color);
    }
}
