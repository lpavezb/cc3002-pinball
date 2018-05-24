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
 * @author Lukas Pavez
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
     * Gets the {@link List} of {@link Hittable}s in the table.
     *
     * @return the hittables in the table
     */
    List<Hittable> getHittables();

    /**
     * Gets the {@link List} of {@link Bumper}s in the table.
     *
     * @return the bumpers in the table
     */
    List<Bumper> getBumpers();

    /**
     * Gets the {@link List} of {@link PopBumper}s in the table.
     *
     * @return the popBumpers in the table
     */
    List<PopBumper> getPopBumpers();

    /**
     * Gets the {@link List} of {@link KickerBumper}s in the table.
     *
     * @return the kickerBumpers in the table
     */
    List<KickerBumper> getKickerBumpers();

    /**
     * Gets the {@link List} of {@link Target}s in the table.
     *
     * @return the targets in the table
     */
    List<Target> getTargets();

    /**
     * Gets the {@link List} of {@link SpotTarget}s in the table.
     *
     * @return the spotTargets in the table
     */
    List<SpotTarget> getSpotTargets();

    /**
     * Gets the {@link List} of {@link DropTarget}s in the table.
     *
     * @return the dropTargets in the table
     */
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

    /**
     * Method to use the visitor pattern, sends a visitor to Game
     *
     * @param extraBallBonusVisitor visitor to send
     */
    void visitExtraBallBonus(ExtraBallBonusVisitor extraBallBonusVisitor);

    /**
     * Method to use the visitor pattern, sends a visitor to Game
     *
     * @param jackPotBonusVisitor visitor to send
     */
    void visitJackPotBonus(JackPotBonusVisitor jackPotBonusVisitor);

    /**
     * Method to use the visitor pattern, sends a visitor to Game
     *
     * @param dropTargetBonusVisitor visitor to send
     */
    void visitDropTargetBonus(DropTargetBonusVisitor dropTargetBonusVisitor);

    /**
     * Method to use the visitor pattern, sends a visitor to Game
     *
     * @param scoreVisitor visitor to send
     */
    void visitScore(ScoreVisitor scoreVisitor);

    /**
     * Add an Observer
     *
     * @see Observer
     * @param o Observer
     */
    void addObserver(Observer o);
}
