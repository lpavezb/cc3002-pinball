package logic.gameelements.bumper;
import logic.gameelements.AbstractHittable;


public abstract class AbstractBumper extends AbstractHittable implements Bumper {
    private int initialScore;
    private int upgradeScore;
    private int hitTimesToUpgrade;
    private int timesHit;
    private boolean upgraded;

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
        if(getRandom().nextDouble() < 0.1){
            setChanged();
            notifyObservers();
        }
    }

    @Override
    public void downgrade() {
        upgraded = false;
        setScore(initialScore);
    }

    @Override
    public int hit() {
        timesHit += 1;
        if(remainingHitsToUpgrade() == 0 && !this.upgraded)
            upgrade();
        return getScore();
    }
}
