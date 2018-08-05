package gui;

import com.almasb.fxgl.entity.component.Component;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import logic.gameelements.bumper.Bumper;

public class BumperControl extends Component {
    private Bumper bumper;
    private int timeControl;
    private int time;
    private Node upgradeView;
    private Node startView;
    private boolean isUpgraded;

    @Override
    public void onUpdate(double tpf) {
        timeControl+=1;
        if (timeControl%60==0)
            time += 1;
        if(bumper.isUpgraded() && !isUpgraded){
            entity.getViewComponent().setView(upgradeView);
            time = 0;
            isUpgraded = true;
        }
        if(bumper.isUpgraded() && time==10){
            bumper.downgrade();
            entity.setView(startView);
            isUpgraded = false;
        }
    }

    public BumperControl(Bumper bumper){
        this.bumper = bumper;
        time = 0;
        timeControl = 0;
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
