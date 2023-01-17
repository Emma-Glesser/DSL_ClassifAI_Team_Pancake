package dsl;

import kernel.App;
import kernel.generator.ToWiring;
import kernel.generator.Visitor;
import kernel.structural.Program;

public class ClassifAI_DSL_Model {
    private Program program;

    public void setProgram(Program program) {
        this.program = program;
    }

    public Program getProgram() {
        return program;
    }

    public Object generateCode() {
		App app = new App();
        app.setProgram(this.program);
		Visitor<StringBuffer> codeGenerator = new ToWiring();
        app.check();
		app.accept(codeGenerator);
		
		return codeGenerator.getResult();
	}
}
