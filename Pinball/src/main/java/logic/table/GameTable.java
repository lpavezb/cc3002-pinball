package logic.table;

import logic.gameelements.bumper.*;
import logic.gameelements.target.*;
import logic.visitor.*;

import java.util.*;

public class GameTable extends Observable implements Table{
    private String name;
    private List<Bumper> bumpers = new ArrayList<>();
    private List<Target> targets = new ArrayList<>();
    private List<DropTarget> dropTargets = new ArrayList<>();
    private List<SpotTarget> spotTargets = new ArrayList<>();
    private List<KickerBumper> kickerBumpers = new ArrayList<>();
    private List<PopBumper> popBumpers = new ArrayList<>();
    private Random random = new Random();

    public GameTable(String name, int numberOfBumpers, double prob, int numberOfTargets, int numberOfDropTargets){
        this.name = name;
        for(int i = 0; i < numberOfBumpers; i++)
            if(random.nextDouble() < prob){
                PopBumper newBumper = new PopBumper();
                bumpers.add(newBumper);
                popBumpers.add(newBumper);
                newBumper.addObserver(this);
            }
            else{
                KickerBumper newBumper = new KickerBumper();
                bumpers.add(newBumper);
                kickerBumpers.add(newBumper);
                newBumper.addObserver(this);
            }

        for(int j = 0; j < numberOfTargets; j++){
            SpotTarget newTarget = new SpotTarget();
            targets.add(newTarget);
            spotTargets.add(newTarget);
            newTarget.addObserver(this);
        }

        for(int k = 0; k < numberOfDropTargets; k++) {
            DropTarget newTarget = new DropTarget();
            targets.add(newTarget);
            dropTargets.add(newTarget);
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
                bumpers.add(newBumper);
                popBumpers.add(newBumper);
                newBumper.addObserver(this);
            }
            else{
                KickerBumper newBumper = new KickerBumper();
                newBumper.setSeed(seed);
                bumpers.add(newBumper);
                kickerBumpers.add(newBumper);
                newBumper.addObserver(this);
            }

        for(int j = 0; j < numberOfTargets; j++){
            SpotTarget newTarget = new SpotTarget();
            newTarget.setSeed(seed);
            targets.add(newTarget);
            spotTargets.add(newTarget);
            newTarget.addObserver(this);
        }

        for(int k = 0; k < numberOfDropTargets; k++) {
            DropTarget newTarget = new DropTarget();
            targets.add(newTarget);
            newTarget.setSeed(seed);
            dropTargets.add(newTarget);
            newTarget.addObserver(this);
        }
    }

    @Override
    public String getTableName() { return name; }

    @Override
    public int getNumberOfDropTargets() { return dropTargets.size(); }

    @Override
    public int getCurrentlyDroppedDropTargets() {
        int res = 0;
        for (Target target : dropTargets)
            if (!target.isActive())
                res += 1;
        return res;
    }

    @Override
    public List<Bumper> getBumpers() { return bumpers; }

    @Override
    public List<PopBumper> getPopBumpers() { return popBumpers; }

    @Override
    public List<KickerBumper> getKickerBumpers() { return kickerBumpers; }

    @Override
    public List<Target> getTargets() { return targets; }

    @Override
    public List<SpotTarget> getSpotTargets() { return spotTargets; }

    @Override
    public List<DropTarget> getDropTargets() { return dropTargets; }

    @Override
    public void resetDropTargets() {
        for (Target target : dropTargets)
            target.reset();
    }

    @Override
    public void upgradeAllBumpers() {
        for(Bumper bumper : bumpers)
            bumper.upgrade();
    }

    @Override
    public boolean isPlayableTable() {
        return !bumpers.isEmpty() || !targets.isEmpty();
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
    public void update(Observable o, Object arg) {
        ((Visitor)o).accept(this);
    }
}
