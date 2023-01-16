package kernel.structural.arduino

import dsl.ClassifAI_DSL_Binding
import kernel.generator.Visitable
import kernel.generator.Visitor

class Variable implements Visitable {
    private String name

    private Integer intValue
    private Boolean boolValue

    Variable(String name) {
        this.name = name
        ClassifAI_DSL_Binding.getInstance().setVariable(name, this)
    }

    String getName() {
        return name
    }

    void setName(String name) {
        this.name = name
    }

    String getType() {
        if (intValue != null) {
            return "int"
        } else if (boolValue != null) {
            return "bool"
        } else {
            return null
        }
    }

    String getValue() {
        if (intValue != null) {
            return intValue.toString()
        } else if (boolValue != null) {
            return boolValue.toString()
        } else {
            return ""
        }
    }

    void value(int value) {
        this.intValue = value
    }

    void value(boolean value) {
        this.boolValue = value
    }

    @Override
    void accept(Visitor visitor) {
        visitor.visit(this)
    }
}
