package logic.gameelements.bumper;

public class PopBumper extends AbstractBumper{

    public PopBumper(){
        super(3, 100);
    }

    @Override
    public void upgrade() {
        this.setUpgrade(true);
        this.setScore(300);
    }

    @Override
    public void downgrade() {
        this.setUpgrade(false);
        this.setScore(100);
    }
}
