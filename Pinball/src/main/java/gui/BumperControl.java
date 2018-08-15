package gui;

import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.entity.component.Component;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import logic.gameelements.bumper.Bumper;

public class BumperControl extends Component {
    private Bumper bumper;
    private Node upgradeView;
    private Node startView;
    private boolean isUpgraded;

    @Override
    public void onUpdate(double tpf) {
        if(bumper.isUpgraded() && !isUpgraded){
            FXGL.getAudioPlayer().playSound("bumper_upgrade.wav");
            entity.getViewComponent().setView(upgradeView);
            isUpgraded = true;
            FXGL.getMasterTimer().runOnceAfter(() -> {
                bumper.downgrade();
                entity.setView(startView);
                isUpgraded = false;
            }, Duration.seconds(10));
        }
    }

    public BumperControl(Bumper bumper){
        this.bumper = bumper;
        isUpgraded = false;
        if (bumper.isKickerBumper()) {
            startView = new Circle(20, Color.BLUE);
            upgradeView = new Circle(20, Color.DARKGREEN);
        }
        else {
            startView = new Circle(20, Color.CYAN);
            upgradeView = new Circle(20, Color.LAWNGREEN);
        }
    }

    public void hit() {
        bumper.hit();
    }


}
