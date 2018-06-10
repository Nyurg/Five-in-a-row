package sample;

import javafx.geometry.Insets;
import javafx.scene.layout.Pane;

public class Cell extends Pane implements java.io.Serializable {
    //attributes
    private int cellLength;
    private Piece piece;

    //constructors
    public Cell() { }
    public Cell(int cellLength) {
        this.cellLength = cellLength;
    }

    //getters
    public int getCellLength() {
        return cellLength;
    }

    public boolean hasPiece() {
        return piece != null;
    }

    public Piece getPiece() {
        return piece;
    }

    //setters
    public void setPiece(Piece setPiece) {
        piece = setPiece;
    }
}
