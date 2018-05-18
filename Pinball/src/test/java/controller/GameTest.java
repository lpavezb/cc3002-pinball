package controller;

import logic.gameelements.bumper.Bumper;
import logic.gameelements.bumper.PopBumper;
import logic.table.GameTable;
import logic.table.Table;
import org.junit.*;

import java.util.List;

import static org.junit.Assert.*;

public class GameTest {
    private Game game;
    private Table table;

    @Before
    public void setUp(){
        game = new Game();
        assertFalse(game.isPlayableTable());
        table = new GameTable("pinball", 10, 0.5, 3, 3, 0);
        game.setGameTable(table);
        assertTrue(game.isPlayableTable());
    }

    @Test
    public void bumperTest(){
        List<PopBumper> bumpers = table.getPopBumpers();
        int size = bumpers.size();
        assertEquals(10,size);
        Bumper aBumper = bumpers.get(0);
    }
}