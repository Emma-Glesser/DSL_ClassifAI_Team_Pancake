package kernel;

import kernel.generator.Visitable;
import kernel.generator.Visitor;
import kernel.structural.Program;

public class App implements Visitable {
    private Program program;
    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }


    @Override
    public void accept(Visitor<StringBuffer> visitor) {
        visitor.visit(this);
    }

    public void check() {

    }
}
