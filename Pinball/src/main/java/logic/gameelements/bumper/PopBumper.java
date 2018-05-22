package logic.gameelements.bumper;

import logic.visitor.Visitor;

public class PopBumper extends AbstractBumper {

    public PopBumper(){ super(100, 300, 3); }

    @Override
    public void accept(Visitor visitor) { visitor.visitPopBumper(this); }
}
