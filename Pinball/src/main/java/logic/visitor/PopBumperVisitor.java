package logic.visitor;

import logic.gameelements.bumper.PopBumper;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents a Visitor object
 *
 * @author Lukas Pavez
 */
public class PopBumperVisitor extends Visitor{
    private List<PopBumper> popBumpers;

    /**
     * Class constructor
     */
    public PopBumperVisitor(){ popBumpers = new ArrayList<>(); }

    @Override
    public void visitPopBumper(PopBumper popBumper) { popBumpers.add(popBumper); }

    /**
     * Gets the visited PopBumpers
     *
     * @return the visited PopBumpers
     */
    public List<PopBumper> getPopBumpers(){ return popBumpers; }
}
