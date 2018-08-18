import logic.table.NullTable;
import logic.table.Table;
import org.junit.*;

import static org.junit.Assert.*;


public class NullTableTest {
    private Table table;

    @Before
    public void setUp(){
        table = new NullTable();
    }

    @Test
    public void getName(){
        assertEquals("", table.getTableName());
    }

    @Test
    public void getElements(){
        assertEquals(0, table.getHittables().size());
    }

    @Test
    public void targets(){
        assertEquals(0, table.getTargets().size());
        assertEquals(0, table.getSpotTargets().size());
        assertEquals(0, table.getDropTargets().size());

        assertEquals(0, table.getCurrentlyDroppedDropTargets());
        assertEquals(0, table.getNumberOfDropTargets());
    }

    @Test
    public void bumpers(){
        assertEquals(0, table.getBumpers().size());
        assertEquals(0, table.getKickerBumpers().size());
        assertEquals(0, table.getPopBumpers().size());
    }
}
