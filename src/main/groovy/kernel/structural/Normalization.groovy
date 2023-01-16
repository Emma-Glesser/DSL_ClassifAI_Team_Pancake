package kernel.structural

import kernel.generator.Visitor

class Normalization extends CNNLayer{

    Normalization(){

    }

    def normalization(){

    }

    @Override
    public void accept(Visitor<StringBuffer> visitor) {
        visitor.visit(this);
    }

}
