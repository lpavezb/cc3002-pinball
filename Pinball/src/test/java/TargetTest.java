
import logic.gameelements.bumper.Bumper;
import logic.gameelements.target.DropTarget;
import logic.gameelements.target.SpotTarget;
import logic.gameelements.target.Target;
import org.junit.Test;

import static org.junit.Assert.*;

public class TargetTest extends GameTest {

    @Test
    public void creationTest(){
        assertEquals(6, targets.size());
        assertEquals(3, spotTargets.size());
        assertEquals(3, table.getNumberOfDropTargets());
    }

    @Test
    public void spotTargetHitTest(){
        Target spotTarget = spotTargets.get(0);
        assertTrue(spotTarget.isActive());
        spotTarget.hit();
        assertFalse(spotTarget.isActive());
        assertEquals(100000, game.getCurrentScore());
    }

    @Test
    public void dropTargetHitTest(){
        Target dropTarget = dropTargets.get(0);
        dropTarget.hit(); //+100
        assertEquals(100, game.getCurrentScore());
    }

    @Test
    public void hitAllDropTargetTest(){
        Target dropTarget0 = dropTargets.get(0);
        Target dropTarget1 = dropTargets.get(1);
        Target dropTarget2 = dropTargets.get(2);

        for(Bumper bumper : bumpers)
            assertFalse(bumper.isUpgraded());

        dropTarget0.hit(); //+100
        assertEquals(100, game.getCurrentScore());

        dropTarget1.hit(); //+100
        assertEquals(200, game.getCurrentScore());

        dropTarget1.hit(); // hit again same target, + 0
        assertEquals(200, game.getCurrentScore());

        assertEquals(2, table.getCurrentlyDroppedDropTargets());

        dropTarget2.hit(); //+100 + 1000000 bonus
        assertEquals(1000300, game.getCurrentScore());

        for(Bumper bumper : bumpers)
            assertTrue(bumper.isUpgraded());
        assertEquals(0, table.getCurrentlyDroppedDropTargets());
    }

    @Test
    public void dropTargetExtraBallBonusTest(){
        Target dropTarget = dropTargets.get(0);

        assertEquals(5, game.getAvailableBalls());

        dropTarget.setSeed(magicalSeedForTesting);
        dropTarget.hit(); //+100
        assertEquals(100, game.getCurrentScore());
        assertEquals(6, game.getAvailableBalls());

    }

    @Test
    public void resetTargetTest(){
        Target dropTarget = dropTargets.get(0);
        assertTrue(dropTarget.isActive());

        dropTarget.hit();
        assertFalse(dropTarget.isActive());

        dropTarget.reset();
        assertTrue(dropTarget.isActive());
    }

    @Test
    public void resetAllTargetsTest(){
        // only 2 of the 3 targets are hit
        for(int i = 0; i<2; i++) {
            dropTargets.get(i).hit();
            assertFalse(dropTargets.get(i).isActive());
        }

        table.resetDropTargets();

        for(DropTarget dropTarget : dropTargets)
            assertTrue(dropTarget.isActive());
    }

    @Test
    public void getNumberOfDroppedDropTargetsTest(){
        DropTarget dropTarget0 = dropTargets.get(0);
        DropTarget dropTarget1 = dropTargets.get(1);

        dropTarget0.hit();
        dropTarget1.hit();


        assertEquals(2, table.getCurrentlyDroppedDropTargets());

        dropTarget0.reset();
        assertEquals(1, table.getCurrentlyDroppedDropTargets());

        dropTarget1.reset();
        assertEquals(0, table.getCurrentlyDroppedDropTargets());

        dropTarget0.hit();
        dropTarget1.hit();

        assertEquals(2, table.getCurrentlyDroppedDropTargets());
        table.resetDropTargets();

        assertEquals(0, table.getCurrentlyDroppedDropTargets());
    }

    @Test
    public void hitNotActiveTargetsTest(){
        DropTarget dropTarget = dropTargets.get(0);
        SpotTarget spotTarget = spotTargets.get(0);

        assertTrue(dropTarget.isActive());
        dropTarget.hit();
        assertEquals(100, game.getCurrentScore());

        assertFalse(dropTarget.isActive());
        dropTarget.hit();
        assertEquals(100, game.getCurrentScore());

        assertTrue(spotTarget.isActive());
        spotTarget.hit();
        assertEquals(100100, game.getCurrentScore());

        assertFalse(spotTarget.isActive());
        spotTarget.hit();
        assertEquals(100100, game.getCurrentScore());

    }
}
