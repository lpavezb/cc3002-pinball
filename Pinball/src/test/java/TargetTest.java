
import logic.gameelements.bumper.Bumper;
import logic.gameelements.target.Target;
import org.junit.Test;

import static org.junit.Assert.*;

public class TargetTest extends GameTest {

    @Test
    public void creationTest(){
        assertEquals(6, targets.size());
        assertEquals(3, spotTargets.size());
        assertEquals(3, dropTargets.size());
    }

    @Test
    public void SpotTargetHitTest(){
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

        dropTarget0.hit();
        assertEquals(100, game.getCurrentScore());

        dropTarget1.hit();
        assertEquals(200, game.getCurrentScore());

        dropTarget2.hit();
        assertEquals(1000300, game.getCurrentScore());
    }

    @Test
    public void kickerBumperUpgradeTest(){
        Bumper kickerBumper = kickerBumpers.get(0);
        kickerBumper.hit();
        kickerBumper.hit();
        kickerBumper.hit();
        kickerBumper.hit();
        assertFalse(kickerBumper.isUpgraded());

        kickerBumper.hit();
        assertTrue(kickerBumper.isUpgraded());
    }

    @Test
    public void popBumperUpgradeTest(){
        Bumper popBumper = popBumpers.get(0);
        popBumper.hit();
        popBumper.hit();
        assertFalse(popBumper.isUpgraded());

        popBumper.hit();
        assertTrue(popBumper.isUpgraded());
    }

    @Test
    public void upgradeBonusTest(){
        Bumper popBumper = popBumpers.get(0);
        assertFalse(popBumper.isUpgraded());
        assertEquals(5, game.getAvailableBalls());

        popBumper.setSeed(456440); // < 0.1
        popBumper.upgrade();
        assertTrue(popBumper.isUpgraded());
        assertEquals(5, game.getAvailableBalls());

        popBumper.downgrade();
        popBumper.hit();
        popBumper.hit();
        popBumper.hit();
        assertTrue(popBumper.isUpgraded());
        assertEquals(6, game.getAvailableBalls());
    }

    @Test
    public void downgradeTest(){
        Bumper popBumper = popBumpers.get(0);
        Bumper kickerBumper = kickerBumpers.get(0);
        assertFalse(popBumper.isUpgraded());
        assertFalse(kickerBumper.isUpgraded());

        popBumper.upgrade();
        kickerBumper.upgrade();
        assertTrue(popBumper.isUpgraded());
        assertTrue(kickerBumper.isUpgraded());

        popBumper.downgrade();
        kickerBumper.downgrade();
        assertFalse(popBumper.isUpgraded());
        assertFalse(kickerBumper.isUpgraded());
    }
}
