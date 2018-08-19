package gui;

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
import javafx.scene.shape.Rectangle;
import logic.gameelements.bumper.KickerBumper;
import logic.gameelements.bumper.PopBumper;
import logic.gameelements.target.DropTarget;
import logic.gameelements.target.SpotTarget;

/**
 * Factory class for building the game Entities
 *
 * @author Lukas Pavez
 * @see Entity
 */
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
        Entity walls = Entities.makeScreenBounds(100);
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

    private Entity newHittable(Point2D position) {
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.STATIC);
        physics.setFixtureDef(new FixtureDef().restitution(1f).density(0.1f).friction(0f));
        Entity bumper = Entities.builder()
                .at(position)
                .bbox(new HitBox("Hittable", BoundingShape.circle(20)))
                .with(physics)
                .build();
        bumper.addComponent(new CollidableComponent(true));
        return bumper;
    }

    public Entity newKickerBumper(KickerBumper kickerBumper, Point2D position) {
        Node startView = new Circle(20, Color.BLUE);
        Node upgradeView = new Circle(20, Color.DARKGREEN);
        Entity newBumper = newHittable(position);
        newBumper.setView(startView);
        newBumper.setType(GameType.BUMPER);
        newBumper.addComponent(new BumperControlFactory()
                .setBumper(kickerBumper)
                .setStartView(startView)
                .setUpgradeView(upgradeView)
                .setSound("KickerBumper.wav")
                .setUpgradeTime(10)
                .build());
        return newBumper;
    }

    public Entity newPopBumper(PopBumper popBumper, Point2D position) {
        Node startView = new Circle(20, Color.CYAN);
        Node upgradeView = new Circle(20, Color.LAWNGREEN);
        Entity newBumper = newHittable(position);
        newBumper.setView(startView);
        newBumper.setType(GameType.BUMPER);
        newBumper.addComponent(new BumperControlFactory()
                .setBumper(popBumper)
                .setStartView(startView)
                .setUpgradeView(upgradeView)
                .setSound("PopBumper.wav")
                .setUpgradeTime(6)
                .build());
        return newBumper;
    }

    public Entity newDropTarget(DropTarget dropTarget, Point2D position) {
        int size = 40;
        Node startView = new Rectangle(size, size, Color.YELLOW);
        Node upgradeView = new Rectangle(size, size, Color.RED);
        Entity newTarget = newHittable(position);
        newTarget.setView(startView);
        newTarget.setType(GameType.TARGET);
        newTarget.addComponent(new TargetControlFactory()
                .setTarget(dropTarget)
                .setStartView(startView)
                .setUpgradeView(upgradeView)
                .setSound("DropTarget.wav")
                .setReactivateTime(120)
                .build());
        return newTarget;
    }

    public Entity newSpotTarget(SpotTarget spotTarget, Point2D position) {
        int size = 40;
        Node startView = new Rectangle(size, size, Color.WHITE);
        Node upgradeView = new Rectangle(size, size, Color.PURPLE);
        Entity newTarget = newHittable(position);
        newTarget.setView(startView);
        newTarget.setType(GameType.TARGET);
        newTarget.addComponent(new TargetControlFactory()
                .setTarget(spotTarget)
                .setStartView(startView)
                .setUpgradeView(upgradeView)
                .setSound("SpotTarget.wav")
                .setReactivateTime(20)
                .build());
        return newTarget;
    }
}
