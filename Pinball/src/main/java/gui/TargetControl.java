package gui;

import com.almasb.fxgl.entity.component.Component;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import logic.gameelements.bumper.Bumper;
import logic.gameelements.target.Target;

public class TargetControl extends Component {
    private Target target;
    private int timeControl;
    private int time;
    private Node upgradeView;
    private Node startView;
    private boolean isActive;

    @Override
    public void onUpdate(double tpf) {
        timeControl+=1;
        if (timeControl%60==0) {
            time += 1;
        }
        if((!target.isActive() && time==4) || target.isActive() && !isActive){
            target.reset();
            entity.setView(startView);
            isActive = true;
        }
    }

    public TargetControl(Target target){
        this.target= target;
        time = 0;
        timeControl = 0;
        isActive = true;
        if (target.isDropTarget()) {
            startView = new Rectangle(20, 20, Color.BLUE);
            upgradeView = new Rectangle(20, 20, Color.DARKGREEN);
        }
        else {
            startView = new Rectangle(20, 20, Color.CYAN);
            upgradeView = new Rectangle(20, 20, Color.LAWNGREEN);
        }
    }

    public void hit() {
        target.hit();
        if(!target.isActive() && isActive){
            entity.getViewComponent().setView(upgradeView);
            time = 0;
            isActive = false;
        }
    }
}
