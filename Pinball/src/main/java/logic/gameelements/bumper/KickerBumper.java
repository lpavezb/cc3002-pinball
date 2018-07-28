package logic.gameelements.bumper;

import logic.visitor.Visitor;

/**
 * Class that represents a Bumper object.
 *
 * @author Lukas Pavez
 * @see Bumper
 * @see AbstractBumper
 * @see PopBumper
 */
public class KickerBumper extends AbstractBumper {

    /**
     * Class constructor
     */
    public KickerBumper(){ super(500, 1000, 5); }

    @Override
    public void accept(Visitor visitor) { visitor.visitKickerBumper(this); }

    @Override
    public boolean isKickerBumper() {
        return true;
    }
}