package logic.gameelements.target;

public abstract class AbstractTarget implements Target{
    private int score;
    private boolean activated;

    public AbstractTarget(int anScore){
        score = anScore;
        activated = true;
    }

    @Override
    public boolean isActive() {
        return activated;
    }

    @Override
    public void reset() {
        setActive(true);
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
    public void setActive(boolean activate) {
        activated = activate;
    }
}
