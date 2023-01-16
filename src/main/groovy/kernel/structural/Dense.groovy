package kernel.structural

import kernel.generator.Visitor

class Dense extends CNNLayer {
    Dense(){

    }

    def dense(){

    }

    @Override
    public void accept(Visitor<StringBuffer> visitor) {
        visitor.visit(this);
    }
}
