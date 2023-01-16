package kernel.structural

import kernel.generator.Visitor

class Flatten extends CNNLayer {

    Flatten(String comment){
        super(comment);
    }

    @Override
    public void accept(Visitor<StringBuffer> visitor) {
        visitor.visit(this);
    }
}
