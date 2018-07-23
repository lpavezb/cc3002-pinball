package logic.bonus;

import controller.Game;
import logic.table.Table;

/**
 * Class that represents a bonus object.
 *
 * @author Lukas Pavez
 * @see Bonus
 * @see ExtraBallBonus
 * @see JackPotBonus
 */
public class DropTargetBonus extends AbstractBonus {

    @Override
    public void trigger(Game game) {
        game.addScore(1000000);
        Table table = game.getCurrentTable();
        table.upgradeAllBumpers();
        table.resetDropTargets();
        super.trigger(game);
    }

}
