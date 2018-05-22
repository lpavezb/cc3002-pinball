package logic.table;

import logic.gameelements.Hittable;
import logic.gameelements.bumper.Bumper;
import logic.gameelements.bumper.KickerBumper;
import logic.gameelements.bumper.PopBumper;
import logic.gameelements.target.DropTarget;
import logic.gameelements.target.Target;
import logic.inverseVisitor.DropTargetBonusVisitor;
import logic.inverseVisitor.ExtraBallBonusVisitor;
import logic.inverseVisitor.JackPotBonusVisitor;
import logic.inverseVisitor.ScoreVisitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class NullTable implements Table {
    @Override
    public String getTableName() { return "There is no table"; }

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
    public List<logic.gameelements.target.SpotTarget> getSpotTargets() { return new ArrayList<>(); }
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
    public void addObserver(Observer o) { }

    @Override
    public List<Hittable> getElements() {
        return new ArrayList<>();
    }

    @Override
    public void update(Observable o, Object arg) { }
}
