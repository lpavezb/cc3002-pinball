package logic.gameelements.bumper;

import logic.visitor.Visitor;

/**
 * Class that represents a Bumper object.
 *
 * @author Lukas Pavez
 * @see Bumper
 * @see AbstractBumper
 * @see KickerBumper
 */
public class PopBumper extends AbstractBumper {

    /**
     * Class constructor
     */
    public PopBumper(){ super(100, 300, 3); }

    @Override
    public void accept(Visitor visitor) { visitor.visitPopBumper(this); }
}
