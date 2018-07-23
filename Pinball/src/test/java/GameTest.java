import controller.Game;
import logic.gameelements.bumper.*;
import logic.gameelements.target.*;
import logic.table.*;
import org.junit.*;

import java.util.List;

import static org.junit.Assert.*;

public class GameTest {
    Game game;
    Table table;
    List<Bumper> bumpers;
    List<PopBumper> popBumpers;
    List<KickerBumper> kickerBumpers;

    List<Target> targets;
    List<SpotTarget> spotTargets;
    List<DropTarget> dropTargets;

    long magicalSeedForTesting = 456440; //random.nextDouble < 0.1

    @Before
    public void setUp(){
        game = new Game();
        assertFalse(game.isPlayableTable());

        table = new TableFactory()
                .setName("pinball")
                .setSeed(0)
                .createTable();

        game.setGameTable(table);
        assertTrue(game.isPlayableTable());

        bumpers = table.getBumpers();
        popBumpers = table.getPopBumpers();
        kickerBumpers = table.getKickerBumpers();

        targets = table.getTargets();
        spotTargets = table.getSpotTargets();
        dropTargets = table.getDropTargets();
    }

    @Test
    public void gameOverTest(){
        game.dropBall();
        game.dropBall();
        game.dropBall();
        game.dropBall();

        assertFalse(game.gameOver());

        game.dropBall();
        assertTrue(game.gameOver());
    }

    @Test
    public void getNameTest(){
        assertEquals("pinball", game.getTableName());
    }
}