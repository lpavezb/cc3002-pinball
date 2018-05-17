package logic.gameelements.target;

import logic.gameelements.AbstractHittable;

public abstract class AbstractTarget extends AbstractHittable implements Target{
    private boolean activated;

    public AbstractTarget(int score){
        setScore(score);
        activated = true;
    }

    @Override
    public boolean isActive() {
        return activated;
    }

    @Override
    public void reset() { setActive(true); }

    @Override
    public void setActive(boolean activate) {
        activated = activate;
    }

}
