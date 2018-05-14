package logic.bonus;

import controller.Game;
import logic.gameelements.bumper.Bumper;

public class DropTargetBonus extends AbstractBonus {
    //gives 1000000 points and upgrades all bumpers
    @Override
    public void trigger(Game game) {
        int current = game.getCurrentScore();
        game.setScore(current + 1000000);
        for(Bumper bumper : game.getBumpers())
            bumper.upgrade();
        super.trigger(game);
    }

}
