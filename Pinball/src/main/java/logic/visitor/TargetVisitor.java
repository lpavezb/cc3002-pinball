package logic.visitor;

import logic.gameelements.target.*;

import java.util.*;

/**
 * Class that represents a Visitor object
 *
 * @author Lukas Pavez
 */
public class TargetVisitor extends Visitor {
    private List<Target> targets;

    /**
     * Class constructor
     */
    public TargetVisitor(){ targets = new ArrayList<>(); }

    @Override
    public void visitDropTarget(DropTarget dropTarget) { targets.add(dropTarget); }

    @Override
    public void visitSpotTarget(SpotTarget spotTarget) { targets.add(spotTarget); }

    /**
     * Gets the visited Targets
     *
     * @return list of Targets
     */
    public List<Target> getTargets() { return targets; }
}
