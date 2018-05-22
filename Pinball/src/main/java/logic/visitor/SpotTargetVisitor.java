package logic.visitor;

import logic.gameelements.target.SpotTarget;

import java.util.ArrayList;
import java.util.List;

public class SpotTargetVisitor extends Visitor{
    private List<SpotTarget> spotTargets;
    public SpotTargetVisitor(){ spotTargets = new ArrayList<>(); }

    @Override
    public void visitSpotTarget(SpotTarget spotTarget) { spotTargets.add(spotTarget); }

    public List<SpotTarget> getSpotTargets(){ return spotTargets; }
}
