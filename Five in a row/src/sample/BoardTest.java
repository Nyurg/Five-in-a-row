package sample;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

public class BoardTest extends TestCase {

    private Player player1 = new Player(1, Main.class.getResource("img/TrashMan.png").toExternalForm());;
    private int SIZE = 15;
    private int turnNumber = 1;
    private Cell[][] cells = new Cell[SIZE + 1][SIZE];
    private Board board;

    @Before
    public void setUp() throws Exception {
        board = new Board(cells, player1);
        board.setTurnNumber(turnNumber);

        // Add cells to board
        for (int x = 0; x < board.getBoardSize().length; x++) {
            for (int y = 0; y < board.getBoardSize()[x].length; y++) {
                Cell cell = board.getBoardSize()[x][y] = new Cell(20);

                // top line is ignored due to weird bugs
                if (x > 0) {
                    cell.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                    final int fx = x, fy = y;

                    // mouse click event handler on click of the generated cell
                    cell.setOnMouseClicked(e -> {
                        Cell cellClicked = board.getBoardSize()[fx][fy];

                        // if a cell hasn't got a piece...
                        if (!cellClicked.hasPlayerClaimed()) {

                            // set it one, based on current player turn
                            cellClicked.setPlayerClaimed(board.getPlayerTurn());
                            cellClicked.setStyle("-fx-background-image: url('" + board.getPlayerTurn().getImage() + "');");

                            // update the next player
                            board.setTurnNumber(board.getTurnNumber() + 1);
                        }
                    });

                    // the cell is added to the board
                    board.add(cell, y, x);
                    cell.setPadding(new Insets(cell.getCellLength(), cell.getCellLength(), cell.getCellLength(), cell.getCellLength()));
                }
            }
        }
    }

    public void testGetBoardSize() { assertSame(board.getBoardSize(), cells); }

    public void testGetPlayerTurn() {
        assertSame(board.getPlayerTurn(), player1);
    }

    public void testGetTurnNumber() {
        assertSame(board.getTurnNumber(), turnNumber);
    }

    public void testDetermineFiveInARow() { assertFalse(board.determineFiveInRow(1, 1));}
}
