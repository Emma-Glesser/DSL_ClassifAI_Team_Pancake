package kernel;

import java.util.List;

import kernel.generator.Visitable;
import kernel.generator.Visitor;
import kernel.structural.Invalid_DSL_SyntaxeException;
import kernel.structural.Program;
import kernel.structural.algorithms.ClassifAIAlgorithm;

public class App implements Visitable {
    private static final List<String> keywords = List.of(
            "False", "None", "True", "and", "as", "assert", "async", "await", "break",
            "class", "continue", "def", "del", "elif", "else", "except", "finally", "for", "from",
            "global", "if", "import", "in", "is", "lambda", "nonlocal", "not", "or", "pass", "raise",
            "return", "try", "while", "with", "yield");
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
        if (program.getAlgorithms().stream().map(ClassifAIAlgorithm::getName).anyMatch(keywords::contains)) {
            throw new Invalid_DSL_SyntaxeException("Variable name can't be set as a C++ keyword");
        }
        if (program.getAlgorithms().stream().map(ClassifAIAlgorithm::getName).anyMatch(name -> !name.matches("^[a-zA-Z_]\\w*$"))) {
            throw new Invalid_DSL_SyntaxeException("Incorrect variable identifiers");
        }
    }
}
