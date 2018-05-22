package logic.visitor;

import logic.gameelements.target.*;

import java.util.*;

public class TargetVisitor extends Visitor {
    private List<Target> targets;

    public TargetVisitor(){ targets = new ArrayList<>(); }

    @Override
    public void visitDropTarget(DropTarget dropTarget) { targets.add(dropTarget); }

    @Override
    public void visitSpotTarget(SpotTarget spotTarget) { targets.add(spotTarget); }

    public List<Target> getTargets() { return targets; }
}
