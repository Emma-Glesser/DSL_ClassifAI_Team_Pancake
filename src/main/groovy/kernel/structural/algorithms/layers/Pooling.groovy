package kernel.structural.algorithms.layers

class Pooling extends CNNLayer{
    private Integer[] strides

    void setStrides(Integer[] strides) {
        if (this.strides != null) {
            throw new RuntimeException("Strides can only be defined once")
        }
        this.strides = strides
    }

    @Override
    String getCode(int layerNumber) {
        if (strides == null) {
            throw new RuntimeException("Pooling layer should have a stride value")
        }
        if (strides.length != 2) {
            throw new RuntimeException("Pooling layer should have a stride value of length 2")
        }
        if (strides[0] == null || strides[1] == null) {
            throw new RuntimeException("Pooling layer should have strides")
        }
        if (strides[0] < 1 || strides[1] < 1) {
            throw new RuntimeException("Pooling layer strides should be greater than 0")
        }
        return String.format("x%d = MaxPooling2D(pool_size=(%d, %d), strides=(%d, %d))(x%d)", layerNumber, 2, 2, strides[0], strides[1], layerNumber-1)
    }
}
