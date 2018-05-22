package logic.gameelements.target;

import logic.inverseVisitor.JackPotBonusVisitor;
import logic.visitor.Visitor;

public class SpotTarget extends AbstractTarget {
    public SpotTarget(){
        super(0);
    }

    @Override
    public int hit() {
        if(this.isActive()) {
            setActive(false);
            setChanged();
            notifyObservers(new JackPotBonusVisitor());
            return getScore();
        }
        return 0;
    }

    @Override
    public void accept(Visitor visitor) { visitor.visitSpotTarget(this); }
}
