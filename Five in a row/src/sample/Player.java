package sample;


import javafx.scene.paint.Color;

public class Player implements java.io.Serializable {
    //attributes
    private int playerNumber;
    private Color pieceColour;
    private int winCount = 0;

    //constructors
    public Player() { }
    public Player(int playerNumber, Color colour) {
        this.playerNumber = playerNumber;
        pieceColour = colour;
    }

    //getters
    public int getPlayerNumber() { return playerNumber; }
    public Color getPieceColour() { return pieceColour; }
    public int getWinCount() { return winCount; }

    //setters
    public void setColour(Color colour) { pieceColour = colour; }

    //methods
    public void winnerWinnerThisGuyHere() { winCount++; }


}
