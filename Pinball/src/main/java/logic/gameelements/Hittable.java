package logic.gameelements;

import logic.visitor.Visitor;

import java.util.*;

/**
 * Interface that represents a hittable object.
 *
 * <p>Objects that are game elements should implement this interface.</p>
 *
 * @author Juan-Pablo Silva
 * @author Lukas Pavez
 * @see logic.gameelements.bumper.Bumper
 * @see logic.gameelements.target.Target
 */
public interface Hittable {
    /**
     * Defines that an object have been hit.
     * Implementations should consider the events that a hit to an object can trigger.
     *
     * @return the score the player obtained hitting the object
     */
    int hit();

    /**
     * Defines that a hittable object has to have a score when it is hit.
     *
     * @return the current score of the object when hit
     */
    int getScore();

    /**
     * Sets the seed to the random variable of the Hittable
     *
     * @param seed seed to set
     */
    void setSeed(long seed);

    /**
     * Gets the Random object of the Hittable
     *
     * @see Random
     * @return Random
     */
    Random getRandom();

    /**
     * Sets the score of the Hittable
     *
     * @param score score to set
     */
    void setScore(int score);

    /**
     * Accepts the visitor
     * @param visitor
     */
    void accept(Visitor visitor);

    /**
     * Add an Observer
     *
     * @see Observer
     * @param o Observer
     */
    void addObserver(Observer o);
}
