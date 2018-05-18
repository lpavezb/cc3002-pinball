package logic.gameelements.target;

import logic.visitor.JackPotBonusVisitor;

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
}
