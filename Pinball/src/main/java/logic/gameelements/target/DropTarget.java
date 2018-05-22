package logic.gameelements.target;

import logic.inverseVisitor.DropTargetBonusVisitor;
import logic.inverseVisitor.ExtraBallBonusVisitor;
import logic.inverseVisitor.ScoreVisitor;
import logic.visitor.Visitor;

public class DropTarget extends AbstractTarget {
    public DropTarget(){ super(100); }

    @Override
    public int hit() {
        if(this.isActive()) {
            setActive(false);
            notifyUp(new ScoreVisitor(this));
            if (getRandom().nextDouble() < 0.3)
                notifyUp(new ExtraBallBonusVisitor());
            notifyUp(new DropTargetBonusVisitor());
            return getScore();
        }
        return 0;
    }

    @Override
    public void accept(Visitor visitor) { visitor.visitDropTarget(this); }
}
