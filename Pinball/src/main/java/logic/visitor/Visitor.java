package logic.visitor;

import controller.Game;
import logic.table.Table;

public interface Visitor {

    void accept(Table table);

    void triggerBonus(Game game);
}
