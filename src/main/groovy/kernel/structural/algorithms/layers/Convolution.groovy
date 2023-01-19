package kernel.structural.algorithms.layers

class Convolution extends CNNLayer{

    private Integer filters
    private Integer[] kernel_size = new Integer[2]
    private Boolean input_shape
    private Padding padding
    private ActivationFunction activation_function

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

    @Override
    String getCode(int layerNumber) {
        return String.format("x%i = Conv2D(%i, kernel_size=(%i, %i), activation='%s', padding='%s')(x%i)", layerNumber, filters, kernel_size[0], kernel_size[1], activation_function.getValue(), padding, layerNumber-1)
    }
}
