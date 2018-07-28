package logic.gameelements.bumper;
import logic.gameelements.AbstractHittable;
import logic.inverseVisitor.ExtraBallBonusVisitor;
import logic.inverseVisitor.ScoreVisitor;

/**
 * Abstract class that represents a Bumper object.
 *
 * @author Lukas Pavez
 * @see Bumper
 * @see KickerBumper
 * @see PopBumper
 */
public abstract class AbstractBumper extends AbstractHittable implements Bumper {
    private int initialScore;
    private int upgradeScore;
    private int hitTimesToUpgrade;
    private int timesHit;
    private boolean upgraded;

    /**
     * Class constructor
     */
    public AbstractBumper(int initialScore, int upgradeScore, int hitTimesToUpgrade){
        this.initialScore = initialScore;
        this.upgradeScore = upgradeScore;
        this.hitTimesToUpgrade = hitTimesToUpgrade;
        this.setScore(initialScore);
        upgraded = false;
    }

    @Override
    public int remainingHitsToUpgrade() {
        int res = hitTimesToUpgrade - timesHit;
        if(res < 0){res = 0;}
        return res;
    }

    @Override
    public boolean isUpgraded() { return upgraded; }

    @Override
    public void upgrade() {
        upgraded = true;
        setScore(upgradeScore);
    }

    @Override
    public void downgrade() {
        timesHit = 0;
        upgraded = false;
        setScore(initialScore);
    }

    @Override
    public int hit() {
        timesHit += 1;
        if(remainingHitsToUpgrade() == 0 && !this.upgraded){
            upgrade();
            tryTriggerBonus();
        }
        notifyUp(new ScoreVisitor(this));
        return getScore();
    }

    /**
     * Method to trigger an extraBallBonus with a probability of 10%
     */
    private void tryTriggerBonus(){
        if(getRandom().nextDouble() < 0.1){
            notifyUp(new ExtraBallBonusVisitor());
        }
    }

    @Override
    public boolean isKickerBumper() {
        return false;
    }

    @Override
    public boolean isPopBumper() {
        return false;
    }
}
