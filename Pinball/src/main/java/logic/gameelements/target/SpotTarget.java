package logic.gameelements.target;

import logic.inverseVisitor.JackPotBonusVisitor;
import logic.visitor.Visitor;

public class SpotTarget extends AbstractTarget {
    public SpotTarget(){
        super(0);
    }

    @Override
    public int hit() {
        setActive(false);
        setChanged();
        notifyObservers(new JackPotBonusVisitor());
        return getScore();
    }

    @Override
    public void accept(Visitor visitor) { visitor.visitSpotTarget(this); }
}
