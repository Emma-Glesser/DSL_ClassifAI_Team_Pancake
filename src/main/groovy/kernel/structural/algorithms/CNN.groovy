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
    public int epochs
    public int batch_size

    List<CNNLayer> getLayers() {
        return layers
    }

    int getEpochs() {
        if (epochs <= 0) {
            throw new RuntimeException("Epochs must be greater than 0")
        }
        return epochs
    }

    int getBatchSize() {
        if (batch_size <= 0) {
            throw new RuntimeException("Batch size must be greater than 0")
        }
        return batch_size
    }

    @Override
    void accept(Visitor<StringBuffer> visitor) {
        visitor.visit(this)
    }

    def normalizationLayer() {
        CNNLayer layer = new Normalization()
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

    def flattenLayer() {
        CNNLayer layer = new Flatten()
        this.layers.add(layer)
    }

    def denseLayer(@DelegatesTo(strategy=Closure.DELEGATE_ONLY, value= Dense) Closure cl) {
        CNNLayer layer = new Dense()
        layer.with(cl)
        this.layers.add(layer)
    }
}
