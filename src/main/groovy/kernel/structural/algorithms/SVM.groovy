package kernel.structural.algorithms

import kernel.generator.Visitor

class SVM extends ClassifAIAlgorithm {

    @Override
    void accept(Visitor<StringBuffer> visitor) {
        visitor.visit(this)
    }
}
