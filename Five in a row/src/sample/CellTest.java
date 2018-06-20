package sample;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

public class CellTest extends TestCase {

    private int length = 20;
    private Cell cell = new Cell(length);
    private Player player = new Player();

    @Before
    public void setUp() throws Exception {
        cell.setPlayerClaimed(player);
    }

    public void testGetCellLength() {
        assertSame(cell.getCellLength(), length);
    }

    public void testGetPlayerClaimed() {
        assertSame(cell.getPlayerClaimed(), player);
    }
}
