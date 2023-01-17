package kernel.structural.algorithms

import kernel.generator.Visitor
import kernel.structural.algorithms.layers.CNNLayer
import kernel.structural.algorithms.layers.Convolution
import kernel.structural.algorithms.layers.Dense
import kernel.structural.algorithms.layers.Dropout
import kernel.structural.algorithms.layers.Flatten
import kernel.structural.algorithms.layers.Normalization
import kernel.structural.algorithms.layers.Pooling

class CNN extends ClassifAIAlgorithm {

    private List<CNNLayer> layers

    List<CNNLayer> getLayers() {
        return layers
    }

    void setLayers(List<CNNLayer> layers) {
        this.layers = layers
    }

    @Override
    void accept(Visitor<StringBuffer> visitor) {
        visitor.visit(this)
    }

    def normalizationLayer(@DelegatesTo(strategy=Closure.DELEGATE_ONLY, value= Normalization) Closure cl) {
        CNNLayer layer = new Normalization()
        layer.with(cl)
        this.layers.add(layer)
    }

    def convolutionLayer(@DelegatesTo(strategy=Closure.DELEGATE_ONLY, value= Convolution) Closure cl) {
        CNNLayer layer = new Convolution()
        layer.with(cl)
        this.layers.add(layer)
    }

    def dropoutLayer(@DelegatesTo(strategy=Closure.DELEGATE_ONLY, value= Dropout) Closure cl) {
        CNNLayer layer = new Dropout()
        layer.with(cl)
        this.layers.add(layer)
    }

    def poolingLayer(@DelegatesTo(strategy=Closure.DELEGATE_ONLY, value= Pooling) Closure cl) {
        CNNLayer layer = new Pooling()
        layer.with(cl)
        this.layers.add(layer)
    }

    def flattenLayer(@DelegatesTo(strategy=Closure.DELEGATE_ONLY, value= Flatten) Closure cl) {
        CNNLayer layer = new Flatten()
        layer.with(cl)
        this.layers.add(layer)
    }

    def denseLayer(@DelegatesTo(strategy=Closure.DELEGATE_ONLY, value= Dense) Closure cl) {
        CNNLayer layer = new Dense()
        layer.with(cl)
        this.layers.add(layer)
    }
}
