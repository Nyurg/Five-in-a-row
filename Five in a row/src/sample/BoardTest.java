package sample;

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
    }

    public void testGetBoardSize() {
        assertSame(board.getBoardSize(), cells);
    }

    public void testGetPlayerTurn() {
        assertSame(board.getPlayerTurn(), player1);
    }

    public void testGetTurnNumber() {
        assertSame(board.getTurnNumber(), turnNumber);
    }
}
