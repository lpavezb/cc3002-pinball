package logic.gameelements.bumper;

public abstract class AbstractBumper implements Bumper {
    private int score;
    private int hitTimesToUpgrade;
    private int timesHit;
    private boolean upgraded;

    public AbstractBumper(int nHitTimesToUpgrade, int anScore){
        score = anScore;
        hitTimesToUpgrade = nHitTimesToUpgrade;
        upgraded = false;
    }

    @Override
    public int remainingHitsToUpgrade() {
        return hitTimesToUpgrade - timesHit;
    }

    @Override
    public boolean isUpgraded() {
        return upgraded;
    }

    @Override
    public int hit() {
        timesHit += 1;
        if(remainingHitsToUpgrade() == 0)
            upgrade();
        return score;
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public void setUpgrade(boolean upgrade) {
        this.upgraded = upgrade;
    }
}
