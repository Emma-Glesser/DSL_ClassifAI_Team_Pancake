package kernel.structural

import kernel.generator.Visitor;

class RandomForest extends ClassifAIAlgorithm {

    private int nb_estimators;

    RandomForest(String name, String comment, int nb_estimators) {
        super(name, comment);
        this.nb_estimators = nb_estimators;
    }

    int getNb_estimators() {
        return nb_estimators;
    }

    void setNb_estimators(int nb_estimators) {
        this.nb_estimators = nb_estimators;
    }

    @Override
    void accept(Visitor<StringBuffer> visitor) {
        visitor.visit(this);
    }

    def nb_estimators (int nb_estimators) {
        this.nb_estimators = nb_estimators;
    }
}
