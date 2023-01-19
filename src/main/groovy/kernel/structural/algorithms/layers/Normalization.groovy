package kernel.structural.algorithms.layers

class Normalization extends CNNLayer {

    @Override
    String getCode(int layerNumber) {
        return String.format("x%i = Normalization()(x%i)", layerNumber, layerNumber-1)
    }
}
