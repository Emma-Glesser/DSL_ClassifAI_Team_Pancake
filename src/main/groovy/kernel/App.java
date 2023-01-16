package kernel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import kernel.behavioral.State;
import kernel.generator.Visitable;
import kernel.generator.Visitor;
import kernel.structural.arduino.Component;
import kernel.structural.Program;
import kernel.structural.arduino.Variable;

public class App implements Visitable {

    private static final List<String> keywords = List.of("alignas", "decltype", "namespace", "struct",
            "alignof", "default", "new", "switch", "and", "delete", "noexcept", "template", "and_eq", "do", "not", "this",
            "asm", "double", "not_eq", "thread_local", "auto", "dynamic_cast", "nullptr", "throw",
            "bitand", "else", "operator", "true", "bitor", "enum", "or", "try","continue", "mutable",
            "bool", "explicit", "or_eq", "typedef", "break", "export", "private", "typeid","static_cast",
            "case", "extern", "protected", "typename", "catch", "false", "public", "union", "xor_eq",
            "char", "float", "register", "unsigned", "char16_t", "for", "reinterpret_cast", "using",
            "char32_t", "friend", "return", "virtual", "class", "goto", "short", "void",
            "compl", "if", "signed", "volatile", "const", "inline", "sizeof", "wchar_t",
            "constexpr", "int", "static", "while", "const_cast", "long", "static_assert", "xor", "high", "low");
    private Program program;
    private List<Variable> variables;
    private List<Component> components = new ArrayList<>();
    private List<State> states = new ArrayList<>();

    public List<Component> getComponents() {
        return components;
    }

    public void setComponents(List<Component> components) {
        this.components = components;
    }

    public List<State> getStates() {
        return states;
    }

    public void setStates(List<State> states) {
        this.states = states;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public List<Variable> getVariables() {
        return variables;
    }

    public void setVariables(List<Variable> variables) {
        this.variables = variables;
    }

    @Override
    public void accept(Visitor<StringBuffer> visitor) {
        visitor.visit(this);
    }

    public void check() {
        if (variables.stream().map(Variable::getName).anyMatch(keywords::contains)) {
            throw new RuntimeException("Variable name can't be set as a C++ keyword");
        }
        if (variables.stream().map(Variable::getName).anyMatch(name -> !name.matches("(?i)[a-z][a-z0-9_]*"))) {
            throw new RuntimeException("Incorrect variable identifiers");
        }
        if (variables.stream().map(Variable::getType).anyMatch(Objects::isNull)) {
            throw new RuntimeException("Every variables should have a default value");
        }
    }
}
