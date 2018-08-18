package gui;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import logic.gameelements.target.DropTarget;
import logic.gameelements.target.Target;

public class TargetControlFactory {
    private Node startView;
    private Node upgradeView;
    private Target target;
    private String sound;

    public TargetControlFactory(){
        int size = 40;
        startView = new Rectangle(size, size, Color.YELLOW);
        upgradeView = new Rectangle(size, size, Color.RED);
        target = new DropTarget();
        sound = "DropTarget.wav";
    }

    public TargetControlFactory setTarget(Target target) {
        this.target = target;
        return this;
    }

    public TargetControlFactory setStartView(Node startView) {
        this.startView = startView;
        return this;
    }

    public TargetControlFactory setUpgradeView(Node upgradeView) {
        this.upgradeView = upgradeView;
        return this;
    }

    public TargetControlFactory setSound(String sound) {
        this.sound = sound;
        return this;
    }

    public TargetControl build(){
        return new TargetControl(target, startView, upgradeView, sound);
    }
}
