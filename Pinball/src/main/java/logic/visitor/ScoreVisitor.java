package logic.visitor;

import controller.Game;
import logic.gameelements.Hittable;
import logic.table.Table;

public class ScoreVisitor implements Visitor{
    private int score;
    public ScoreVisitor(Hittable hittable) { score = hittable.getScore(); }

    @Override
    public void accept(Table table) { table.visitScore(this);  }

    @Override
    public void trigger(Game game) { game.addScore(score); }
}
