package kernel.structural.algorithms.layers

import dsl.ClassifAI_DSL

class Dense extends CNNLayer {

    private Integer units
    private ClassifAI_DSL.ActivationFunction activation_function

    void setUnits(Integer units) {
        if (this.units != null) {
            throw new RuntimeException("Units can only be defined once")
        }
        this.units = units
    }

    void setActivation_function(ClassifAI_DSL.ActivationFunction activation_function) {
        if (this.activation_function != null) {
            throw new RuntimeException("Activation function can only be defined once")
        }
        this.activation_function = activation_function
    }

    @Override
    String getCode(int layerNumber) {
        if (units == null) {
            throw new RuntimeException("Dense layer should have a units value")
        }
        if (units < 1) {
            throw new RuntimeException("Dense layer should have a units value greater than 0")
        }
        if (activation_function == null) {
            throw new RuntimeException("Dense layer should have an activation function")
        }
        return String.format("x%i = Dense(%i, activation='%s')(x%i)", layerNumber, units, activation_function.toString().toLowerCase(), layerNumber-1)
    }
}
