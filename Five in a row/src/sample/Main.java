package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.*;

public class Main extends Application {

    private Player player1 = new Player(1, Main.class.getResource("img/TrashMan.png").toExternalForm());
    private Player player2 = new Player(2, Main.class.getResource("img/DrToboggan.jpg").toExternalForm());
    private final int SIZE = 15;
    private Board board = new Board(new Cell[SIZE + 1][SIZE], player1);
    private final static String FILE_PATH = System.getProperty("user.dir") + "\\progress.dat";

    private Stage stage;
    private GridPane layout = new GridPane();
    private Label turnPlayer = new Label();
    private Label turnNumber = new Label();
    private Button saveButton = new Button();
    private Button loadButton = new Button();

    /** The method that calls when the application is being launched. */
    @Override
    public void start(Stage window) throws Exception {
        stage = window;
        stage.setTitle("5 in a Row");

        createBoard();

        // save button properties
        saveButton.setText("Save current progress.");
        saveButton.setOnAction(e -> saveProgress(board));
        saveButton.setTranslateX(0);
        saveButton.setTranslateY(375);

        // load button properties
        loadButton.setText("Load previous progress.");
        loadButton.setOnAction(e -> {
            board = loadProgress(board);
            refreshBoard();
        });
        loadButton.setTranslateX(300);
        loadButton.setTranslateY(375);

        // current player label properties
        turnPlayer.setText("Next Move: The Trash Man");
        turnPlayer.setTranslateX(0);
        turnPlayer.setTranslateY(350);
        turnPlayer.setMinWidth(Region.USE_PREF_SIZE);

        // turn number label properties
        turnNumber.setText("Turn Number: 1");
        turnNumber.setTranslateX(300);
        turnNumber.setTranslateY(350);
        turnNumber.setMinWidth(Region.USE_PREF_SIZE);

        stage.setScene(new Scene(layout, 400, 400));
        stage.setMinWidth(700);
        stage.setMaxWidth(700);
        stage.setMinHeight(800);
        stage.setMaxHeight(800);

        stage.show();
    }

    /** The main method of the application */
    public static void main(String[] args) {
        launch(args);
    }


    /**
     * Saves the current progress of the board into a binary file.
     * @param board1 The board to be saved.
     */
    private void saveProgress(Board board1) {
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            output.writeObject(board1);
            output.close();
        } catch (IOException ioe) {
            System.out.println("IOException in file writing: " + ioe.getMessage() + "\nFile writing will now stop.");
        }
    }

    /**
     * Loads the board's current progress saved in the binary file.
     * @param board1 The board to be passed back if something goes wrong.
     */
    private Board loadProgress(Board board1) {
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            Board retrievedBoard = (Board) input.readObject();
            input.close();
            return retrievedBoard;
        } catch (Exception e) {
            System.out.println("IOException in file reading: " + e.getMessage() + "\nFile reading will now stop.");
            return board1;
        }
    }

    /** Creates a new board to be displayed. */
    private void createBoard() {
        // the layout is cleared
        layout.getChildren().clear();

        // cells are generated
        for (int x = 0; x < board.getBoardSize().length; x++) {
            for (int y = 0; y < board.getBoardSize()[x].length; y++) {
                final int fx = x, fy = y;
                Cell cell = board.getBoardSize()[fx][fy] = new Cell(20);

                // top line is ignored due to weird bugs
                if (x > 0) {
                    cell.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

                    // mouse click event handler on click of the generated cell
                    cell.setOnMouseClicked(e -> {

                        // if a cell hasn't got a piece...
                        if (!cell.hasPlayerClaimed()) {

                            // set it one, based on current player turn
                            cell.setPlayerClaimed(board.getPlayerTurn());
                            cell.setStyle("-fx-background-image: url('" + board.getPlayerTurn().getImage() + "');");

                            // update the next player and turn number labels
                            turnPlayer.setText("Next Move: " + (board.getPlayerTurn() == player1 ? "Dr. Mantis Toboggan" : "The Trash Man"));
                            board.setTurnNumber(board.getTurnNumber() + 1);
                            turnNumber.setText("Turn Number: " + board.getTurnNumber());

                            // five-in-a-row is determined
                            if (board.determineFiveInRow(fy, fx)) {
                                if (AlertWinner.display(board.getPlayerTurn().getPlayerNumber())) {
                                    board = new Board(new Cell[SIZE + 1][SIZE], player1);
                                    createBoard();
                                    //refreshBoard();
                                } else {
                                    stage.close();
                                }
                            } else {
                                board.setPlayerTurn(board.getPlayerTurn() == player1 ? player2 : player1);
                            }
                        }
                    });

                    // the cell is added to the board
                    board.add(cell, y, x);
                    cell.setPadding(new Insets(cell.getCellLength(), cell.getCellLength(), cell.getCellLength(), cell.getCellLength()));
                }
            }
        }

        // labels and player turn are updated
        board.setPlayerTurn(board.getTurnNumber() % 2 == 0 ? player2 : player1);
        turnPlayer.setText("Next Move: " + (board.getPlayerTurn() == player2 ? "Dr. Mantis Toboggan" : "The Trash Man"));
        turnNumber.setText("Turn Number: " + board.getTurnNumber());

        layout.getChildren().addAll(board, saveButton, loadButton, turnNumber, turnPlayer);
        layout.setPadding(new Insets(20, 20, 20, 20));
    }

    /** Refreshes the board being displayed. */
    private void refreshBoard() {
        // layout is cleared
        layout.getChildren().clear();

        // cells are regenerated
        for (int x = 0; x < board.getBoardSize().length; x++) {
            for (int y = 0; y < board.getBoardSize()[x].length; y++) {
                final int fx = x, fy = y;
                Cell cell = board.getBoardSize()[fx][fy];

                // first row is ignored
                if (x > 0) {
                    cell.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));


                    // if a cell has been claimed, then display its claim
                    if (cell.hasPlayerClaimed()) {
                        //cell.setPlayerClaimed(cell.getPlayerClaimed());
                        cell.setPlayerClaimed(cell.getPlayerClaimed().getPlayerNumber() == player1.getPlayerNumber() ? player1 : player2);
                        cell.setStyle("-fx-background-image: url('" + cell.getPlayerClaimed().getImage() + "');");
                    }

                    // mouse click event handler on click of the regenerated cell
                    cell.setOnMouseClicked(i -> {

                        // if a cell hasn't got a piece...
                        if (!cell.hasPlayerClaimed()) {

                            // set it one, based on current player turn
                            cell.setPlayerClaimed(board.getPlayerTurn());
                            cell.setStyle("-fx-background-image: url('" + board.getPlayerTurn().getImage() + "');");

                            // update the next player and turn number labels
                            turnPlayer.setText("Next Move: " + (board.getPlayerTurn() == player1 ? "Dr. Mantis Toboggan" : "The Trash Man"));
                            board.setTurnNumber(board.getTurnNumber() + 1);
                            turnNumber.setText("Turn Number: " + board.getTurnNumber());

                            // five-in-a-row is determined
                            if (board.determineFiveInRow(fy, fx)) {
                                if (AlertWinner.display(board.getPlayerTurn().getPlayerNumber())) {
                                    board = new Board(new Cell[SIZE + 1][SIZE], player1);
                                    createBoard();
                                    //refreshBoard();
                                } else {
                                    stage.close();
                                }
                            } else {
                                board.setPlayerTurn(board.getPlayerTurn() == player1 ? player2 : player1);
                            }
                        }
                    });

                    // cells are re-added to the board
                    board.add(cell, y, x);
                    cell.setPadding(new Insets(cell.getCellLength(), cell.getCellLength(), cell.getCellLength(), cell.getCellLength()));
                }
            }
        }

        // labels and player turn are updated
        board.setPlayerTurn(board.getTurnNumber() % 2 == 0 ? player2 : player1);
        turnPlayer.setText("Next Move: " + (board.getPlayerTurn() == player2 ? "Dr. Mantis Toboggan" : "The Trash Man"));
        turnNumber.setText("Turn Number: " + board.getTurnNumber());

        layout.getChildren().addAll(board, saveButton, loadButton, turnNumber, turnPlayer);
    }
}
