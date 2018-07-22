package logic.table;

import logic.gameelements.Hittable;
import logic.gameelements.bumper.*;
import logic.gameelements.target.*;
import logic.inverseVisitor.*;
import logic.visitor.*;

import java.util.*;

/**
 * Class that represents a Table
 *
 * @author Lukas Pavez
 * @see Table
 * @see NullTable
 */
public class GameTable extends Observable implements Table{
    private String name;
    private List<Hittable> elements = new ArrayList<>();

    /**
     * class constructor
     *
     * @param name name of the table
     * @param numberOfBumpers number of Bumpers of the table
     * @param prob probability of a Bumper to be a PopBumper
     * @param numberOfTargets number of Targets of the table
     * @param numberOfDropTargets number of DropTargets of the table
     * @param seed seed to set the Random for testing
     */

    public GameTable(String name, int numberOfBumpers, double prob, int numberOfTargets, int numberOfDropTargets, long seed){
        this.name = name;
        Random random = new Random();
        random.setSeed(seed);
        for(int i = 0; i < numberOfBumpers; i++)
            if(random.nextDouble() < prob)
                elements.add(new PopBumper());
            else
                elements.add(new KickerBumper());
        for(int j = 0; j < numberOfTargets; j++)
            elements.add(new SpotTarget());

        for(int k = 0; k < numberOfDropTargets; k++)
            elements.add(new DropTarget());

        for (Hittable hittable : elements) {
            hittable.setSeed(seed);
            hittable.addObserver(this);
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
    public List<Hittable> getHittables() {
        return elements;
    }

    @Override
    public void update(Observable o, Object arg) {
        ((IVisitor)arg).accept(this);
    }
}
