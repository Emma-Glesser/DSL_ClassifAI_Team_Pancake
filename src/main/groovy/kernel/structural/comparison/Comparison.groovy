package kernel.structural.comparison

import dsl.ClassifAI_DSL

class Comparison {
    private Set<ClassifAI_DSL.Param> parameterList

    Comparison(Set<ClassifAI_DSL.Param> parameterList) {
        this.parameterList = parameterList
    }

    Set<ClassifAI_DSL.Param> getParameterList() {
        return parameterList
    }
}
