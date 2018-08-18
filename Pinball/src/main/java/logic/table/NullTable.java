package logic.table;

import logic.gameelements.Hittable;
import logic.gameelements.bumper.*;
import logic.gameelements.target.*;
import logic.inverseVisitor.*;

import java.util.*;

/**
 * Class that represents a Table
 *
 * @author Lukas Pavez
 * @see Table
 * @see NullTable
 */
public class NullTable extends Observable implements Table {
    @Override
    public String getTableName() { return ""; }

    @Override
    public int getNumberOfDropTargets() { return 0; }

    @Override
    public int getCurrentlyDroppedDropTargets() { return 0; }

    @Override
    public List<Bumper> getBumpers() { return new ArrayList<>(); }

    @Override
    public List<PopBumper> getPopBumpers() { return new ArrayList<>(); }

    @Override
    public List<KickerBumper> getKickerBumpers() { return new ArrayList<>(); }

    @Override
    public List<Target> getTargets() { return new ArrayList<>(); }

    @Override
    public List<SpotTarget> getSpotTargets() { return new ArrayList<>(); }
    @Override
    public List<DropTarget> getDropTargets() { return new ArrayList<>(); }

    @Override
    public void resetDropTargets() { }

    @Override
    public void upgradeAllBumpers() { }

    @Override
    public boolean isPlayableTable() { return false; }

    @Override
    public void visitExtraBallBonus(ExtraBallBonusVisitor extraBallBonusVisitor) { }

    @Override
    public void visitJackPotBonus(JackPotBonusVisitor jackPotBonusVisitor) { }

    @Override
    public void visitDropTargetBonus(DropTargetBonusVisitor dropTargetBonusVisitor) { }

    @Override
    public void visitScore(ScoreVisitor scoreVisitor) { }

    @Override
    public List<Hittable> getHittables() {
        return new ArrayList<>();
    }

    @Override
    public void update(Observable o, Object arg) { }
}
