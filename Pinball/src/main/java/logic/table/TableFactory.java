package logic.table;

import java.util.Random;

public class TableFactory {
    private String name;
    private int numberOfBumpers;
    private double prob;
    private int numberOfTargets;
    private int numberOfDropTargets;
    private long seed;

    public TableFactory(){
        name = "Table";
        numberOfBumpers = 10;
        prob = 0.5;
        numberOfTargets = 3;
        numberOfDropTargets = 3;
        seed = new Random().nextLong();
    }

    public void setName(String name) { this.name = name; }

    public void setNumberOfBumpers(int numberOfBumpers) { this.numberOfBumpers = numberOfBumpers; }

    public void setProb(double prob) { this.prob = prob; }

    public void setNumberOfTargets(int numberOfTargets) { this.numberOfTargets = numberOfTargets; }

    public void setNumberOfDropTargets(int numberOfDropTargets) { this.numberOfDropTargets = numberOfDropTargets; }

    public void setSeed(long seed) { this.seed = seed; }

    public Table createTable(){
        return new GameTable(name, numberOfBumpers, prob, numberOfTargets, numberOfDropTargets, seed);
    }
}
