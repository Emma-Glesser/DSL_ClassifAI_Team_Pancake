package kernel.structural;

import kernel.generator.Visitor;

class KNN extends ClassifAIAlgorithm {

    private int k;

    KNN (String name, String comment, int k) {
        super(name,comment);
        this.k = k;
    }

    int getK() {
        return k
    }

    void setK(int k) {
        this.k = k
    }

    @Override
    void accept(Visitor<StringBuffer> visitor) {
        visitor.visit(this);
    }

    def k (int k) {
        this.k = k;
    }
}
