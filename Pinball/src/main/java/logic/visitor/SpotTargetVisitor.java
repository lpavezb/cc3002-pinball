package logic.visitor;

import logic.gameelements.target.SpotTarget;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents a Visitor object
 *
 * @author Lukas Pavez
 */
public class SpotTargetVisitor extends Visitor{
    private List<SpotTarget> spotTargets;

    /**
     * Class constructor
     */
    public SpotTargetVisitor(){ spotTargets = new ArrayList<>(); }

    @Override
    public void visitSpotTarget(SpotTarget spotTarget) { spotTargets.add(spotTarget); }

    /**
     * Gets the visited SpotTargets
     *
     * @return the visited SpotTargets
     */
    public List<SpotTarget> getSpotTargets(){ return spotTargets; }
}
