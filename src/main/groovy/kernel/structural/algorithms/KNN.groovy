package kernel.structural.algorithms

import kernel.generator.Visitor

class KNN extends ClassifAIAlgorithm {

    public Integer k

    int getK() {
        if (k == null) {
            throw new RuntimeException("KNN algorithm should have a k value")
        }
        if (k < 1) {
            throw new RuntimeException("KNN algorithm should have a k value greater than 0")
        }
        return k
    }

    @Override
    void accept(Visitor<StringBuffer> visitor) {
        visitor.visit(this)
    }
}
