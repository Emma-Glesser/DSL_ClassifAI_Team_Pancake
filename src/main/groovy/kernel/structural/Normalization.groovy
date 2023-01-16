package kernel.structural

import kernel.generator.Visitor

class Normalization extends CNNLayer {

    Normalization(String comment){
        super(comment);
    }

    @Override
    public void accept(Visitor<StringBuffer> visitor) {
        visitor.visit(this);
    }

}
