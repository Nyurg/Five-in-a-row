package sample;

import javafx.scene.layout.Pane;

public class Cell extends Pane implements java.io.Serializable {
    //attributes
    private int cellLength;
    private Player playerClaimed;

    //constructors
    public Cell() { }
    public Cell(int cellLength) {
        this.cellLength = cellLength;
    }

    //getters
    public int getCellLength() {
        return cellLength;
    }

    public boolean hasPlayerClaimed() {
        return playerClaimed != null;
    }

    public Player getPlayerlaimed() {
        return playerClaimed;
    }

    //setters
    public void setPlayerClaimed(Player playerClaimed) {
        this.playerClaimed = playerClaimed;
    }
}
