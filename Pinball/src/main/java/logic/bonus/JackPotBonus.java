package logic.bonus;

import controller.Game;

/**
 * Class that represents a bonus object.
 *
 * @author Lukas Pavez
 * @see Bonus
 * @see ExtraBallBonus
 * @see JackPotBonus
 */
public class JackPotBonus extends AbstractBonus {

    @Override
    public void trigger(Game game) {
        game.addScore(100000);
        super.trigger(game);
    }
}
