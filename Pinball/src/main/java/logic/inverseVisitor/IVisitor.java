package logic.inverseVisitor;

import controller.Game;
import logic.table.Table;

/**
 * Class that represents a IVisitor pattern
 *
 * @author Lukas Pavez
 */
public interface IVisitor {

    /**
     * Accepts a Table
     *
     * @param table
     * @see Table
     */
    void accept(Table table);

    /**
     * Triggers a change in the game score/balls
     *
     * @param game
     * @see Game
     */
    void trigger(Game game);
}
