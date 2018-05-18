package controller;

import logic.gameelements.bumper.Bumper;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BumperTest extends GameTest {

    @Test
    public void creationTest(){
        assertEquals(10, bumpers.size());
        assertEquals(3, popBumpers.size());
        assertEquals(7, kickerBumpers.size());
    }

    @Test
    public void kickerBumperHitTest(){
        Bumper kickerBumper = kickerBumpers.get(0);
        kickerBumper.hit(); //+500
        assertEquals(500, game.getCurrentScore());
        assertEquals(5, game.getAvailableBalls());
    }

    @Test
    public void popBumperHitTest(){
        Bumper popBumper = popBumpers.get(0);
        popBumper.hit(); //+100
        assertEquals(100, game.getCurrentScore());
        assertEquals(5, game.getAvailableBalls());
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
        Bumper kickerBumper = kickerBumpers.get(0);
        assertFalse(popBumper.isUpgraded());
        assertFalse(kickerBumper.isUpgraded());
        assertEquals(5, game.getAvailableBalls());

        popBumper.setSeed(456440); // < 0.1
        kickerBumper.setSeed(456440); // < 0.1
        popBumper.upgrade();
        assertTrue(popBumper.isUpgraded());
        assertEquals(6, game.getAvailableBalls());

        kickerBumper.upgrade();
        assertTrue(kickerBumper.isUpgraded());
        assertEquals(7, game.getAvailableBalls());
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
