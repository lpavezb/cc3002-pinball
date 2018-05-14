package logic.table;

import logic.gameelements.bumper.*;
import logic.gameelements.target.*;

import java.util.ArrayList;
import java.util.List;

public class GameTable implements Table {
    private String name;
    private int nDropTargets;
    private List<Bumper> bumpers = new ArrayList<>();
    private List<Target> targets = new ArrayList<>();

    public GameTable(String aName, int numberOfBumpers, double prob, int numberOfTargets, int numberOfDropTargets){
        name = aName;
        double random = Math.random();
        nDropTargets = numberOfDropTargets;
        for(int i = 0; i < numberOfBumpers; i++)
            if(random < prob)
                bumpers.add(new PopBumper());
            else
                bumpers.add(new KickerBumper());

        for(int j = 0; j < numberOfTargets; j++)
            targets.add(new SpotTarget());

        for(int k = 0; k < numberOfDropTargets; k++)
            targets.add(new DropTarget());
    }

    @Override
    public String getTableName() {
        return name;
    }

    @Override
    public int getNumberOfDropTargets() {
        return nDropTargets;
    }

    @Override
    public int getCurrentlyDroppedDropTargets() {
        //TODO: visitor(?)
        return 0;
    }

    @Override
    public List<Bumper> getBumpers() {
        return bumpers;
    }

    @Override
    public List<Target> getTargets() {
        return targets;
    }

    @Override
    public void resetDropTargets() {
        //TODO: visitor(?)
    }

    @Override
    public void upgradeAllBumpers() {
        for(Bumper bumper : bumpers)
            bumper.upgrade();
    }

    @Override
    public boolean isPlayableTable() {
        //TODO: ask ucursos
        return !bumpers.isEmpty() || !targets.isEmpty();
    }
}
