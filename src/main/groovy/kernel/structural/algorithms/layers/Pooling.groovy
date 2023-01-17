package kernel.structural.algorithms.layers

import kernel.generator.Visitor

class Pooling extends CNNLayer{
    private Integer[] strides = new Integer[2]

    Integer[] getStrides() {
        return strides
    }

    @Override
    void accept(Visitor<StringBuffer> visitor) {
        visitor.visit(this)
    }

    def strides (Integer[] strides) {
        this.strides = strides
    }

}
