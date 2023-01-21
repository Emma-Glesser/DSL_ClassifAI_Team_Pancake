package kernel.structural.algorithms

import kernel.generator.Visitor
import kernel.structural.Invalid_DSL_SyntaxeException

class KNN extends ClassifAIAlgorithm {

    public Integer k

    int getK() {
        if (k == null) {
            throw new Invalid_DSL_SyntaxeException("KNN algorithm should have a k value")
        }
        if (k < 1) {
            throw new Invalid_DSL_SyntaxeException("KNN algorithm should have a k value greater than 0")
        }
        return k
    }

    @Override
    void accept(Visitor<StringBuffer> visitor) {
        visitor.visit(this)
    }

    def definedAs(@DelegatesTo(strategy=Closure.DELEGATE_ONLY, value=KNN) Closure cl) {
        Closure code = cl.rehydrate(this, this, this)
        code.resolveStrategy = Closure.DELEGATE_ONLY
        code()
    }
}
