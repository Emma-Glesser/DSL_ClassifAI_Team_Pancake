package kernel.structural

import kernel.generator.Visitor

class Convolution extends CNNLayer{

    public int filters;
    public int[] kernel_size = new Integer[2];
    public boolean input_shape;
    public Padding padding;
    public ActivationFunction activation_function;

    Convolution(int filters, int[] kernel_size, boolean input_shape, Padding padding, ActivationFunction activation_function){
        this.filters = filters;
        this.kernel_size = kernel_size;
        this.input_shape = input_shape;
        this.padding = padding;
        this.activation_function = activation_function;
    }

    @Override
    public void accept(Visitor<StringBuffer> visitor) {
        visitor.visit(this);
    }

}
