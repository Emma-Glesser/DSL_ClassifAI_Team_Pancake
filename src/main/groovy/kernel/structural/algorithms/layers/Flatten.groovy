package kernel.structural.algorithms.layers

class Flatten extends CNNLayer {

    @Override
    String getCode(int layerNumber) {
        return String.format("x%i = Flatten()(x%i)", layerNumber, layerNumber-1)
    }
}
