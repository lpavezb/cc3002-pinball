package gui;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import logic.gameelements.bumper.Bumper;
import logic.gameelements.bumper.KickerBumper;

public class BumperControlFactory {
    private Node startView;
    private Node upgradeView;
    private Bumper bumper;
    private String sound;
    private int upgradeTime;

    public BumperControlFactory(){
        startView = new Circle(20, Color.BLUE);
        upgradeView = new Circle(20, Color.DARKGREEN);
        bumper = new KickerBumper();
        sound = "KickerBumper.wav";
        upgradeTime = 10;
    }

    public BumperControlFactory setBumper(Bumper bumper) {
        this.bumper = bumper;
        return this;
    }

    public BumperControlFactory setStartView(Node startView) {
        this.startView = startView;
        return this;
    }

    public BumperControlFactory setUpgradeView(Node upgradeView) {
        this.upgradeView = upgradeView;
        return this;
    }

    public BumperControlFactory setSound(String sound) {
        this.sound = sound;
        return this;
    }

    public BumperControlFactory setUpgradeTime(int time) {
        this.upgradeTime = time;
        return this;
    }

    public BumperControl build(){
        return new BumperControl(bumper, startView, upgradeView, sound, upgradeTime);
    }
}
