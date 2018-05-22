package logic.bonus;

import controller.Game;
import logic.table.Table;

public class DropTargetBonus extends AbstractBonus {
    //gives 1000000 points and upgrades all bumpers
    @Override
    public void trigger(Game game) {
        game.addScore(1000000);
        Table table = game.getCurrentTable();
        table.upgradeAllBumpers();
        super.trigger(game);
    }

}
