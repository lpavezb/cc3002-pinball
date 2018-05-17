package logic.gameelements.target;

public class DropTarget extends AbstractTarget {
    public DropTarget(){ super(100); }

    @Override
    public int hit() {
        setActive(false);
        double random = Math.random();
        if(random < 0.3)
            setChanged();
        //TODO: if all DropTarget has been hit, call DropTargetBonus
        return getScore();
    }
}
