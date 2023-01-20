package kernel.structural.dataProcessing

import kernel.generator.Visitable
import kernel.generator.Visitor

class Preprocessing implements Visitable {
    private Integer[] reshape
    private boolean normalize

    Integer[] getReshape() {
        return reshape
    }

    boolean getNormalize() {
        return normalize
    }

    void reshape (Integer[] reshape) {
        this.reshape = reshape
    }

    void normalize (boolean normalize) {
        this.normalize = normalize
    }

    void accept(Visitor<StringBuffer> visitor) {
        visitor.visit(this)
    }

    String getCode() {
        if(reshape != null) {
            String reshapeline = String.format("X_train = X_train.values.reshape(-1, 28,28,1)\n" +
                    "X_val = X_val.values.reshape(-1, 28,28,1)")
        }
        return String.format("\"# Data preprocessing : reshape and normalization\\n\"\n" +
                "    \"%s\\n\"\n",this.getTestSize());
    }
}
