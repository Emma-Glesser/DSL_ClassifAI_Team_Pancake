package kernel.structural.algorithms

import kernel.generator.Visitor

class RandomForest extends ClassifAIAlgorithm {

    private Integer nb_estimators

    int getNb_estimators() {
        if (nb_estimators == null) {
            throw new RuntimeException("RandomForest algorithm should have a nb_estimators value")
        }
        if (nb_estimators < 1) {
            throw new RuntimeException("RandomForest algorithm should have a nb_estimators value greater than 0")
        }
        return nb_estimators
    }

    @Override
    void accept(Visitor<StringBuffer> visitor) {
        visitor.visit(this)
    }
}
