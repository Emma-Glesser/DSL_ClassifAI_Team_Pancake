package kernel.structural.dataProcessing

import kernel.generator.Visitable
import kernel.generator.Visitor

class Preprocessing implements Visitable {
    private Integer[] reshape = new Integer[3]
    private Integer normalize

    Integer[] getReshape() {
        return reshape
    }

    Integer getNormalize() {
        return normalize
    }

    void reshape (Integer[] reshape) {
        this.reshape = reshape
    }

    void normalize (Integer normalize) {
        this.normalize = normalize
    }

    void accept(Visitor<StringBuffer> visitor) {
        visitor.visit(this)
    }

    String getCode() {
        String preprocessing = new String();
        if(reshape != null) {
            String reshapeline = String.format("X_train = X_train.values.reshape(-1, %i,%i,%i)\n" +
                    "X_val = X_val.values.reshape(-1, %i,%i,%i)\n", reshape[0], reshape[1], reshape[2], reshape[0], reshape[1], reshape[2])
            preprocessing += reshapeline;
        }
        if(normalize != null) {
            String normalizeline = String.format("X_train = X_train/(%f))\n" , normalize)
            preprocessing += normalizeline;
        }
        return String.format("\"# Data preprocessing : reshape and normalization\\n\"\n", preprocessing);
    }
}
