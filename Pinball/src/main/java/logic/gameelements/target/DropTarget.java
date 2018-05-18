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
            notifyUp(new ExtraBallBonusVisitor());
        notifyUp(new DropTargetBonusVisitor());
        return getScore();
    }
}
