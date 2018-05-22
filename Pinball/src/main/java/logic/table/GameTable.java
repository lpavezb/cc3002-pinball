package logic.table;

import logic.gameelements.Hittable;
import logic.gameelements.bumper.*;
import logic.gameelements.target.*;
import logic.inverseVisitor.*;
import logic.visitor.*;

import java.util.*;

public class GameTable extends Observable implements Table{
    private String name;
    private List<Hittable> elements = new ArrayList<>();

    private Random random = new Random();

    public GameTable(String name, int numberOfBumpers, double prob, int numberOfTargets, int numberOfDropTargets){
        this.name = name;
        for(int i = 0; i < numberOfBumpers; i++)
            if(random.nextDouble() < prob){
                PopBumper newBumper = new PopBumper();
                elements.add(newBumper);
                newBumper.addObserver(this);
            }
            else{
                KickerBumper newBumper = new KickerBumper();
                elements.add(newBumper);
                newBumper.addObserver(this);
            }

        for(int j = 0; j < numberOfTargets; j++){
            SpotTarget newTarget = new SpotTarget();
            elements.add(newTarget);
            newTarget.addObserver(this);
        }

        for(int k = 0; k < numberOfDropTargets; k++) {
            DropTarget newTarget = new DropTarget();
            elements.add(newTarget);
            newTarget.addObserver(this);
        }
    }

    public GameTable(String name, int numberOfBumpers, double prob, int numberOfTargets, int numberOfDropTargets, long seed){
        this.name = name;
        random.setSeed(seed);
        for(int i = 0; i < numberOfBumpers; i++)
            if(random.nextDouble() < prob){
                PopBumper newBumper = new PopBumper();
                newBumper.setSeed(seed);
                elements.add(newBumper);
                newBumper.addObserver(this);
            }
            else{
                KickerBumper newBumper = new KickerBumper();
                newBumper.setSeed(seed);
                elements.add(newBumper);
                newBumper.addObserver(this);
            }

        for(int j = 0; j < numberOfTargets; j++){
            SpotTarget newTarget = new SpotTarget();
            newTarget.setSeed(seed);
            elements.add(newTarget);
            newTarget.addObserver(this);
        }

        for(int k = 0; k < numberOfDropTargets; k++) {
            DropTarget newTarget = new DropTarget();
            newTarget.setSeed(seed);
            elements.add(newTarget);
            newTarget.addObserver(this);
        }
    }

    @Override
    public String getTableName() { return name; }

    @Override
    public int getNumberOfDropTargets() {
        DropTargetVisitor v = new DropTargetVisitor();
        this.accept(v);
        return v.getNumberOfDropTargets(); }

    @Override
    public int getCurrentlyDroppedDropTargets() {
        DropTargetVisitor v = new DropTargetVisitor();
        this.accept(v);
        return v.getCurrentlyDroppedDropTargets();
    }

    @Override
    public List<Bumper> getBumpers() {
        BumperVisitor v = new BumperVisitor();
        this.accept(v);
        return v.getBumpers();
    }

    @Override
    public List<PopBumper> getPopBumpers() {
        PopBumperVisitor v = new PopBumperVisitor();
        this.accept(v);
        return v.getPopBumpers();
    }

    @Override
    public List<KickerBumper> getKickerBumpers() {
        KickerBumperVisitor v = new KickerBumperVisitor();
        this.accept(v);
        return v.getKickerBumpers();
    }

    @Override
    public List<Target> getTargets() {
        TargetVisitor v = new TargetVisitor();
        this.accept(v);
        return v.getTargets();
    }

    @Override
    public List<SpotTarget> getSpotTargets() {
        SpotTargetVisitor v = new SpotTargetVisitor();
        this.accept(v);
        return v.getSpotTargets();
    }

    @Override
    public List<DropTarget> getDropTargets() {
        DropTargetVisitor v = new DropTargetVisitor();
        this.accept(v);
        return v.getDropTargets();
    }

    private void accept(Visitor v) { v.visitTable(this); }

    @Override
    public void resetDropTargets() {
        DropTargetVisitor v = new DropTargetVisitor();
        this.accept(v);
        v.resetDropTargets();
    }

    @Override
    public void upgradeAllBumpers() {
        BumperVisitor v = new BumperVisitor();
        this.accept(v);
        v.upgradeAllBumpers();
    }

    @Override
    public boolean isPlayableTable() {
        return true;
    }

    @Override
    public void visitExtraBallBonus(ExtraBallBonusVisitor extraBallBonusVisitor) {
        setChanged();
        notifyObservers(extraBallBonusVisitor);
    }

    @Override
    public void visitJackPotBonus(JackPotBonusVisitor jackPotBonusVisitor) {
        setChanged();
        notifyObservers(jackPotBonusVisitor);
    }

    @Override
    public void visitDropTargetBonus(DropTargetBonusVisitor dropTargetBonusVisitor) {
        setChanged();
        notifyObservers(dropTargetBonusVisitor);
    }

    @Override
    public void visitScore(ScoreVisitor scoreVisitor) {
        setChanged();
        notifyObservers(scoreVisitor);
    }

    @Override
    public List<Hittable> getElements() {
        return elements;
    }

    @Override
    public void update(Observable o, Object arg) {
        ((IVisitor)arg).accept(this);
    }
}
