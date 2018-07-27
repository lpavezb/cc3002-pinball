package gui;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.core.math.Vec2;
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
import controller.Game;
import javafx.scene.effect.BlendMode;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import logic.gameelements.bumper.Bumper;
import logic.table.NullTable;
import logic.table.Table;
import logic.table.TableFactory;

import java.util.ArrayList;
import java.util.List;


public class GUI extends GameApplication {
    private int inGameBalls = 0;
    private Entity gameBall;
    private Game game;
    private PinballFactory factory;
    private Entity leftFlipper;
    private Entity rightFlipper;
    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(600);
        settings.setHeight(600);
        settings.setTitle("Pinball");
        settings.setVersion("pre-alpha");
    }

    public static Entity newWalls(){
        Entity walls = Entities.makeScreenBounds(100);
        walls.setType(GameType.WALL);
        walls.addComponent(new CollidableComponent(true));
        return walls;
    }

    @Override
    protected void initGame() {
        game = new Game();
        factory = new PinballFactory();
        rightFlipper = factory.newFlipper(300, 470, GameType.RIGHT_FLIPPER);
        leftFlipper = factory.newFlipper(180, 470, GameType.LEFT_FLIPPER);
        getGameScene().setBackgroundColor(Color.BLACK);
        getGameWorld().addEntities(newWalls(), leftFlipper, rightFlipper);

    }

    @Override
    protected void initInput() {
        Input input = getInput();

        input.addAction(new UserAction("LeftFlipper") {
            @Override
            protected void onAction() {
                leftFlipper.getComponent(FlipperControl.class).up();
            }

            @Override
            protected void onActionEnd() {
                input.mockKeyPress(KeyCode.F8);
            }
        }, KeyCode.A);

        input.addAction(new UserAction("RightFlipper") {
            @Override
            protected void onAction() {
                rightFlipper.getComponent(FlipperControl.class).up();
            }

            @Override
            protected void onActionEnd() {
                input.mockKeyPress(KeyCode.F8);
            }
        }, KeyCode.S);

        input.addAction(new UserAction("DownFlippers") {
            @Override
            protected void onAction() {
                rightFlipper.getComponent(FlipperControl.class).down();
                leftFlipper.getComponent(FlipperControl.class).down();
            }

        }, KeyCode.F8);

        input.addAction(new UserAction("NewBall") {
            @Override
            protected void onActionBegin() {
                if(inGameBalls == 0){
                    gameBall = factory.newBall(300,400);
                    getGameWorld().addEntity(gameBall);
                    //inGameBalls += 1;
                }
            }

        }, KeyCode.SPACE);

        input.addAction(new UserAction("NewTable") {
            @Override
            protected void onActionBegin() {
                game.setGameTable(new TableFactory().setNumberOfBumpers(1)
                                                    .setNumberOfDropTargets(0)
                                                    .setNumberOfTargets(0)
                                                    .createTable());
                createElements();
            }

        }, KeyCode.N);
    }

    private void createElements() {
        Table gameTable = game.getCurrentTable();
        List<Bumper> bumpers = gameTable.getBumpers();
        for (Bumper bumper : bumpers) {
            Entity newBumper = factory.newBumper(bumper);
            getGameWorld().addEntity(newBumper);
        }
    }

    @Override
    protected void initPhysics() {
        PhysicsWorld world = getPhysicsWorld();
        world.setGravity(0,800);
        world.addCollisionHandler(new CollisionHandler(GameType.BALL, GameType.WALL){
            @Override
            protected void onHitBoxTrigger(Entity ball, Entity wall, HitBox boxBall, HitBox boxWall) {
                if(boxWall.getName().equals("BOT")){
                    //ball.removeFromWorld();
                    //inGameBalls -= 1;
                }
            }
        });

        world.addCollisionHandler(new CollisionHandler(GameType.LEFT_FLIPPER, GameType.BALL) {
            @Override
            protected void onCollisionBegin(Entity a, Entity b) {
                sparks(b);
            }
        });
        world.addCollisionHandler(new CollisionHandler(GameType.RIGHT_FLIPPER, GameType.BALL) {
            @Override
            protected void onCollisionBegin(Entity a, Entity b) {
                sparks(b);
            }
        });
        world.addCollisionHandler(new CollisionHandler(GameType.BUMPER, GameType.BALL) {
            @Override
            protected void onCollisionBegin(Entity a, Entity b) {
                a.getComponent(BumperControl.class).hit();
                sparks(b);
                System.out.println(game.getCurrentScore());
            }
        });
    }

    private void sparks(Entity ball){
        // 1. create entity
        Entity sparks = new Entity();
        sparks.setPosition(ball.getPosition());

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
