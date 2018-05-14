package logic.gameelements.bumper;

public abstract class AbstractBumper implements Bumper {
    private int score;
    private int initialScore;
    private int upgradeScore;
    private int hitTimesToUpgrade;
    private int timesHit;
    private boolean upgraded;

    public AbstractBumper(int nHitTimesToUpgrade, int aInitialScore, int aUpgradeScore){
        initialScore = aInitialScore;
        upgradeScore = aUpgradeScore;
        score = initialScore;
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
    public void upgrade() {
        this.setUpgrade(true);
        this.setScore(upgradeScore);
        double random = Math.random();
        if(random < 0.1){
            //TODO: trigger ExtraBallBonus
        }
    }

    @Override
    public void downgrade() {
        this.setUpgrade(false);
        this.setScore(initialScore);
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
