package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    private Player player1 = new Player(1, Color.BLUE);
    private Player player2 = new Player(2, Color.RED);
    Label turnPlayer = new Label();
    Label turnNumber = new Label();

    @Override
    public void start(Stage window) throws Exception {
        window.setTitle("5 in a Row");
        GridPane layout = new GridPane();

        final int SIZE = 15;
        Board board = new Board(new Cell[SIZE + 1][SIZE], player1, 3);
        board.setPlayerTurn(player1);

        String trashMan = Main.class.getResource("img/TrashMan.png").toExternalForm();
        String drToboggan = Main.class.getResource("img/DrToboggan.jpg").toExternalForm();

        for (int x = 0; x < board.getBoardSize().length; x++) {
            for (int y = 0; y < board.getBoardSize()[x].length; y++) {
                Cell cell = board.getBoardSize()[x][y] = new Cell(20);
                if (x > 0) {
                    cell.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
                            CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                    final int fx = x, fy = y;

                    cell.setOnMouseClicked(e -> {
                        Cell cellClicked = board.getBoardSize()[fx][fy];
                        if (!cellClicked.hasPiece()) {
                            cellClicked.setPiece(new Piece(board.getPlayerTurn().getPieceColour()));
                            cellClicked.setStyle(board.getPlayerTurn().getPieceColour() == player1.getPieceColour() ?
                                    "-fx-background-image: url('" + trashMan + "')" : "-fx-background-image: url('" + drToboggan + "')");
                            turnPlayer.setText(board.getPlayerTurn().getPieceColour() == player1.getPieceColour() ?
                                    "Next Move: Dr. Mantis Toboggan" : "Next Move: The Trash Man");
                            board.setTurnNumber(board.getTurnNumber() + 1);
                            turnNumber.setText("Turn Number: " + Integer.toString(board.getTurnNumber()));
                            board.determineFiveInRow(fy, fx);
                            board.setPlayerTurn(board.getPlayerTurn() == player1 ? player2 : player1);
                        }
                    });

                    board.add(cell, y, x);
                    cell.setPadding(new Insets(cell.getCellLength(), cell.getCellLength(), cell.getCellLength(), cell.getCellLength()));
                }
            }
        }

        Button saveButton = new Button("Save current progress.");
        saveButton.setOnAction(e -> board.saveProgress());
        saveButton.setTranslateX(0);
        saveButton.setTranslateY(375);

        Button loadButton = new Button("Load previous progress.");
        loadButton.setOnAction(e -> board.loadProgress());
        loadButton.setTranslateX(300);
        loadButton.setTranslateY(375);

        turnPlayer.setText("Next Move: The Trash Man");
        turnPlayer.setTranslateX(0);
        turnPlayer.setTranslateY(350);
        turnPlayer.setMinWidth(Region.USE_PREF_SIZE);

        turnNumber.setText("Turn Number: 0");
        turnNumber.setTranslateX(300);
        turnNumber.setTranslateY(350);
        turnNumber.setMinWidth(Region.USE_PREF_SIZE);

        layout.getChildren().addAll(board, saveButton, loadButton, turnNumber, turnPlayer);
        layout.setPadding(new Insets(20, 20, 20, 20));

        window.setScene(new Scene(layout, 400, 400));
        window.setMinWidth(700);
        window.setMaxWidth(700);
        window.setMinHeight(800);
        window.setMaxHeight(800);

        window.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
