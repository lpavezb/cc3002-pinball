package gui;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.core.math.Vec2;
import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.GameWorld;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.particle.ParticleComponent;
import com.almasb.fxgl.particle.ParticleEmitter;
import com.almasb.fxgl.particle.ParticleEmitters;
import com.almasb.fxgl.physics.*;
import com.almasb.fxgl.physics.box2d.dynamics.Body;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.almasb.fxgl.physics.box2d.dynamics.FixtureDef;
import com.almasb.fxgl.settings.GameSettings;
import controller.Game;
import javafx.geometry.Point2D;
import javafx.scene.effect.BlendMode;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;
import logic.gameelements.bumper.Bumper;
import logic.gameelements.target.Target;
import logic.table.Table;
import logic.table.TableFactory;

import java.util.List;
import java.util.Map;


public class GUI extends GameApplication {
    private int inGameBalls;
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

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("score", 0);
        vars.put("lives", 5);
    }

    @Override
    protected void initGame() {
        game = new Game();
        factory = new PinballFactory();
        Entity walls = factory.newWalls();
        Entity infoBar = factory.newInfoBar();
        inGameBalls = 0;
        rightFlipper = factory.newFlipper(300, 470, GameType.RIGHT_FLIPPER);
        leftFlipper = factory.newFlipper(180, 470, GameType.LEFT_FLIPPER);
        getGameScene().setBackgroundColor(Color.BLACK);
        getGameWorld().addEntities(walls, leftFlipper, rightFlipper, infoBar);
    }

    @Override
    protected void onUpdate(double tpf) {
        getGameState().setValue("score", game.getCurrentScore());
        getGameState().setValue("lives", game.getAvailableBalls());
    }

    @Override
    protected void initUI() {
        int tx = 10;
        int ty = 20;
        Text scoreText = getUIFactory().newText("score: ", Color.BLACK, 16.0);
        scoreText.setTranslateX(tx);
        scoreText.setTranslateY(ty);

        Text uiScore = getUIFactory().newText("", Color.BLACK, 16.0);
        uiScore.setTranslateX(tx + 50);
        uiScore.setTranslateY(ty);
        uiScore.textProperty().bind(getGameState().intProperty("score").asString());

        getGameScene().addUINodes(scoreText, uiScore);

        ty+=20;
        Text livesText = getUIFactory().newText("lives: ", Color.BLACK, 16.0);
        livesText.setTranslateX(tx);
        livesText.setTranslateY(ty);

        Text uiLives = getUIFactory().newText("", Color.BLACK, 16.0);
        uiLives.setTranslateX(tx + 50);
        uiLives.setTranslateY(ty);
        uiLives.textProperty().bind(getGameState().intProperty("lives").asString());

        getGameScene().addUINodes(livesText, uiLives);
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
                leftFlipper.getComponent(FlipperControl.class).down();
            }
        }, KeyCode.A);

        input.addAction(new UserAction("RightFlipper") {
            @Override
            protected void onAction() {
                rightFlipper.getComponent(FlipperControl.class).up();
            }

            @Override
            protected void onActionEnd() {
                rightFlipper.getComponent(FlipperControl.class).down();
            }
        }, KeyCode.S);

        input.addAction(new UserAction("NewBall") {
            @Override
            protected void onActionBegin() {
                if(inGameBalls == 0){
                    getGameWorld().addEntity(factory.newBall(300,400));
                    //inGameBalls += 1;
                }
            }

        }, KeyCode.SPACE);

        input.addAction(new UserAction("NewTable") {
            @Override
            protected void onActionBegin() {
                game.setGameTable(new TableFactory().setNumberOfBumpers(2)
                                                    .setNumberOfDropTargets(2)
                                                    .setNumberOfTargets(2)
                                                    .createTable());
                deleteElements();
                createElements();
            }

        }, KeyCode.N);
    }

    private void deleteElements() {
        GameWorld world = getGameWorld();
        world.removeEntities(world.getEntitiesByType(GameType.BUMPER));
        world.removeEntities(world.getEntitiesByType(GameType.TARGET));
    }

    private void createElements() {
        Table gameTable = game.getCurrentTable();
        List<Bumper> bumpers = gameTable.getBumpers();
        List<Target> targets = gameTable.getTargets();
        for (Bumper bumper : bumpers) {
            Entity newBumper = factory.newBumper(bumper);
            getGameWorld().addEntity(newBumper);
        }
        for (Target target : targets) {
            Entity newTarget = factory.newTarget(target);
            getGameWorld().addEntity(newTarget);
        }
    }

    @Override
    protected void initPhysics() {
        PhysicsWorld world = getPhysicsWorld();
        world.setGravity(0,800);
        world.addCollisionHandler(new CollisionHandler(GameType.BALL, GameType.WALL) {
            @Override
            protected void onHitBoxTrigger(Entity ball, Entity wall, HitBox ballBox, HitBox wallBox) {
                if(wallBox.getName().equals("BOT")) {
                    Body body = ball.getComponent(PhysicsComponent.class).getBody();
                    ball.removeComponent(PhysicsComponent.class);

                    getMasterTimer().runOnceAfter(() -> {
                        getPhysicsWorld().getJBox2DWorld().destroyBody(body);
                    }, Duration.seconds(0.1));

                    getGameWorld().removeEntity(ball);
                }
            }
        });
        world.addCollisionHandler(new CollisionHandler(GameType.BUMPER, GameType.BALL) {
            @Override
            protected void onCollisionBegin(Entity a, Entity b) {
                a.getComponent(BumperControl.class).hit();
                sparks(b);
                //TODO: play bumper sound
            }
        });
        world.addCollisionHandler(new CollisionHandler(GameType.TARGET, GameType.BALL) {
            @Override
            protected void onCollisionBegin(Entity a, Entity b) {
                a.getComponent(TargetControl.class).hit();
                sparks(b);
                //TODO: play target sound
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
