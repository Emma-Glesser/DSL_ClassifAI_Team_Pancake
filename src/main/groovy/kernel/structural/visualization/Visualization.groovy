package kernel.structural.visualization

import dsl.ClassifAI_DSL
import dsl.ClassifAI_DSL_Binding
import kernel.generator.Visitor
import kernel.structural.Code
import kernel.structural.comparison.Comparison

class Visualization extends Code {

    private List<String> algorithmsToVisualize
    private Comparison comparison


    List<String> getAlgotihmNames() {
        return algorithmsToVisualize
    }

    void setAlgorithmsToVisualize(String... algorithmsToVisualize) {
        if (this.algorithmsToVisualize != null) {
            throw new RuntimeException("Algorithms to visualize can only be defined once")
        }
        if (algorithmsToVisualize.length == 0) {
            throw new RuntimeException("At least one algorithm must be defined")
        }
        for (String algorithmName : algorithmsToVisualize) {
            try {
                ClassifAI_DSL_Binding.instance.getVariable(algorithmName)
            } catch (Exception ignored) {
                throw new RuntimeException("Algorithm " + algorithmName + " is not defined")
            }
        }
        this.algorithmsToVisualize = algorithmsToVisualize
    }

    void setComparison(ClassifAI_DSL.Param... params) {
        if (comparison != null) {
            throw new RuntimeException("Comparison can only be defined once")
        }
        if (params.length == 0) {
            throw new RuntimeException("At least one parameter must be defined")
        }
        Set<ClassifAI_DSL.Param> paramSet = Set.of(params)
        if (paramSet.size() != params.length) {
            throw new RuntimeException("Parameters must be unique")
        }

        Comparison comparison = new Comparison(paramSet)
        this.comparison = comparison
    }

    @Override
    void accept(Visitor<StringBuffer> visitor) {
        visitor.visit(this)
    }
}
