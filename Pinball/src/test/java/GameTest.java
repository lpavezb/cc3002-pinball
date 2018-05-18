import controller.Game;
import logic.gameelements.bumper.*;
import logic.gameelements.target.DropTarget;
import logic.gameelements.target.SpotTarget;
import logic.gameelements.target.Target;
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

    @Before
    public void setUp(){
        game = new Game();
        assertFalse(game.isPlayableTable());
        table = new GameTable("pinball", 10, 0.5, 3, 3, 0);
        game.setGameTable(table);
        assertTrue(game.isPlayableTable());

        bumpers = table.getBumpers();
        popBumpers = table.getPopBumpers();
        kickerBumpers = table.getKickerBumpers();

        targets = table.getTargets();
        spotTargets = table.getSpotTargets();
        dropTargets = table.getDropTargets();
    }

}