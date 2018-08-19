package controller;

import logic.bonus.*;
import logic.table.*;
import logic.inverseVisitor.IVisitor;

import java.util.*;

/**
 * Game logic controller class.
 *
 * @author Juan-Pablo Silva
 * @author Lukas Pavez
 *
 * @see Observer
 */
public class Game implements Observer {
    private Bonus dropTargetBonus;
    private Bonus extraBallBonus;
    private Bonus jackPotBonus;

    private Table table;

    private int score;
    private int balls;

    /**
     * Class constructor
     */
    public Game(){
        dropTargetBonus = new DropTargetBonus();
        extraBallBonus = new ExtraBallBonus();
        jackPotBonus = new JackPotBonus();

        table = new NullTable();

        score = 0;
        balls = 5;
    }

    /**
     * Gets whether the current table is playable or not.
     *
     * @return true if the current table is playable, false otherwise
     */
    public boolean isPlayableTable() {
        return table.isPlayableTable();
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
     * Gets the name of the current table.
     *
     * @return the name of the current table
     */
    public String getTableName() { return table.getTableName(); }

    /**
     * Gets the current number of available balls to play.
     *
     * @return the number of available balls
     */
    public int getAvailableBalls() { return balls; }

    /**
     * Gets the points earned so far.
     *
     * @return the earned score
     */
    public int getCurrentScore() { return score; }

    /**
     * Gets the current table.
     *
     * @return the current table
     * @see Table
     */
    public Table getCurrentTable() { return table; }

    /**
     * Sets a new table to play.
     *
     * @param table the new table
     */
    public void setGameTable(Table table) {
        table.addObserver(this);
        this.table = table;
    }

    /**
     * Increase the number of available balls.
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
        balls = (balls==0) ? 0 : balls-1;
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

    /**
     * Adds the given value to the current score
     *
     * @param score the new table
     */
    public void addScore(int score) { this.score += score; }


    /**
     * Trigger ExtraBallBonus
     */
    public void triggerExtraBallBonus() { extraBallBonus.trigger(this); }

    /**
     * Trigger DropTargetBonus
     */
    public void triggerDropTargetBonus() { dropTargetBonus.trigger(this); }

    /**
     * Trigger JackPotBonus
     */
    public void triggerJackPotBonus() { jackPotBonus.trigger(this); }

    @Override
    public void update(Observable o, Object arg) { ((IVisitor)arg).trigger(this); }
}
