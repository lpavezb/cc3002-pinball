package logic.visitor;

import logic.gameelements.target.DropTarget;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents a Visitor object
 *
 * @author Lukas Pavez
 */
public class DropTargetVisitor extends Visitor{
    private List<DropTarget> dropTargets;

    /**
     * Class constructor
     */
    public DropTargetVisitor(){ dropTargets = new ArrayList<>(); }

    @Override
    public void visitDropTarget(DropTarget dropTarget) { dropTargets.add(dropTarget); }

    /**
     * Gets the visited DropTargets
     *
     * @return the visited DropTargets
     */
    public List<DropTarget> getDropTargets(){ return dropTargets; }

    /**
     * Gets the number of dropped DropTargets
     *
     * @return number of dropped DropTargets
     */
    public int getCurrentlyDroppedDropTargets(){
        int res = 0;
        for (DropTarget target : dropTargets)
            if (!target.isActive())
                res += 1;
        return res;
    }

    /**
     * Gets the number of DropTargets
     *
     * @return number of DropTargets
     */
    public int getNumberOfDropTargets() { return dropTargets.size(); }

    /**
     * Reset all DropTargets
     */
    public void resetDropTargets() {
        for (DropTarget target : dropTargets)
            target.reset();
    }
}
