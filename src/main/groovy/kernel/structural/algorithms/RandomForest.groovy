package kernel.structural.algorithms

import kernel.generator.Visitor
import kernel.structural.Invalid_DSL_SyntaxeException

class RandomForest extends ClassifAIAlgorithm {

    private Integer nbEstimators

    int getNbEstimators() {
        if (nbEstimators == null) {
            throw new Invalid_DSL_SyntaxeException("RandomForest algorithm should have a nb_estimators value")
        }
        if (nbEstimators < 1) {
            throw new Invalid_DSL_SyntaxeException("RandomForest algorithm should have a nb_estimators value greater than 0")
        }
        return nbEstimators
    }

    void setNbEstimators(int nbEstimators) {
        this.nbEstimators = nbEstimators
    }

    def definedAs(@DelegatesTo(strategy=Closure.DELEGATE_ONLY, value=RandomForest) Closure cl) {
        Closure code = cl.rehydrate(this, this, this)
        code.resolveStrategy = Closure.DELEGATE_ONLY
        code()
    }

    @Override
    void accept(Visitor<StringBuffer> visitor) {
        visitor.visit(this)
    }
}
