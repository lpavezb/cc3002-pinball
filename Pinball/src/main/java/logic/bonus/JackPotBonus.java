package logic.bonus;

import controller.Game;

public class JackPotBonus extends AbstractBonus {
    //gives 100000 points
    @Override
    public void trigger(Game game) {
        game.addScore(100000);
        super.trigger(game);
    }
}
