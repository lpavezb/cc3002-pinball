package logic.visitor;

import controller.Game;
import logic.gameelements.target.DropTarget;
import logic.table.Table;

import java.util.List;

public class DropTargetBonusVisitor implements Visitor {

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
