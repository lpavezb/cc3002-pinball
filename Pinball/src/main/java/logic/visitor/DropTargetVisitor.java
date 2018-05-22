package logic.visitor;

import logic.gameelements.target.DropTarget;

import java.util.ArrayList;
import java.util.List;

public class DropTargetVisitor extends Visitor{
    private List<DropTarget> dropTargets;
    public DropTargetVisitor(){ dropTargets = new ArrayList<>(); }

    @Override
    public void visitDropTarget(DropTarget dropTarget) { dropTargets.add(dropTarget); }

    public List<DropTarget> getDropTargets(){ return dropTargets; }

    public int getCurrentlyDroppedDropTargets(){
        int res = 0;
        for (DropTarget target : dropTargets)
            if (!target.isActive())
                res += 1;
        return res;
    }

    public int getNumberOfDropTargets() { return dropTargets.size(); }

    public void resetDropTargets() {
        for (DropTarget target : dropTargets)
            target.reset();
    }
}
