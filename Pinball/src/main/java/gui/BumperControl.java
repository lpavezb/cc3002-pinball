package gui;

import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.entity.component.Component;
import javafx.scene.Node;
import logic.gameelements.bumper.Bumper;

public class BumperControl extends Component {
    private int timeControl;
    private int time;
    private String sound;
    private String startSound;
    private String upgradeSound;
    private int upgradeTime;
    private Bumper bumper;
    private Node upgradeView;
    private Node startView;
    private boolean isUpgraded;

    @Override
    public void onUpdate(double tpf) {
        timeControl+=1;
        if (timeControl%60==0)
            time += 1;
        if(upgraded()){
            FXGL.getAudioPlayer().playSound("BumperUpgrade.wav");
            entity.getViewComponent().setView(upgradeView);
            sound = upgradeSound;
            isUpgraded = true;
            time = 0;
        }
        if(downgraded()){
            downgrade();
        }
        if(bumper.isUpgraded() && time==upgradeTime){
            downgrade();
        }
    }

    public BumperControl(Bumper bumper, Node startView, Node upgradeView, String sound, int upgradeTime){
        isUpgraded = false;
        this.upgradeTime = upgradeTime;
        this.bumper = bumper;
        this.startView = startView;
        this.upgradeView = upgradeView;
        this.sound = sound;
        this.startSound= sound;
        upgradeSound = sound.replace(".", "2.");
    }

    public void hit() {
        bumper.hit();
        FXGL.getAudioPlayer().playSound(sound);
    }

    private boolean upgraded(){
        return bumper.isUpgraded() && !isUpgraded;
    }

    private boolean downgraded(){
        return !bumper.isUpgraded() && isUpgraded;
    }

    private void downgrade(){
        bumper.downgrade();
        entity.getViewComponent().setView(startView);
        isUpgraded = false;
        sound = startSound;
    }
}
