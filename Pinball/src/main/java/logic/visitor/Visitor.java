package logic.visitor;

import logic.gameelements.Hittable;
import logic.gameelements.bumper.*;
import logic.gameelements.target.*;
import logic.table.Table;

/**
 * Class that represents a Visitor object
 *
 * @author Lukas Pavez
 */
public abstract class Visitor {

    /**
     * Visits the elements in the Table
     *
     * @param table table to visit
     */
    public void visitTable(Table table) {
        for(Hittable hittable : table.getHittables())
            hittable.accept(this);
    }

    /**
     * Visits a KickerBumper
     *
     * @param kickerBumper
     */
    public void visitKickerBumper(KickerBumper kickerBumper) { }

    /**
     * Visits a PopBumper
     *
     * @param popBumper
     */
    public void visitPopBumper(PopBumper popBumper) { }

    /**
     * Visits a DropTarget
     *
     * @param dropTarget
     */
    public void visitDropTarget(DropTarget dropTarget) { }

    /**
     * Visits a SpotTarget
     *
     * @param spotTarget
     */
    public void visitSpotTarget(SpotTarget spotTarget) { }
}