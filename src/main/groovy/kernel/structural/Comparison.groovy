package kernel.structural

import kernel.generator.Visitor

class Comparison extends Code {
    Comparison(String comment) {
        super(comment)
    }

    @Override
    void accept(Visitor<StringBuffer> visitor) {
        visitor.visit(this)
    }
}
