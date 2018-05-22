package logic.table;

import logic.gameelements.Hittable;
import logic.gameelements.bumper.*;
import logic.gameelements.target.*;
import logic.inverseVisitor.*;

import java.util.*;

/**
 * Interface that represents the basics of a table to be played on.
 *
 * @author Juan-Pablo Silva
 */
public interface Table extends Observer {
    /**
     * Gets the table name.
     *
     * @return the table's name
     */
    String getTableName();

    /**
     * Gets the number of {@link DropTarget} in the table.
     *
     * @return the number of DropTargets in the table
     */
    int getNumberOfDropTargets();

    /**
     * Gets the number of {@link DropTarget} that are currently dropped or inactive.
     *
     * @return the number of DropTargets that are currently inactive
     */
    int getCurrentlyDroppedDropTargets();

    /**
     * Gets the {@link List} of {@link Bumper}s in the table.
     *
     * @return the bumpers in the table
     */
    List<Bumper> getBumpers();

    List<PopBumper> getPopBumpers();

    List<KickerBumper> getKickerBumpers();

    /**
     * Gets the {@link List} of {@link Target}s in the table.
     *
     * @return the targets in the table
     */
    List<Target> getTargets();

    List<SpotTarget> getSpotTargets();

    List<DropTarget> getDropTargets();

    /**
     * Resets all {@link DropTarget} in the table. Make them active.
     */
    void resetDropTargets();

    /**
     * Upgrade all {@link Bumper}s in the table.
     */
    void upgradeAllBumpers();

    /**
     * Gets whether the table is playable or not.
     *
     * @return true if the table is playable, false otherwise
     */
    boolean isPlayableTable();

    void visitExtraBallBonus(ExtraBallBonusVisitor extraBallBonusVisitor);

    void visitJackPotBonus(JackPotBonusVisitor jackPotBonusVisitor);

    void visitDropTargetBonus(DropTargetBonusVisitor dropTargetBonusVisitor);

    void visitScore(ScoreVisitor scoreVisitor);

    void addObserver(Observer o);

    List<Hittable> getElements();
}
