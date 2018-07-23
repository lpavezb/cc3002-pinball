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

    public TableFactory setName(String name) {
        this.name = name;
        return this;
    }

    public TableFactory setNumberOfBumpers(int numberOfBumpers) {
        this.numberOfBumpers = numberOfBumpers;
        return this;
    }

    public TableFactory setProb(double prob) {
        this.prob = prob;
        return this;
    }

    public TableFactory setNumberOfTargets(int numberOfTargets) {
        this.numberOfTargets = numberOfTargets;
        return this;
    }

    public TableFactory setNumberOfDropTargets(int numberOfDropTargets) {
        this.numberOfDropTargets = numberOfDropTargets;
        return this;
    }

    public TableFactory setSeed(long seed) {
        this.seed = seed;
        return this;
    }

    public Table createTable(){
        return new GameTable(name, numberOfBumpers, prob, numberOfTargets, numberOfDropTargets, seed);
    }
}
