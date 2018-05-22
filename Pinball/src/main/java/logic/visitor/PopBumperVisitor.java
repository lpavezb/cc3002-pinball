package logic.visitor;

import logic.gameelements.bumper.PopBumper;

import java.util.ArrayList;
import java.util.List;

public class PopBumperVisitor extends Visitor{
    private List<PopBumper> popBumpers;
    public PopBumperVisitor(){ popBumpers = new ArrayList<>(); }

    @Override
    public void visitPopBumper(PopBumper popBumper) { popBumpers.add(popBumper); }

    public List<PopBumper> getPopBumpers(){ return popBumpers; }
}
