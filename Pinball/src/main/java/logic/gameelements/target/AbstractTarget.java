package logic.gameelements.target;

import logic.gameelements.AbstractHittable;

/**
 * Abstract class that represents a Target object.
 *
 * @author Lukas Pavez
 * @see Target
 * @see DropTarget
 * @see SpotTarget
 */
public abstract class AbstractTarget extends AbstractHittable implements Target{
    private boolean activated;

    /**
     * Class constructor
     */
    public AbstractTarget(int score){
        setScore(score);
        activated = true;
    }

    @Override
    public boolean isActive() { return activated; }

    @Override
    public void reset() { setActive(true); }

    @Override
    public void setActive(boolean activate) { activated = activate; }
}
