package gui;

import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.entity.component.Component;
import javafx.scene.Node;
import logic.gameelements.target.Target;

/**
 * Class for controlling a target component
 *
 * @author Lukas Pavez
 * @see Component
 */
public class TargetControl extends Component {
    private Target target;
    private boolean isActive;
    private Node upgradeView;
    private Node startView;
    private String sound;
    private String startSound;
    private String deactivateSound;
    private int reactivateTime;
    private int timeControl;
    private int time;

    @Override
    public void onUpdate(double tpf) {
        timeControl+=1;
        if (timeControl%60==0)
            time += 1;
        if(deactivated()){
            FXGL.getAudioPlayer().playSound("TargetDeactivate.wav");
            entity.getViewComponent().setView(upgradeView);
            sound = deactivateSound;
            time = 0;
            isActive = false;
        }
        if (activated())
            activate();

        if((!target.isActive() && time==reactivateTime))
            activate();
    }

    public TargetControl(Target target, Node startView, Node upgradeView, String sound, int reactivateTime){
        time = timeControl = 0;
        isActive = true;
        this.reactivateTime = reactivateTime;
        this.target= target;
        this.startView = startView;
        this.upgradeView = upgradeView;
        this.sound = sound;
        this.startSound= sound;
        deactivateSound = sound.replace(".", "2.");
    }

    public void hit() {
        target.hit();
        FXGL.getAudioPlayer().playSound(sound);
    }

    private boolean activated(){
        return target.isActive() && !isActive;
    }

    private boolean deactivated(){
        return !target.isActive() && isActive;
    }

    private void activate() {
        target.reset();
        entity.setView(startView);
        sound = startSound;
        isActive = true;
    }
}
