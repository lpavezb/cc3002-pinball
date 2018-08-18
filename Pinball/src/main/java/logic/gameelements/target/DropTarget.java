package logic.gameelements.target;

import logic.inverseVisitor.*;
import logic.visitor.Visitor;

/**
 * Class that represents a Target object.
 *
 * @author Lukas Pavez
 * @see Target
 * @see AbstractTarget
 * @see SpotTarget
 */
public class DropTarget extends AbstractTarget {

    /**
     * Class constructor
     */
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
