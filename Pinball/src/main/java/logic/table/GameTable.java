package logic.table;

import logic.gameelements.bumper.*;
import logic.gameelements.target.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Random;

public class GameTable extends Observable implements Table{
    private String name;
    private int numberOfDropTargets;
    private List<Bumper> bumpers = new ArrayList<>();
    private List<Target> targets = new ArrayList<>();
    private List<DropTarget> dropTargets = new ArrayList<>();
    private Random random = new Random();

    public GameTable(String name, int numberOfBumpers, double prob, int numberOfTargets, int numberOfDropTargets){
        this.name = name;
        this.numberOfDropTargets = numberOfDropTargets;
        for(int i = 0; i < numberOfBumpers; i++)
            if(random.nextDouble() < prob){
                PopBumper newBumper = new PopBumper();
                bumpers.add(newBumper);
                newBumper.addObserver(this);
            }
            else{
                KickerBumper newBumper = new KickerBumper();
                bumpers.add(newBumper);
                newBumper.addObserver(this);
            }

        for(int j = 0; j < numberOfTargets; j++){
            SpotTarget newTarget = new SpotTarget();
            targets.add(newTarget);
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
        this.numberOfDropTargets = numberOfDropTargets;
        random.setSeed(seed);
        for(int i = 0; i < numberOfBumpers; i++)
            if(random.nextDouble() < prob){
                PopBumper newBumper = new PopBumper();
                bumpers.add(newBumper);
                newBumper.addObserver(this);
            }
            else{
                KickerBumper newBumper = new KickerBumper();
                bumpers.add(newBumper);
                newBumper.addObserver(this);
            }

        for(int j = 0; j < numberOfTargets; j++){
            SpotTarget newTarget = new SpotTarget();
            targets.add(newTarget);
            newTarget.addObserver(this);
        }

        for(int k = 0; k < numberOfDropTargets; k++) {
            DropTarget newTarget = new DropTarget();
            targets.add(newTarget);
            dropTargets.add(newTarget);
            newTarget.addObserver(this);
        }
    }

    @Override
    public String getTableName() { return name; }

    @Override
    public int getNumberOfDropTargets() { return numberOfDropTargets; }

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
    public List<Target> getTargets() { return targets; }

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
    public void update(Observable o, Object arg) {
        setChanged();
        notifyObservers();
    }
}
