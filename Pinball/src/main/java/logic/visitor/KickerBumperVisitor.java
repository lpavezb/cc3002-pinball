package logic.visitor;

import logic.gameelements.bumper.KickerBumper;

import java.util.ArrayList;
import java.util.List;

public class KickerBumperVisitor extends Visitor {
    private List<KickerBumper> kickerBumpers;
    public KickerBumperVisitor(){ kickerBumpers = new ArrayList<>(); }

    @Override
    public void visitKickerBumper(KickerBumper kickerBumper) { kickerBumpers.add(kickerBumper); }

    public List<KickerBumper> getKickerBumpers(){ return kickerBumpers; }
}
