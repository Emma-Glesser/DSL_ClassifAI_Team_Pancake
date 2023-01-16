package kernel.structural;

import kernel.generator.Visitor;

class KNN extends ClassifAIAlgorithm {

    private Integer k;

    KNN (String name, String comment, Integer k) {
        super(name,comment);
        this.k = k;
    }

    int getK() {
        return k
    }

    void setK(Integer k) {
        this.k = k
    }

    @Override
    void accept(Visitor<StringBuffer> visitor) {
        visitor.visit(this);
    }

    def k (Integer k) {
        this.k = k;
    }
}
