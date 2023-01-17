package kernel.structural;

import kernel.generator.Visitor
import kernel.structural.algorithms.ClassifAIAlgorithm;

class SVM extends ClassifAIAlgorithm {

    SVM (String name, String comment) {
        super(name,comment);
    }

    @Override
    void accept(Visitor<StringBuffer> visitor) {
        visitor.visit(this);
    }
}
