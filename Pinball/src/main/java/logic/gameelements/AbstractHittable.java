package logic.gameelements;

import logic.visitor.ScoreVisitor;
import logic.visitor.Visitor;

import java.util.Observable;
import java.util.Random;

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

    public void notifyUp(Visitor visitor){
        setChanged();
        notifyObservers(visitor);
    }
}
