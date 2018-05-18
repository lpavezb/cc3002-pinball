package logic.visitor;

import controller.Game;
import logic.table.Table;

public class ExtraBallBonusVisitor implements Visitor{

    @Override
    public void accept(Table table){ table.visitExtraBallBonus(this); }

    @Override
    public void triggerBonus(Game game) { game.triggerExtraBallBonus(); }
}
