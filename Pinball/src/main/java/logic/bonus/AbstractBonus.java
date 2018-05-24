package logic.bonus;

import controller.Game;

/**
 * Abstract class that represents a bonus object.
 *
 * @author Lukas Pavez
 * @see Bonus
 * @see ExtraBallBonus
 * @see JackPotBonus
 * @see DropTargetBonus
 */
public abstract class AbstractBonus implements Bonus{
    private int nTimesTriggered;

    public AbstractBonus(){ nTimesTriggered = 0; }
    @Override
    public int timesTriggered() {
        return nTimesTriggered;
    }

    @Override
    public void trigger(Game game) { nTimesTriggered += 1; }
}
