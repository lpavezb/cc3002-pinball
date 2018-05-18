package logic.gameelements.target;

import logic.visitor.DropTargetBonusVisitor;
import logic.visitor.ExtraBallBonusVisitor;

public class DropTarget extends AbstractTarget {
    public DropTarget(){ super(100); }

    @Override
    public int hit() {
        setActive(false);
        double random = Math.random();
        if(random < 0.3)
            setChanged();
            notifyObservers(new ExtraBallBonusVisitor());
        setChanged();
        notifyObservers(new DropTargetBonusVisitor());
        return getScore();
    }
}
