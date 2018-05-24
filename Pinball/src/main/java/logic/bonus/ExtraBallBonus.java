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
public class ExtraBallBonus extends AbstractBonus {

    @Override
    public void trigger(Game game) {
        game.addBall();
        super.trigger(game);
    }
}
