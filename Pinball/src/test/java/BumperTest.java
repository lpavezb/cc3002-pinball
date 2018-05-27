import logic.gameelements.bumper.Bumper;
import org.junit.Test;

import static org.junit.Assert.*;

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
        kickerBumper.hit(); //+500
        kickerBumper.hit(); //+500
        kickerBumper.hit(); //+500
        kickerBumper.hit(); //+500
        assertFalse(kickerBumper.isUpgraded());

        assertEquals(2000, game.getCurrentScore());

        kickerBumper.hit(); //+1000
        assertTrue(kickerBumper.isUpgraded());
        assertEquals(3000, game.getCurrentScore());
    }

    @Test
    public void popBumperUpgradeTest(){
        Bumper popBumper = popBumpers.get(0);
        popBumper.hit(); //+100
        popBumper.hit(); //+100
        assertFalse(popBumper.isUpgraded());

        assertEquals(200, game.getCurrentScore());

        popBumper.hit(); //+300
        assertTrue(popBumper.isUpgraded());

        assertEquals(500, game.getCurrentScore());
    }

    @Test
    public void upgradeBonusTest(){
        Bumper popBumper = popBumpers.get(0);
        assertFalse(popBumper.isUpgraded());
        assertEquals(5, game.getAvailableBalls());

        popBumper.setSeed(magicalSeedForTesting);
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
