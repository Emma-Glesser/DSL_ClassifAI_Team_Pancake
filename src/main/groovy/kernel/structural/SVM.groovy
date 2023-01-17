package kernel.structural;

import kernel.generator.Visitor;

class SVM extends ClassifAIAlgorithm {

    SVM (String name, String comment) {
        super(name,comment);
    }

    @Override
    void accept(Visitor<StringBuffer> visitor) {
        visitor.visit(this);
    }
}
