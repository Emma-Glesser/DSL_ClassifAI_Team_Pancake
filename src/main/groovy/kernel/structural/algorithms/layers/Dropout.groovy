package kernel.structural.algorithms.layers

class Dropout extends CNNLayer{
    private Integer rate_of_disabled_neurons

    def rate_of_disabled_neurons(Integer rate_of_disabled_neurons ) {
        this.rate_of_disabled_neurons = rate_of_disabled_neurons
    }

    @Override
    String getCode(int layerNumber) {
        return String.format("x%i = Dropout(%f)(x%i)", layerNumber, rate_of_disabled_neurons, layerNumber-1)
    }
}
