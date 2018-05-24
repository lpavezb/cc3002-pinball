package logic.visitor;

import logic.gameelements.bumper.KickerBumper;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents a Visitor object
 *
 * @author Lukas Pavez
 */
public class KickerBumperVisitor extends Visitor {
    private List<KickerBumper> kickerBumpers;

    /**
     * Class constructor
     */
    public KickerBumperVisitor(){ kickerBumpers = new ArrayList<>(); }

    @Override
    public void visitKickerBumper(KickerBumper kickerBumper) { kickerBumpers.add(kickerBumper); }

    /**
     * Gets the visited KickerBumpers
     *
     * @return the visited KickerBumpers
     */
    public List<KickerBumper> getKickerBumpers(){ return kickerBumpers; }
}
