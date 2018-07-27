package gui;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.entity.components.ViewComponent;
import com.almasb.fxgl.physics.PhysicsComponent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import logic.gameelements.bumper.Bumper;

import java.awt.*;

public class BumperControl extends Component {
    private Bumper bumper;
    public BumperControl(Bumper bumper){
        this.bumper = bumper;
    }
    public void hit() {
        bumper.hit();
        if (bumper.isUpgraded())
            entity.getViewComponent().setView(new Circle(20,Color.GREEN));
    }
}
