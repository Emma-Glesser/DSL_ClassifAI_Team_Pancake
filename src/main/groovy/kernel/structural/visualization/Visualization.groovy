package kernel.structural.visualization

import dsl.ClassifAI_DSL
import dsl.ClassifAI_DSL_Binding
import kernel.generator.Visitor
import kernel.structural.Code
import kernel.structural.Invalid_DSL_SyntaxeException

class Visualization extends Code {

    private List<String> algorithmsToVisualize
    private ClassifAI_DSL.Param comparison


    List<String> getAlgorithmNames() {
        return algorithmsToVisualize
    }

    void setAlgorithmsToVisualize(String... algorithmsToVisualize) {
        if (this.algorithmsToVisualize != null) {
            throw new Invalid_DSL_SyntaxeException("Algorithms to visualize can only be defined once")
        }
        if (algorithmsToVisualize.length == 0) {
            throw new Invalid_DSL_SyntaxeException("At least one algorithm must be defined")
        }
        for (String algorithmName : algorithmsToVisualize) {
            try {
                ClassifAI_DSL_Binding.instance.getVariable(algorithmName)
            } catch (Exception ignored) {
                throw new Invalid_DSL_SyntaxeException("Algorithm " + algorithmName + " is not defined. Can't visualize it")
            }
        }
        this.algorithmsToVisualize = algorithmsToVisualize
    }

    void setComparison(ClassifAI_DSL.Param comparison) {
        if (this.comparison != null) {
            throw new Invalid_DSL_SyntaxeException("Comparison can only be defined once")
        }
        this.comparison = comparison
    }

    ClassifAI_DSL.Param getComparison_factor(){
        return comparison;
    }

    @Override
    void accept(Visitor<StringBuffer> visitor) {
        visitor.visit(this)
    }
}
