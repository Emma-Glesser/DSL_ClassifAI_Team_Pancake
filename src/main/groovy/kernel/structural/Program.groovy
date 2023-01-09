package kernel.structural

import dsl.ClassifAI_DSL_Binding
import kernel.NamedElement
import kernel.behavioral.State
import kernel.generator.Visitable
import kernel.generator.Visitor

class Program implements NamedElement, Visitable {
    private String name

    private List<Variable> variables

    private List<Component> components

    private List<State> states

    private String initialStateName

    private boolean variablesDefined = false
    private boolean componentsDefined = false
    private boolean statesDefined = false

    Program() {
        this.variables = new ArrayList<>()
        this.components = new ArrayList<>()
        this.states = new ArrayList<>()
    }

    List<Variable> getVariables() {
        return variables
    }

    List<Component> getComponents() {
        return components
    }
    List<State> getStates() {
        return states
    }

    State getInitialState() {
        if (initialStateName == null) {
            throw new RuntimeException("Initial state not defined")
        }
        try {
            return (State)ClassifAI_DSL_Binding.getInstance().getVariable(initialStateName)
        } catch (Exception ignored) {
            throw new RuntimeException("The initial state must be defined")
        }
    }

    @Override
    void setName(String name) {
        this.name = name
    }

    String getName() {
        return name
    }

    @Override
    void accept(Visitor visitor) {
        visitor.visit(this)
    }

    void name(String name) {
        this.name = name
    }

    def variables(@DelegatesTo(strategy = Closure.DELEGATE_ONLY, value = VariableScope) Closure cl) {
        if (variablesDefined) {
            throw new RuntimeException("Variables can only be defined once")
        }
        VariableScope variablesScope = VariableScope.instance
        variablesScope.with(cl)
        this.variables = variablesScope.getVariables()
        variablesDefined = true
    }

    def components(@DelegatesTo(strategy = Closure.DELEGATE_ONLY, value = ComponentScope) Closure cl) {
        if (componentsDefined) {
            throw new RuntimeException("Components can only be defined once")
        }
        ComponentScope componentScope = ComponentScope.instance
        componentScope.with(cl)
        this.components = componentScope.getComponents()
        componentsDefined = true
    }

    def states(@DelegatesTo(strategy = Closure.DELEGATE_ONLY, value = StateScope) Closure cl) {
        if (statesDefined) {
            throw new RuntimeException("States can only be defined once")
        }
        StateScope stateScope = StateScope.instance
        stateScope.with(cl)
        this.states = stateScope.getStates()
        statesDefined = true
    }

    void initialState(String name) {
        if (this.initialStateName != null) {
            throw new RuntimeException("The initial state can't be defined more than once")
        }
        this.initialStateName = name
    }

    def being (@DelegatesTo(strategy=Closure.DELEGATE_ONLY, value=Program) Closure cl) {
        Closure code = cl.rehydrate(this, this, this)
        code.resolveStrategy = Closure.DELEGATE_ONLY
        code()
    }
}
