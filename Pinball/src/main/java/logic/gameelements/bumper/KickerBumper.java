package logic.gameelements.bumper;

import logic.visitor.Visitor;

public class KickerBumper extends AbstractBumper {

    public KickerBumper(){ super(500, 1000, 5); }

    @Override
    public void accept(Visitor visitor) { visitor.visitKickerBumper(this); }
}