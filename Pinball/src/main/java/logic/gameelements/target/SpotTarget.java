package logic.gameelements.target;

public class SpotTarget extends AbstractTarget {
    public SpotTarget(){
        super(0);
    }

    @Override
    public int hit() {
        setActive(false);
        //TODO: trigger JackPotBonus bonus
        return getScore();
    }
}
