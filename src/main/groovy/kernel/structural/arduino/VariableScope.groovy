package kernel.structural.arduino

class VariableScope {
    private List<Variable> variables

    private static final VariableScope instance = new VariableScope()

    static VariableScope getInstance() {
        return instance
    }

    VariableScope() {
        this.variables = new ArrayList<>()
    }

    Variable variable(String name) {
        Variable variable = new Variable(name)
        this.variables.add(variable)
        return variable
    }

    List<Variable> getVariables() {
        return variables
    }
}
