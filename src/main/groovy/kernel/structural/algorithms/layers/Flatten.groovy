package kernel.structural.algorithms.layers

class Flatten extends CNNLayer {

    @Override
    String getCode(int layerNumber) {
        return String.format("x%d = Flatten()(x%d)", layerNumber, layerNumber-1)
    }
}
