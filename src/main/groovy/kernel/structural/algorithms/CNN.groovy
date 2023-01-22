package kernel.structural.algorithms

import kernel.generator.Visitor
import kernel.structural.Invalid_DSL_SyntaxeException
import kernel.structural.algorithms.layers.CNNLayer
import kernel.structural.algorithms.layers.Convolution
import kernel.structural.algorithms.layers.Dense
import kernel.structural.algorithms.layers.Dropout
import kernel.structural.algorithms.layers.Flatten
import kernel.structural.algorithms.layers.Normalization
import kernel.structural.algorithms.layers.Pooling

class CNN extends ClassifAIAlgorithm {

    private List<CNNLayer> layers
    private int epochs
    private int batchSize

    CNN() {
        this.layers = new ArrayList<>()
    }

    List<CNNLayer> getLayers() {
        return layers
    }

    int getEpochs() {
        epochs
    }

    int getBatchSize() {
        batchSize
    }

    void setEpochs(int epochs) {
        if (epochs <= 0) {
            throw new Invalid_DSL_SyntaxeException("Epochs must be greater than 0")
        }
        this.epochs = epochs
    }

    void setBatchSize(int batchSize) {
        if (batchSize <= 0) {
            throw new Invalid_DSL_SyntaxeException("Batch size must be greater than 0")
        }
        this.batchSize = batchSize
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

    def definedAs(@DelegatesTo(strategy=Closure.DELEGATE_ONLY, value=CNN) Closure cl) {
        Closure code = cl.rehydrate(this, this, this)
        code.resolveStrategy = Closure.DELEGATE_ONLY
        code()
    }
}
