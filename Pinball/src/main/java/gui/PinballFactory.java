package gui;

import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.almasb.fxgl.physics.box2d.dynamics.FixtureDef;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Rectangle;
import logic.gameelements.bumper.Bumper;
import logic.gameelements.target.Target;

public class PinballFactory {

    public Entity newBall(double x, double y) {
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);
        physics.setFixtureDef(new FixtureDef().restitution(1f).density(0.1f).friction(0f));
        physics.setOnPhysicsInitialized(() -> physics.setLinearVelocity(0, -8 * 60));
        Entity ball = Entities.builder()
                .at(x, y)
                .type(GameType.BALL)
                .bbox(new HitBox("Ball", BoundingShape.circle(10)))
                .viewFromNode(new Circle(10, Color.RED))
                .with(physics)
                .build();
        ball.addComponent(new CollidableComponent(true));
        ball.addComponent(new BallComponent());
        return ball;
    }

    public Entity newFlipper(double x, double y, GameType type) {
        int aux = type == GameType.RIGHT_FLIPPER ? 1 : -1;
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.KINEMATIC);
        physics.setFixtureDef(new FixtureDef().restitution(1f).density(0.1f).friction(0f));
        Entity flipper = Entities.builder()
                .at(x, y)
                .rotate(-30 * aux)
                .type(type)
                .bbox(new HitBox("Flipper", BoundingShape.box(100, 30)))
                .viewFromNode(new Rectangle(100, 30, Color.BLUE))
                .with(physics)
                .build();
        flipper.addComponent(new CollidableComponent(true));
        flipper.addComponent(new FlipperControl(type));
        return flipper;
    }

    public Entity newWalls(){
        double w = (double)FXGL.getSettings().getWidth();
        double h = (double)FXGL.getSettings().getHeight();
        double thickness = 100;
        Entity walls = Entities.makeScreenBounds(100);
        /*
        TODO: use Entities.makeScreenBounds
        al usar makeScreenBounds lanza error al remover bolita
        Message: null
        Type: NullPointerException
        Method: Fixture.getProxyId()
        Line: Fixture.java:263
        */
        /*Entity walls = Entities.builder()
                .bbox(new HitBox("LEFT", new Point2D(-thickness, 0.0D), BoundingShape.box(thickness, h)))
                .bbox(new HitBox("RIGHT", new Point2D(w, 0.0D), BoundingShape.box(thickness, h)))
                .bbox(new HitBox("TOP", new Point2D(0.0D, -thickness), BoundingShape.box(w, thickness)))
                .bbox(new HitBox("BOT", new Point2D(0.0D, h), BoundingShape.box(w, thickness)))
                .with(new PhysicsComponent()).build();*/
        walls.setType(GameType.WALL);
        walls.addComponent(new CollidableComponent(true));
        return walls;
    }

    public Entity newInfoBar(){
        return Entities.builder()
                .at(0,0)
                .type(GameType.WALL)
                .viewFromNodeWithBBox(new Rectangle(600,50,Color.WHITE))
                .with(new PhysicsComponent(), new CollidableComponent(true))
                .build();
    }

    public Entity newBumper(Bumper aBumper) {
        Node view;
        if (aBumper.isKickerBumper())
            view = new Circle(20, Color.BLUE);
        else
            view = new Circle(20, Color.CYAN);

        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.STATIC);
        physics.setFixtureDef(new FixtureDef().restitution(1f).density(0.1f).friction(0f));
        Entity bumper = Entities.builder()
                .at(FXGLMath.random(60, 550), FXGLMath.random(60, 250))
                .type(GameType.BUMPER)
                .bbox(new HitBox("Target", BoundingShape.circle(20)))
                .viewFromNode(view)
                .with(physics)
                .build();
        bumper.addComponent(new CollidableComponent(true));
        bumper.addComponent(new BumperControl(aBumper));
        return bumper;
    }

    public Entity newTarget(Target target) {
        Node view;
        int size = 40;
        if (target.isDropTarget())
            view = new Rectangle(size, size, Color.YELLOW);
        else
            view = new Rectangle(size, size, Color.WHITE);

        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.STATIC);
        physics.setFixtureDef(new FixtureDef().restitution(1f).density(0.1f).friction(0f));
        Entity bumper = Entities.builder()
                .at(FXGLMath.random(60, 550), FXGLMath.random(60, 250))
                .type(GameType.TARGET)
                .viewFromNodeWithBBox(view)
                .with(physics)
                .build();
        bumper.addComponent(new CollidableComponent(true));
        bumper.addComponent(new TargetControl(target));
        return bumper;
    }
}
