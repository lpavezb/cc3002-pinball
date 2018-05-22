package logic.inverseVisitor;

import controller.Game;
import logic.table.Table;

public interface IVisitor {

    void accept(Table table);

    void trigger(Game game);
}
