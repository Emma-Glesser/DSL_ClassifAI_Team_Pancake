package kernel.structural.algorithms.layers

class Dense extends CNNLayer {

    private Integer units
    private ActivationFunction activation_function

    def units(Integer units) {
        this.units = units
    }

    def activation_function(ActivationFunction activation_function) {
        this.activation_function = activation_function
    }

    @Override
    String getCode(int layerNumber) {
        return String.format("x%i = Dense(%i, activation='%s')(x%i)", layerNumber, units, activation_function.getValue(), layerNumber-1)
    }
}
