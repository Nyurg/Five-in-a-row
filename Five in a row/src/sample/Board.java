package sample;

import javafx.scene.layout.GridPane;

import java.io.*;
import java.util.Scanner;

public class Board extends GridPane implements java.io.Serializable {
    //attributes
    private Cell[][] boardSize;
    private Player playerTurn;
    private int turnNumber = 0;
    private int bestOf;

    //constructors
    public Board() { }
    public Board(Cell[][] boardSize, Player playerTurn, int bestOf) {
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
            if (yPos + a < boardSize.length && boardSize[yPos + a][xPos].hasPiece() && playerTurn.getPieceColour() == boardSize[yPos + a][xPos].getPiece().getColor()) {
                count++;
            } else {
                a = 5;
                for (int b = 1; b < 5; b++) {
                    if (yPos - b >= 0 && boardSize[yPos - b][xPos].hasPiece() && playerTurn.getPieceColour() == boardSize[yPos - b][xPos].getPiece().getColor()) {
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
            if (xPos + a < boardSize[yPos].length && boardSize[yPos][xPos + a].hasPiece() && playerTurn.getPieceColour() == boardSize[yPos][xPos + a].getPiece().getColor()) {
                count++;
            } else {
                a = 5;
                for (int b = 1; b < 5; b++) {
                    if (xPos - b >= 0 && boardSize[yPos][xPos - b].hasPiece() && playerTurn.getPieceColour() == boardSize[yPos][xPos - b].getPiece().getColor()) {
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
            if (xPos + a < boardSize[yPos].length && yPos + a < boardSize.length && boardSize[yPos + a][xPos + a].hasPiece() && playerTurn.getPieceColour() == boardSize[yPos + a][xPos + a].getPiece().getColor()) {
                count++;
            } else {
                a = 5;
                for (int b = 1; b < 5; b++) {
                    if (xPos - b >= 0 && yPos - b > 0 && boardSize[yPos - b][xPos - b].hasPiece() && playerTurn.getPieceColour() == boardSize[yPos - b][xPos - b].getPiece().getColor()) {
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
            if (xPos + a < boardSize[yPos].length && yPos - a > 0 && boardSize[yPos - a][xPos + a].hasPiece() && playerTurn.getPieceColour() == boardSize[yPos - a][xPos + a].getPiece().getColor()) {
                count++;
            } else {
                a = 5;
                for (int b = 1; b < 5; b++) {
                    if (xPos - b >= 0 && yPos + b < boardSize.length && boardSize[yPos + b][xPos - b].hasPiece() && playerTurn.getPieceColour() == boardSize[yPos + b][xPos - b].getPiece().getColor()) {
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


    private final static String FILE_PATH = System.getProperty("user.dir") + "\\progress.dat";

    public void saveProgress() {
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            output.writeObject(this);
            output.close();
        } catch (IOException ioe) {
            System.out.println("IOException in file writing: " + ioe.getMessage() + "\nFile writing will now stop.");
        }
    }

    public void loadProgress() {
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            Board board = (Board) input.readObject();
            input.close();
            boardSize = board.boardSize;
            playerTurn = board.playerTurn;
            bestOf = board.bestOf;
        } catch (Exception e) {
            System.out.println("IOException in file reading: " + e.getMessage() + "\nFile reading will now stop.");
        }
    }
}
