package logic.visitor;

import logic.gameelements.bumper.*;

import java.util.*;

public class BumperVisitor extends Visitor{
    private List<Bumper> bumpers;

    public BumperVisitor(){ bumpers = new ArrayList<>(); }

    public void visitKickerBumper(KickerBumper kickerBumper) { bumpers.add(kickerBumper); }

    public void visitPopBumper(PopBumper popBumper) { bumpers.add(popBumper); }

    public List<Bumper> getBumpers() { return bumpers; }

    public void upgradeAllBumpers() {
        for(Bumper bumper : bumpers)
            bumper.upgrade();
    }
}
