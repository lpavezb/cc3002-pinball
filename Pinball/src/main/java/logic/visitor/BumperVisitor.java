package logic.visitor;

import logic.gameelements.bumper.*;

import java.util.*;

/**
 * Class that represents a Visitor object
 *
 * @author Lukas Pavez
 */
public class BumperVisitor extends Visitor{
    private List<Bumper> bumpers;

    /**
     * Class constructor
     */
    public BumperVisitor(){ bumpers = new ArrayList<>(); }

    @Override
    public void visitKickerBumper(KickerBumper kickerBumper) { bumpers.add(kickerBumper); }

    @Override
    public void visitPopBumper(PopBumper popBumper) { bumpers.add(popBumper); }

    /**
     * Gets the visited Bumpers
     *
     * @return list of Bumpers
     */
    public List<Bumper> getBumpers() { return bumpers; }

    /**
     * Upgrades all visited Bumpers
     */
    public void upgradeAllBumpers() {
        for(Bumper bumper : bumpers)
            bumper.upgrade();
    }
}
