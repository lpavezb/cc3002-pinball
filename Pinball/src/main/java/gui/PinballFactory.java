package gui;

import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.almasb.fxgl.physics.box2d.dynamics.FixtureDef;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import logic.gameelements.bumper.Bumper;

public class PinballFactory {

    public Entity newBall(double x, double y){
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);
        physics.setFixtureDef(new FixtureDef().restitution(1f).density(0.1f).friction(0f));
        physics.setOnPhysicsInitialized(() -> physics.setLinearVelocity(0 , -8 * 60));
        return Entities.builder()
                .at(x, y)
                .type(GameType.BALL)
                .bbox(new HitBox("Ball", BoundingShape.circle(10)))
                .viewFromNode(new Circle(10, Color.RED))
                .with(physics, new CollidableComponent(true), new BallComponent())
                .build();
    }

    public Entity newFlipper(double x, double y, GameType type){
        int aux = type==GameType.RIGHT_FLIPPER ? 1 : -1;
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.KINEMATIC);
        physics.setFixtureDef(new FixtureDef().restitution(1f).density(0.1f).friction(0f));
        return Entities.builder()
                .at(x, y)
                .rotate(-30*aux)
                .type(type)
                .bbox(new HitBox("Flipper", BoundingShape.box(100,30)))
                .viewFromNode(new Rectangle(100,30, Color.BLUE))
                .with(physics, new CollidableComponent(true), new FlipperControl(type))
                .build();
    }

    public Entity newBumper(Bumper bumper) {
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.STATIC);
        physics.setFixtureDef(new FixtureDef().restitution(1f).density(0.1f).friction(0f));
        return Entities.builder()
                .at(250, 250)
                .type(GameType.BUMPER)
                .bbox(new HitBox("Bumper", BoundingShape.circle(20)))
                .viewFromNode(new Circle(20, Color.WHITE))
                .with(physics, new CollidableComponent(true), new BumperControl(bumper))
                .build();
    }
}
