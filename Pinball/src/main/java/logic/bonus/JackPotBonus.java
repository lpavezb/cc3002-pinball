package logic.bonus;

import controller.Game;

public class JackPotBonus extends AbstractBonus {
    //gives 100000 points
    @Override
    public void trigger(Game game) {
        int current = game.getCurrentScore();
        game.setScore(current + 100000);
        super.trigger(game);
    }
}
