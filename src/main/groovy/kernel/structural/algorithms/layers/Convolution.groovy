package kernel.structural.algorithms.layers

import dsl.ClassifAI_DSL

class Convolution extends CNNLayer{

    private Integer filters
    private Integer[] kernelSize
    private ClassifAI_DSL.Padding padding
    private ClassifAI_DSL.ActivationFunction activationFunction

    void setFilters(Integer filters) {
        if (this.filters != null) {
            throw new RuntimeException("Can't call `filters` twice in a same layer")
        }
        this.filters = filters
    }

    void setKernelSize(Integer[] kernel_size) {
        if (this.kernelSize != null) {
            throw new RuntimeException("Can't call `kernel_size` twice in a same layer")
        }
        this.kernelSize = kernel_size
    }

    void setPadding(ClassifAI_DSL.Padding padding) {
        if (this.padding != null) {
            throw new RuntimeException("Can't call `padding` twice in a same layer")
        }
        this.padding = padding
    }

    void setActivationFunction(ClassifAI_DSL.ActivationFunction activation_function) {
        if (this.activationFunction != null) {
            throw new RuntimeException("Can't call `activation_function` twice in a same layer")
        }
        this.activationFunction = activation_function
    }

    @Override
    String getCode(int layerNumber) {
        if (filters == null) {
            throw new RuntimeException("Convolution layer should have a filters value")
        }
        if (filters <= 0) {
            throw new RuntimeException("Filters must be greater than 0")
        }
        if (kernelSize == null) {
            throw new RuntimeException("Convolution layer should have a kernel_size value")
        }
        if (kernelSize.length != 2) {
            throw new RuntimeException("Convolution layer should have a kernel_size value with 2 dimensions")
        }
        if (padding == null && activationFunction == null) {
            return String.format("x%d = Conv2D(%d, (%d, %d))(x%d)", layerNumber, filters, kernelSize[0], kernelSize[1], layerNumber - 1)
        }
        if (activationFunction == null) {
            return String.format("x%d = Conv2D(%d, (%d, %d), padding='%s')(x%d)", layerNumber, filters, kernelSize[0], kernelSize[1], padding, layerNumber - 1)
        }
        if (padding == null) {
            return String.format("x%d = Conv2D(%d, (%d, %d), activation='%s')(x%d)", layerNumber, filters, kernelSize[0], kernelSize[1], activationFunction.toString().toLowerCase(), layerNumber - 1)
        }
        return String.format("x%d = Conv2D(%d, kernel_size=(%d, %d), activation='%s', padding='%s')(x%d)", layerNumber, filters, kernelSize[0], kernelSize[1], activationFunction.toString().toLowerCase(), padding.toString().toLowerCase(), layerNumber-1)
    }
}
