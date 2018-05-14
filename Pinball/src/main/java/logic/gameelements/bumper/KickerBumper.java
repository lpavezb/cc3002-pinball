package logic.gameelements.bumper;

public class KickerBumper extends AbstractBumper {

    public KickerBumper(){
        super(5, 500);
    }

    @Override
    public void upgrade() {
        this.setUpgrade(true);
        this.setScore(1000);
    }

    @Override
    public void downgrade() {
        this.setUpgrade(false);
        this.setScore(500);
    }
}
