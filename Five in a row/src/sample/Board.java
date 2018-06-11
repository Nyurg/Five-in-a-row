package sample;

import javafx.scene.layout.GridPane;

public class Board extends GridPane implements java.io.Serializable {
    //attributes
    private Cell[][] boardSize;
    private Player playerTurn;
    private int turnNumber = 1;

    //constructors
    public Board() { }
    public Board(Cell[][] boardSize, Player playerTurn) {
        this.boardSize = boardSize;
        this.playerTurn = playerTurn;
    }

    //getters
    public Cell[][] getBoardSize() {
        return boardSize;
    }

    public Player getPlayerTurn() {
        return playerTurn;
    }

    public int getTurnNumber() {
        return turnNumber;
    }

    //setters
    public void setPlayerTurn(Player playerTurn) {
        this.playerTurn = playerTurn;
    }

    public void setTurnNumber(int turn) {
        this.turnNumber = turn;
    }

    //methods

    public boolean determineFiveInRow(int xPos, int yPos) {
        int count = 0;
        //vertical determination
        for (int a = 0; a < 5; a++) {
            if (yPos + a < boardSize.length && boardSize[yPos + a][xPos].hasPlayerClaimed() && playerTurn.getImage() == boardSize[yPos + a][xPos].getPlayerlaimed().getImage()) {
                count++;
            } else {
                a = 5;
                for (int b = 1; b < 5; b++) {
                    if (yPos - b >= 0 && boardSize[yPos - b][xPos].hasPlayerClaimed() && playerTurn.getImage() == boardSize[yPos - b][xPos].getPlayerlaimed().getImage()) {
                        count++;
                    } else {
                        b = 5;

                    }
                }
            }
        }
        if (count == 5) {
            System.out.println("5 in a row detected.");
            return true;
        }

        //horizontal determination
        count = 0;
        for (int a = 0; a < 5; a++) {
            if (xPos + a < boardSize[yPos].length && boardSize[yPos][xPos + a].hasPlayerClaimed() && playerTurn.getImage() == boardSize[yPos][xPos + a].getPlayerlaimed().getImage()) {
                count++;
            } else {
                a = 5;
                for (int b = 1; b < 5; b++) {
                    if (xPos - b >= 0 && boardSize[yPos][xPos - b].hasPlayerClaimed() && playerTurn.getImage() == boardSize[yPos][xPos - b].getPlayerlaimed().getImage()) {
                        count++;
                    } else {
                        b = 5;
                    }
                }
            }
        }
        if (count == 5) {
            System.out.println("5 in a row detected.");
            return true;
        }

        //negative diagonal determination
        count = 0;
        for (int a = 0; a < 5; a++) {
            if (xPos + a < boardSize[yPos].length && yPos + a < boardSize.length && boardSize[yPos + a][xPos + a].hasPlayerClaimed() && playerTurn.getImage() == boardSize[yPos + a][xPos + a].getPlayerlaimed().getImage()) {
                count++;
            } else {
                a = 5;
                for (int b = 1; b < 5; b++) {
                    if (xPos - b >= 0 && yPos - b > 0 && boardSize[yPos - b][xPos - b].hasPlayerClaimed() && playerTurn.getImage() == boardSize[yPos - b][xPos - b].getPlayerlaimed().getImage()) {
                        count++;
                    } else {
                        b = 5;
                    }
                }
            }
        }
        if (count == 5) {
            System.out.println("5 in a row detected.");
            return true;
        }

        //positive diagonal determination
        count = 0;
        for (int a = 0; a < 5; a++) {
            if (xPos + a < boardSize[yPos].length && yPos - a > 0 && boardSize[yPos - a][xPos + a].hasPlayerClaimed() && playerTurn.getImage() == boardSize[yPos - a][xPos + a].getPlayerlaimed().getImage()) {
                count++;
            } else {
                a = 5;
                for (int b = 1; b < 5; b++) {
                    if (xPos - b >= 0 && yPos + b < boardSize.length && boardSize[yPos + b][xPos - b].hasPlayerClaimed() && playerTurn.getImage() == boardSize[yPos + b][xPos - b].getPlayerlaimed().getImage()) {
                        count++;
                    } else {
                        b = 5;
                    }
                }
            }
        }
        if (count == 5) {
            System.out.println("5 in a row detected.");
            return true;
        }

        return false;
    }
}
