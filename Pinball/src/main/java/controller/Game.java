package controller;

import logic.bonus.*;
import logic.gameelements.bumper.Bumper;
import logic.gameelements.target.Target;
import logic.table.*;

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

    private Table table;

    private int score;
    private int balls;

    private boolean playableTable = false;

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
        return playableTable;
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
     * Gets the list of bumpers in the current table.
     *
     * @return the list of bumpers
     * @see Bumper
     */
    public List<Bumper> getBumpers() {
        return table.getBumpers();
    }

    /**
     * Gets the list of targets in the current table.
     *
     * @return the list of targets
     * @see Target
     */
    public List<Target> getTargets() {
        return table.getTargets();
    }

    /**
     * Gets the name of the current table.
     *
     * @return the name of the current table
     */
    public String getTableName() {
        return table.getTableName();
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
     */
    public void setScore(int newScore) {
        score = newScore;
    }

    /**
     * Gets the current table.
     *
     * @return the current table
     * @see Table
     */
    public Table getCurrentTable() {
        return table;
    }

    /**
     * Sets a new table to play.
     *
     * @param newTable the new table
     */
    public void setGameTable(Table newTable) {
        playableTable = true;
        table = newTable;
    }



    /**
     * Increase the number of available balls and returns the new number.
     */
    public void addBall() {
        balls += 1;
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
        return balls == 0;
    }

}
