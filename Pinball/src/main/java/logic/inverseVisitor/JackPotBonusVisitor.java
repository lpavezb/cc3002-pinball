package logic.inverseVisitor;

import controller.Game;
import logic.table.Table;

/**
 * Class that represents a IVisitor object
 *
 * @author Lukas Pavez
 * @see IVisitor
 */
public class JackPotBonusVisitor implements IVisitor {

    @Override
    public void accept(Table table) { table.visitJackPotBonus(this);  }

    @Override
    public void trigger(Game game) { game.triggerJackPotBonus(); }
}
