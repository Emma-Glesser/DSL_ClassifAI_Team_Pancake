package kernel.structural

import kernel.generator.Visitor

class Flatten extends CNNLayer {

    Flatten(){

    }

    def flatten(){

    }

    @Override
    public void accept(Visitor<StringBuffer> visitor) {
        visitor.visit(this);
    }
}
