package logic.inverseVisitor;

import controller.Game;
import logic.gameelements.target.DropTarget;
import logic.table.Table;

import java.util.List;

/**
 * Class that represents a IVisitor object
 *
 * @author Lukas Pavez
 * @see IVisitor
 */
public class DropTargetBonusVisitor implements IVisitor {

    @Override
    public void accept(Table table) { table.visitDropTargetBonus(this); }

    @Override
    public void trigger(Game game) {
        Table gameTable = game.getCurrentTable();
        List<DropTarget> dropTargets = gameTable.getDropTargets();
        for(DropTarget dropTarget : dropTargets)
            if(dropTarget.isActive())
                return;
        game.triggerDropTargetBonus();
    }
}
