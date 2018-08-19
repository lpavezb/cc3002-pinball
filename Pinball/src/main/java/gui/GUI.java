package gui;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.GameWorld;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.particle.ParticleComponent;
import com.almasb.fxgl.particle.ParticleEmitter;
import com.almasb.fxgl.particle.ParticleEmitters;
import com.almasb.fxgl.physics.*;
import com.almasb.fxgl.physics.box2d.dynamics.Body;
import com.almasb.fxgl.settings.GameSettings;
import controller.Game;
import javafx.geometry.Point2D;
import javafx.scene.effect.BlendMode;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;
import logic.gameelements.bumper.KickerBumper;
import logic.gameelements.bumper.PopBumper;
import logic.gameelements.target.DropTarget;
import logic.gameelements.target.SpotTarget;
import logic.table.Table;
import logic.table.TableFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Pinball Game
 *
 * Main App class
 *
 * @author Lukas Pavez
 *
 * @see GameApplication
 */
public class GUI extends GameApplication {
    private int inGameBalls;
    private Game game;
    private PinballFactory factory;
    private Entity leftFlipper;
    private Entity rightFlipper;
    private Point2D[][] hittableAvailablePositions;
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
        vars.put("tExtraBallBonus", 0);
        vars.put("tJackPotBonus", 0);
        vars.put("tDropTargetBonus", 0);
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

        // init positions for hittables
        hittableAvailablePositions = new Point2D[14][6];
        int x = 20;
        int y = 80;
        for(int i=0; i<14; i++){
            for(int j=0; j<6; j++){
                hittableAvailablePositions[i][j] = new Point2D(x,y);
                y+=40;
            }
            y=80;
            x+=40;
        }

        getGameState().<Integer>addListener("lives", (old, newScore) -> {
            if (newScore == 0)
                showGameOver();
        });

        //bonus sounds
        getGameState().<Integer>addListener("tExtraBallBonus", (old, newScore) -> {
            if (!old.equals(newScore)) {
                getAudioPlayer().playSound("ExtraBallBonus.wav");
            }
        });
        getGameState().<Integer>addListener("tJackPotBonus", (old, newScore) -> {
            if (!old.equals(newScore)) {
                getAudioPlayer().playSound("JackPotBonus.wav");
            }
        });
        getGameState().<Integer>addListener("tDropTargetBonus", (old, newScore) -> {
            if (!old.equals(newScore)) {
                getAudioPlayer().playSound("DropTargetBonus.wav");
            }
        });
    }

    @Override
    protected void onUpdate(double tpf) {
        int eBB = game.getExtraBallBonus().timesTriggered();
        int jPB = game.getJackPotBonus().timesTriggered();
        int dTB = game.getDropTargetBonus().timesTriggered();
        getGameState().setValue("tExtraBallBonus", eBB);
        getGameState().setValue("tJackPotBonus", jPB);
        getGameState().setValue("tDropTargetBonus", dTB);
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
                    inGameBalls += 1;
                }
            }

        }, KeyCode.SPACE);

        input.addAction(new UserAction("NewTable") {
            @Override
            protected void onActionBegin() {
                game.setGameTable(new TableFactory().setNumberOfBumpers(3)
                                                    .setNumberOfDropTargets(3)
                                                    .setNumberOfTargets(3)
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

        List<Point2D> invalidPositions = new ArrayList<>();
        List<Entity> entities = new ArrayList<>();
        Table gameTable = game.getCurrentTable();

        //create KickerBumpers
        List<KickerBumper> kickerBumpers = gameTable.getKickerBumpers();
        for (KickerBumper kickerBumper : kickerBumpers) {
            Point2D point = getPoint(invalidPositions);
            entities.add(factory.newKickerBumper(kickerBumper, point));
        }
        //create PopBumpers
        List<PopBumper> popBumpers = gameTable.getPopBumpers();
        for (PopBumper popBumper : popBumpers) {
            Point2D point = getPoint(invalidPositions);
            entities.add(factory.newPopBumper(popBumper, point));
        }
        //create DropTargets
        List<DropTarget> dropTargets = gameTable.getDropTargets();
        for(DropTarget dropTarget : dropTargets){
            Point2D point = getPoint(invalidPositions);
            entities.add(factory.newDropTarget(dropTarget, point));
        }
        //create SpotTargets
        List<SpotTarget> spotTargets = gameTable.getSpotTargets();
        for(SpotTarget spotTarget : spotTargets){
            Point2D point = getPoint(invalidPositions);
            entities.add(factory.newSpotTarget(spotTarget, point));
        }

        for(Entity entity : entities) {
            getGameWorld().addEntity(entity);
        }

        //add mouse events
        for(Entity entity : entities) {
            if(entity.getType()==GameType.BUMPER)
                entity.getView().setOnMouseClicked(e -> entity.getComponent(BumperControl.class).hit());
            if(entity.getType()==GameType.TARGET)
                entity.getView().setOnMouseClicked(e -> entity.getComponent(TargetControl.class).hit());
        }
    }

    private Point2D getPoint(List<Point2D> invalidPositions){
        Random r = new Random();
        int x = r.nextInt(14);
        int y = r.nextInt(6);
        Point2D point = hittableAvailablePositions[x][y];
        while(invalidPositions.contains(point)){
            x = r.nextInt(14);
            y = r.nextInt(6);
            point = hittableAvailablePositions[x][y];
        }
        invalidPositions.add(point);
        return point;
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
                    game.dropBall();
                    inGameBalls-=1;
                }
            }
        });
        world.addCollisionHandler(new CollisionHandler(GameType.BUMPER, GameType.BALL) {
            @Override
            protected void onCollisionBegin(Entity a, Entity b) {
                a.getComponent(BumperControl.class).hit();
                sparks(b.getPosition());
            }
        });
        world.addCollisionHandler(new CollisionHandler(GameType.TARGET, GameType.BALL) {
            @Override
            protected void onCollisionBegin(Entity a, Entity b) {
                a.getComponent(TargetControl.class).hit();
                sparks(b.getPosition());
            }
        });
    }

    private void sparks(Point2D position){
        // 1. create entity
        Entity sparks = new Entity();
        sparks.setPosition(position);

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

    private void showGameOver() {
        getDisplay().showMessageBox("You ran out of lives!\nThanks for playing", this::exit);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
