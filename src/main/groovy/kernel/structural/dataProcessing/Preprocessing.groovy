package kernel.structural.dataProcessing

import kernel.generator.Visitor

class Preprocessing {
    private boolean reshape
    private boolean normalize

    boolean getReshape() {
        return reshape
    }

    boolean getNormalize() {
        return normalize
    }

    void reshape (boolean reshape) {
        this.reshape = reshape
    }

    void normalize (boolean normalize) {
        this.normalize = normalize
    }

    void accept(Visitor<StringBuffer> visitor) {
        visitor.visit(this)
    }
}
