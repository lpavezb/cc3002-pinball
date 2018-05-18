package logic.gameelements.target;

import logic.visitor.DropTargetBonusVisitor;
import logic.visitor.ExtraBallBonusVisitor;
import logic.visitor.ScoreVisitor;

public class DropTarget extends AbstractTarget {
    public DropTarget(){ super(100); }

    @Override
    public int hit() {
        setActive(false);
        notifyUp(new ScoreVisitor(this));
        if(getRandom().nextDouble() < 0.3)
            notifyUp(new ExtraBallBonusVisitor());
        notifyUp(new DropTargetBonusVisitor());
        return getScore();
    }
}
