package kernel.structural.algorithms.layers

import dsl.ClassifAI_DSL
import kernel.structural.Invalid_DSL_SyntaxeException

class Dense extends CNNLayer {

    private Integer units
    private ClassifAI_DSL.ActivationFunction activationFunction

    void setUnits(Integer units) {
        if (this.units != null) {
            throw new Invalid_DSL_SyntaxeException("Units can only be defined once")
        }
        this.units = units
    }

    void setActivationFunction(ClassifAI_DSL.ActivationFunction activation_function) {
        if (this.activationFunction != null) {
            throw new Invalid_DSL_SyntaxeException("Activation function can only be defined once")
        }
        this.activationFunction = activation_function
    }

    @Override
    String getCode(int layerNumber) {
        if (units == null) {
            throw new Invalid_DSL_SyntaxeException("Dense layer should have a units value")
        }
        if (units < 1) {
            throw new Invalid_DSL_SyntaxeException("Dense layer should have a units value greater than 0")
        }
        if (activationFunction == null) {
            throw new Invalid_DSL_SyntaxeException("Dense layer should have an activation function")
        }
        return String.format("x%d = Dense(%d, activation='%s')(x%d)", layerNumber, units, activationFunction.toString().toLowerCase(), layerNumber-1)
    }
}
