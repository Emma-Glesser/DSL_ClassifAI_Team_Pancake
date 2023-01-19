package kernel.structural.algorithms.layers

class Pooling extends CNNLayer{
    private Integer[] strides = new Integer[2]

    def strides (Integer[] strides) {
        this.strides = strides
    }

    @Override
    String getCode(int layerNumber) {
        return String.format("x%i = MaxPooling2D(pool_size=(%i, %i), strides=(%i, %i))(x%i)", layerNumber, 2, 2, strides[0], strides[1], layerNumber-1)
    }
}
