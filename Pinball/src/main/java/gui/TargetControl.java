package gui;

import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.entity.component.Component;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import logic.gameelements.bumper.Bumper;
import logic.gameelements.target.Target;

public class TargetControl extends Component {
    private Target target;

    private Node upgradeView;
    private Node startView;
    private boolean isActive;

    @Override
    public void onUpdate(double tpf) {

        if(!target.isActive() && isActive){
            entity.getViewComponent().setView(upgradeView);
            isActive = false;
            FXGL.getMasterTimer().runOnceAfter(() -> {
                target.reset();
                entity.setView(startView);
                isActive = true;
            }, Duration.seconds(20));
        }
    }

    public TargetControl(Target target){
        int size = 40;
        this.target= target;
        isActive = true;
        if (target.isDropTarget()) {
            startView = new Rectangle(size, size, Color.YELLOW);
            upgradeView = new Rectangle(size, size, Color.RED);
        }
        else {
            startView = new Rectangle(size, size, Color.WHITE);
            upgradeView = new Rectangle(size, size, Color.PURPLE);
        }
    }

    public void hit() {
        target.hit();
    }
}
