package controller;

import logic.bonus.*;
import logic.gameelements.bumper.Bumper;
import logic.gameelements.target.Target;
import logic.table.Table;

import java.util.List;

/**
 * Game logic controller class.
 *
 * @author Juan-Pablo Silva
 */
public class Game {
    private Bonus dropTargetBonus;
    private Bonus extraBallBonus;
    private Bonus jackPotBonus;

    private int score;
    private int balls;

    public Game(){
        dropTargetBonus = new DropTargetBonus();
        extraBallBonus = new ExtraBallBonus();
        jackPotBonus = new JackPotBonus();

        score = 0;
        balls = 5;
    }
    /**
     * Gets whether the current table is playable or not.
     *
     * @return true if the current table is playable, false otherwise
     */
    public boolean isPlayableTable() {
        //TODO
        return false;
    }

    /**
     * Gets the instance of {@link logic.bonus.DropTargetBonus} currently in the game.
     *
     * @return the DropTargetBonus instance
     */
    public Bonus getDropTargetBonus() {
        return dropTargetBonus;
    }

    /**
     * Gets the instance of {@link logic.bonus.ExtraBallBonus} currently in the game.
     *
     * @return the ExtraBallBonus instance
     */
    public Bonus getExtraBallBonus() {
        return extraBallBonus;
    }

    /**
     * Gets the instance of {@link logic.bonus.JackPotBonus} currently in the game.
     *
     * @return the JackPotBonus instance
     */
    public Bonus getJackPotBonus() {
        return jackPotBonus;
    }

    /**
     * Creates a new table with the given parameters with no targets.
     *
     * @param name            the name of the table
     * @param numberOfBumpers the number of bumpers in the table
     * @param prob            the probability a {@link logic.gameelements.bumper.PopBumper}
     * @return a new table determined by the parameters
     */
    public Table newPlayableTableWithNoTargets(String name, int numberOfBumpers, double prob) {
        //TODO
        return null;
    }

    /**
     * Creates a new table with the given parameters.
     *
     * @param name                the name of the table
     * @param numberOfBumpers     the number of bumpers in the table
     * @param prob                the probability a {@link logic.gameelements.bumper.PopBumper}
     * @param numberOfTargets     the number of {@link logic.gameelements.target.SpotTarget}
     * @param numberOfDropTargets the number of {@link logic.gameelements.target.DropTarget}
     * @return a new table determined by the parameters
     */
    public Table newFullPlayableTable(String name, int numberOfBumpers, double prob, int numberOfTargets, int numberOfDropTargets) {
        //TODO
        return null;
    }

    /**
     * Gets the list of bumpers in the current table.
     *
     * @return the list of bumpers
     * @see Bumper
     */
    public List<Bumper> getBumpers() {
        //TODO
        return null;
    }

    /**
     * Gets the list of targets in the current table.
     *
     * @return the list of targets
     * @see Target
     */
    public List<Target> getTargets() {
        //TODO
        return null;
    }

    /**
     * Gets the name of the current table.
     *
     * @return the name of the current table
     */
    public String getTableName() {
        //TODO
        return null;
    }

    /**
     * Gets the current number of available balls to play.
     *
     * @return the number of available balls
     */
    public int getAvailableBalls() {
        return balls;
    }

    /**
     * Gets the points earned so far.
     *
     * @return the earned score
     */
    public int getCurrentScore() { return score; }

    /**
     * Sets the points earned so far.
     *
     * @return the actual score
     */
    public int setScore(int newScore) {
        score = newScore;
        return score;
    }

    /**
     * Gets the current table.
     *
     * @return the current table
     * @see Table
     */
    public Table getCurrentTable() {
        //TODO
        return null;
    }

    /**
     * Sets a new table to play.
     *
     * @param newTable the new table
     */
    public void setGameTable(Table newTable) {
        //TODO
    }



    /**
     * Increase the number of available balls and returns the new number.
     *
     * @return the new number of available balls
     */
    public int addBall() {
        balls += 1;
        return balls;
    }

    /**
     * Reduces the number of available balls and returns the new number.
     *
     * @return the new number of available balls
     */
    public int dropBall() {
        balls -= 1;
        return balls;
    }

    /**
     * Checks whether the game is over or not. A game is over when the number of available balls are 0.
     *
     * @return true if the game is over, false otherwise
     */
    public boolean gameOver() {
        //TODO
        return false;
    }

}
