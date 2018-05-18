package logic.visitor;

import controller.Game;
import logic.table.Table;

public class JackPotBonusVisitor implements Visitor {

    @Override
    public void accept(Table table) { table.visitJackPotBonus(this);  }

    @Override
    public void triggerBonus(Game game) { game.triggerJackPotBonus(); }
}
