package logic.visitor;

import logic.gameelements.Hittable;
import logic.gameelements.bumper.*;
import logic.gameelements.target.*;
import logic.table.Table;


public abstract class Visitor {
    public void visitTable(Table table) {
        for(Hittable hittable : table.getElements())
            hittable.accept(this);
    }

    public void visitKickerBumper(KickerBumper kickerBumper) {

    }

    public void visitPopBumper(PopBumper popBumper) {

    }

    public void visitDropTarget(DropTarget dropTarget) {

    }

    public void visitSpotTarget(SpotTarget spotTarget) {

    }
}