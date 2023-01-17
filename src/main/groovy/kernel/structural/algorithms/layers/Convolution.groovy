package kernel.structural.algorithms.layers

import kernel.generator.Visitor

class Convolution extends CNNLayer{

    private Integer filters
    private Integer[] kernel_size = new Integer[2]
    private Boolean input_shape
    private Padding padding
    private ActivationFunction activation_function

    Integer getFilters() {
        return filters
    }

    Integer[] getKernel_size() {
        return kernel_size
    }

    Boolean getInput_shape() {
        return input_shape
    }

    Padding getPadding() {
        return padding
    }

    ActivationFunction getActivation_function() {
        return activation_function
    }

    @Override
    void accept(Visitor<StringBuffer> visitor) {
        visitor.visit(this)
    }

    def filters (Integer filters) {
        this.filters = filters
    }

    def kernel_size (Integer[] kernel_size) {
        this.kernel_size = kernel_size
    }

    def input_shape (Boolean input_shape) {
        this.input_shape = input_shape
    }

    def padding(Padding padding) {
        this.padding = padding
    }

    def activation_function (ActivationFunction activation_function) {
        this.activation_function = activation_function
    }
}
