package kernel.structural;

import kernel.generator.Visitor;

class KNN extends ClassifAIAlgorithm {

    private int k;

    KNN (String name, String comment, int k) {
        super(name,comment);
        this.k = k;
    }

    @Override
    public void accept(Visitor<StringBuffer> visitor) {
        visitor.visit(this);
    }
}
