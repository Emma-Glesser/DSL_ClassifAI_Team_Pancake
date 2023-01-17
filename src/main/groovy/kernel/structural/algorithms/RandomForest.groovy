package kernel.structural.algorithms

import kernel.generator.Visitor

class RandomForest extends ClassifAIAlgorithm {

    private Integer nb_estimators

    int getNb_estimators() {
        return nb_estimators;
    }

    void setNb_estimators(Integer nb_estimators) {
        this.nb_estimators = nb_estimators
    }

    @Override
    void accept(Visitor<StringBuffer> visitor) {
        visitor.visit(this)
    }

    def nb_estimators (Integer nb_estimators) {
        this.nb_estimators = nb_estimators
    }
}
