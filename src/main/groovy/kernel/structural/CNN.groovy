package kernel.structural;

import kernel.generator.Visitor;

class CNN extends ClassifAIAlgorithm {

    private List<CNNLayer> layers;

    CNN (String name, String comment, List<CNNLayer> layers) {
        super(name, comment);
        this.layers = layers;
    }

    List<CNNLayer> getLayers() {
        return layers
    }

    void setLayers(List<CNNLayer> layers) {
        this.layers = layers
    }

    @Override
    public void accept(Visitor<StringBuffer> visitor) {
        visitor.visit(this);
    }

    def normalizationLayer(@DelegatesTo(strategy=Closure.DELEGATE_ONLY, value=Normalization) Closure cl) {
        Closure code = cl.rehydrate(this, this, this)
        code.resolveStrategy = Closure.DELEGATE_ONLY
        code()
    }

    def convolutionLayer(@DelegatesTo(strategy=Closure.DELEGATE_ONLY, value=Convolution) Closure cl) {
        Closure code = cl.rehydrate(this, this, this)
        code.resolveStrategy = Closure.DELEGATE_ONLY
        code()
    }

    def dropoutLayer(@DelegatesTo(strategy=Closure.DELEGATE_ONLY, value=Dropout) Closure cl) {
        Closure code = cl.rehydrate(this, this, this)
        code.resolveStrategy = Closure.DELEGATE_ONLY
        code()
    }

    def poolingLayer(@DelegatesTo(strategy=Closure.DELEGATE_ONLY, value=Pooling) Closure cl) {
        Closure code = cl.rehydrate(this, this, this)
        code.resolveStrategy = Closure.DELEGATE_ONLY
        code()
    }

    def flattenLayer(@DelegatesTo(strategy=Closure.DELEGATE_ONLY, value=Flatten) Closure cl) {
        Closure code = cl.rehydrate(this, this, this)
        code.resolveStrategy = Closure.DELEGATE_ONLY
        code()
    }

    def denseLayer(@DelegatesTo(strategy=Closure.DELEGATE_ONLY, value=Dense) Closure cl) {
        Closure code = cl.rehydrate(this, this, this)
        code.resolveStrategy = Closure.DELEGATE_ONLY
        code()
    }
}
