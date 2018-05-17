package logic.gameelements.bumper;
import java.util.*;

public abstract class AbstractBumper extends Observable implements Bumper {
    private int score;
    private int initialScore;
    private int upgradeScore;
    private int hitTimesToUpgrade;
    private int timesHit;
    private boolean upgraded;
    private Random random;

    public AbstractBumper(int initialScore, int upgradeScore, int hitTimesToUpgrade){
        this.initialScore = initialScore;
        this.upgradeScore = upgradeScore;
        this.hitTimesToUpgrade = hitTimesToUpgrade;
        this.score = initialScore;
        upgraded = false;

        random = new Random();
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
        score = upgradeScore;
        if(random.nextDouble() < 0.1){
            setChanged();
            notifyObservers();
        }
    }

    @Override
    public void downgrade() {
        upgraded = false;
        score = initialScore;
    }

    @Override
    public int hit() {
        timesHit += 1;
        if(remainingHitsToUpgrade() == 0 && !this.upgraded)
            upgrade();
        return score;
    }

    @Override
    public int getScore() { return score; }

    @Override
    public void setSeed(long seed) { this.random.setSeed(seed); }
}
