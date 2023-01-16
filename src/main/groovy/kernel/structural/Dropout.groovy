package kernel.structural

import kernel.generator.Visitor

class Dropout extends CNNLayer{
    public int rate_of_disabled_neurons;


    Dropout(int rate_of_disabled_neurons){
        this.rate_of_disabled_neurons = rate_of_disabled_neurons;
    }

    @Override
    public void accept(Visitor<StringBuffer> visitor) {
        visitor.visit(this);
    }

}
