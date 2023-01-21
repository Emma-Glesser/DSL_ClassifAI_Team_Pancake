package kernel.structural.algorithms.layers

class Normalization extends CNNLayer {

    @Override
    String getCode(int layerNumber) {
        return String.format("x%d = BatchNormalization()(x%d)", layerNumber, layerNumber-1)
    }
}
