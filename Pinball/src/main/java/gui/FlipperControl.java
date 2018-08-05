package gui;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.entity.components.PositionComponent;
import com.almasb.fxgl.physics.PhysicsComponent;
import javafx.geometry.Point2D;

public class FlipperControl extends Component {
    private int minAngle;
    private int maxAngle;
    private double velocity;
    private boolean right;
    private boolean down;

    @Override
    public void onUpdate(double tpf) {
        if(down){
            double rot =entity.getRotation();
            if (right ? rot>minAngle : rot<minAngle){
                entity.getComponent(PhysicsComponent.class).setAngularVelocity(-1 * velocity);
            }
            else {
                down = false;
                stop();
            }
        }
    }

    public FlipperControl(GameType type){
        right = type == GameType.RIGHT_FLIPPER;
        int aux = right ? 1 : -1;
        minAngle = -30*aux;
        maxAngle = 60*aux;
        velocity = 8*aux;
        down = false;
    }
    public void down() {
        down = true;
    }

    public void up() {
        double rot =entity.getRotation();
        if (right ? rot<maxAngle : rot>maxAngle){
            entity.getComponent(PhysicsComponent.class).setAngularVelocity(velocity);
        }
        else
            stop();
    }

    public void stop() {
        entity.getComponent(PhysicsComponent.class).setAngularVelocity(0);
    }
}
