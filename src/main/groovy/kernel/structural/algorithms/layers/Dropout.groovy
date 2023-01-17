package kernel.structural.algorithms.layers

import kernel.generator.Visitor

class Dropout extends CNNLayer{
    private Integer rate_of_disabled_neurons

    Integer getRate_of_disabled_neurons() {
        return rate_of_disabled_neurons
    }

    @Override
    void accept(Visitor<StringBuffer> visitor) {
        visitor.visit(this)
    }

    def rate_of_disabled_neurons(Integer rate_of_disabled_neurons ) {
        this.rate_of_disabled_neurons = rate_of_disabled_neurons
    }

}
