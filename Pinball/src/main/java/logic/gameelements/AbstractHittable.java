package logic.gameelements;

import logic.inverseVisitor.IVisitor;

import java.util.Observable;
import java.util.Random;

/**
 * Abstract class that represents a Hittable object.
 *
 * @author Lukas Pavez
 * @see Hittable
 * @see logic.gameelements.target.Target
 * @see logic.gameelements.bumper.Bumper
 */
public abstract class AbstractHittable extends Observable implements Hittable{
    private int score;
    private Random random = new Random();

    @Override
    public int getScore() { return score; }

    @Override
    public void setSeed(long seed) { this.random.setSeed(seed); }

    @Override
    public Random getRandom(){ return random;}

    @Override
    public void setScore(int score){ this.score = score; }

    /**
     * Method to notify an observer
     * @param visitor IVisitor to send to the observer
     */
    public void notifyUp(IVisitor visitor){
        setChanged();
        notifyObservers(visitor);
    }
}
