package kernel.structural.arduino

import dsl.ClassifAI_DSL_Binding
import kernel.NamedElement
import kernel.generator.Visitable
import kernel.generator.Visitor
import kernel.structural.Code

class Program implements NamedElement, Visitable {
    private String name

    private List<Code> codes

    boolean codesDefined = false

    Program() {
        this.codes = new ArrayList<>()
    }

    List<Code> getCodes() {
        return codes
    }

    List<Component> getComponents() {
        return components
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

    def codes(@DelegatesTo(strategy = Closure.DELEGATE_ONLY, value = VariableScope) Closure cl) {
        if (codesDefined) {
            throw new RuntimeException("Codes can only be defined once")
        }
        VariableScope variablesScope = VariableScope.instance
        variablesScope.with(cl)
        this.codes = variablesScope.getVariables()
        codesDefined = true
    }

    def being (@DelegatesTo(strategy=Closure.DELEGATE_ONLY, value=Program) Closure cl) {
        Closure code = cl.rehydrate(this, this, this)
        code.resolveStrategy = Closure.DELEGATE_ONLY
        code()
    }
}
