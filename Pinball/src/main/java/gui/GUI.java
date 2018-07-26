package gui;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.particle.ParticleComponent;
import com.almasb.fxgl.particle.ParticleEmitter;
import com.almasb.fxgl.particle.ParticleEmitters;
import com.almasb.fxgl.physics.*;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.almasb.fxgl.physics.box2d.dynamics.FixtureDef;
import com.almasb.fxgl.settings.GameSettings;
import javafx.scene.effect.BlendMode;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;


public class GUI extends GameApplication {
    private Entity leftFlipper;
    private Entity rightFlipper;
    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(600);
        settings.setHeight(600);
        settings.setTitle("Sample");
        settings.setVersion("pre-alpha");
    }

    public Entity newPlayer(double x, double y, GameType type){
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.KINEMATIC);
        physics.setFixtureDef(new FixtureDef().restitution(1f).density(0.1f).friction(0f));
        return Entities.builder()
                .at(x, y)
                .type(type)
                .bbox(new HitBox("flipper", BoundingShape.box(100,30)))
                .viewFromNode(new Rectangle(100,30, Color.BLUE))
                .with(physics, new CollidableComponent(true), new FlipperControl(type))
                .build();
    }

    public static Entity newBackground(){
        return Entities.builder()
                .viewFromNode(new Rectangle(600,600, Color.BLACK))
                .build();
    }
    public static Entity newWalls(){
        Entity walls = Entities.makeScreenBounds(100);
        walls.setType(GameType.WALL);
        walls.addComponent(new CollidableComponent(true));
        return walls;
    }

    public static Entity newBall(double x, double y){
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);
        physics.setFixtureDef(new FixtureDef().restitution(1f).density(0.1f).friction(0f));
        physics.setOnPhysicsInitialized(() -> physics.setLinearVelocity(5 * 60 , 0));
        return Entities.builder()
                .at(x, y)
                .type(GameType.BALL)
                .bbox(new HitBox("Ball", BoundingShape.circle(10)))
                .viewFromNode(new Circle(10, Color.RED))
                .with(physics, new CollidableComponent(true))
                .build();
    }

    @Override
    protected void initGame() {
        Entity ball = newBall(500, 500);
        Entity bkg = newBackground();
        rightFlipper = newPlayer(300, 500, GameType.RIGHT_FLIPPER);
        rightFlipper.rotateBy(-30);
        leftFlipper = newPlayer(200, 500, GameType.LEFT_FLIPPER);
        leftFlipper.rotateBy(30);
        getGameWorld().addEntities(bkg, newWalls(), rightFlipper, leftFlipper, ball);

    }

    @Override
    protected void initInput() {
        Input input = getInput();

        input.addAction(new UserAction("Up") {
            @Override
            protected void onAction() {
                rightFlipper.getComponent(FlipperControl.class).up();
                leftFlipper.getComponent(FlipperControl.class).up();
            }

            @Override
            protected void onActionEnd() {
                input.mockKeyPress(KeyCode.F8);
            }
        }, KeyCode.A);

        input.addAction(new UserAction("Down") {
            @Override
            protected void onAction() {
                rightFlipper.getComponent(FlipperControl.class).down();
                leftFlipper.getComponent(FlipperControl.class).down();
            }

        }, KeyCode.F8);
    }

    @Override
    protected void initPhysics() {
        PhysicsWorld world = getPhysicsWorld();
        world.setGravity(0,800);
        world.addCollisionHandler(new CollisionHandler(GameType.BALL, GameType.WALL){});

        world.addCollisionHandler(new CollisionHandler(GameType.LEFT_FLIPPER, GameType.BALL) {
            @Override
            protected void onCollisionBegin(Entity a, Entity b) {
                collision(a, b);
            }
        });
        world.addCollisionHandler(new CollisionHandler(GameType.RIGHT_FLIPPER, GameType.BALL) {
            @Override
            protected void onCollisionBegin(Entity a, Entity b) {
                collision(a, b);
            }
        });

    }

    private void collision(Entity a, Entity b){
        // 1. create entity
        Entity sparks = new Entity();
        sparks.setPosition(b.getPosition());

        // 2. create and configure emitter + component
        ParticleEmitter emitter = ParticleEmitters.newSparkEmitter();
        emitter.setMaxEmissions(1);
        emitter.setColor(Color.WHITE);
        emitter.setBlendMode(BlendMode.SRC_OVER);

        ParticleComponent component = new ParticleComponent(emitter);

        // we also want the entity to destroy itself when particle component is done
        component.setOnFinished(sparks::removeFromWorld);

        // 3. add control to entity
        sparks.addComponent(component);

        // 4. add entity to game world
        getGameWorld().addEntity(sparks);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
