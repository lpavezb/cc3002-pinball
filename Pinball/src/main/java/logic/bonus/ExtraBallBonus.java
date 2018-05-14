package logic.bonus;

import controller.Game;

public class ExtraBallBonus extends AbstractBonus {
    //gives extra ball
    @Override
    public void trigger(Game game) {
        game.addBall();
        super.trigger(game);
    }
}
