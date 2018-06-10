package sample;

import javafx.scene.paint.Color;

public class Piece implements java.io.Serializable {
    //attributes
    private Color color;

    public Piece() { }
    public Piece(Color color) {
        this.color = color;
    }

    //getters
    public Color getColor() {
        return color;
    }


}
