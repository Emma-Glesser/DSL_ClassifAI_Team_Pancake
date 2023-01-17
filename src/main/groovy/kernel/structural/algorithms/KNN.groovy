package kernel.structural.algorithms

import kernel.generator.Visitor

class KNN extends ClassifAIAlgorithm {

    private Integer k

    int getK() {
        return k
    }

    @Override
    void accept(Visitor<StringBuffer> visitor) {
        visitor.visit(this)
    }

    def k (Integer k) {
        this.k = k
    }
}
