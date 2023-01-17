package kernel.structural.algorithms.layers

import kernel.generator.Visitor

class Flatten extends CNNLayer {

    @Override
    void accept(Visitor<StringBuffer> visitor) {
        visitor.visit(this)
    }
}
