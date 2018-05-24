package logic.inverseVisitor;

import controller.Game;
import logic.gameelements.Hittable;
import logic.table.Table;

/**
 * Class that represents a IVisitor object
 *
 * @author Lukas Pavez
 * @see IVisitor
 */
public class ScoreVisitor implements IVisitor {
    private int score;
    public ScoreVisitor(Hittable hittable) { score = hittable.getScore(); }

    @Override
    public void accept(Table table) { table.visitScore(this);  }

    @Override
    public void trigger(Game game) { game.addScore(score); }
}
