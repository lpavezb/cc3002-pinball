
import logic.bonus.Bonus;
import logic.gameelements.bumper.Bumper;
import org.junit.Test;

import static org.junit.Assert.*;

public class BonusTest extends GameTest {

    @Test
    public void extraBallBonusTest(){
        Bonus extraBallBonus = game.getExtraBallBonus();
        assertEquals(0, game.getCurrentScore());
        assertEquals(5, game.getAvailableBalls());
        extraBallBonus.trigger(game);

        assertEquals(6, game.getAvailableBalls());
        assertEquals(0, game.getCurrentScore());

        assertEquals(1, extraBallBonus.timesTriggered());
    }

    @Test
    public void jackPotBonusTest(){
        Bonus jackPotBonus = game.getJackPotBonus();
        assertEquals(0, game.getCurrentScore());

        jackPotBonus.trigger(game);
        assertEquals(100000, game.getCurrentScore());

        assertEquals(1, jackPotBonus.timesTriggered());
    }

    @Test
    public void dropTargetBonusTest(){
        Bonus dropTargetBonus = game.getDropTargetBonus();
        assertEquals(0, game.getCurrentScore());

        for(Bumper bumper : bumpers)
            assertFalse(bumper.isUpgraded());

        dropTargetBonus.trigger(game);

        for(Bumper bumper : bumpers)
            assertTrue(bumper.isUpgraded());

        assertEquals(1000000, game.getCurrentScore());
        assertEquals(1, dropTargetBonus.timesTriggered());
    }

}
