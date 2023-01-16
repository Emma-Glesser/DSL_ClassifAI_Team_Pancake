package kernel.structural

import kernel.generator.Visitor

class DataProcessing extends Code {

    private Acquisition dataAcquisition;
    private Selection dataSelection;
    private Preprocessing preprocessing;

    def DataProcessing(String comment) {
        super(comment)
    }

    Acquisition getDataAcquisition() {
        return dataAcquisition
    }

    void setDataAcquisition(Acquisition dataAcquisition) {
        this.dataAcquisition = dataAcquisition
    }

    Selection getDataSelection() {
        return dataSelection
    }

    void setDataSelection(Selection dataSelection) {
        this.dataSelection = dataSelection
    }

    Preprocessing getPreprocessing() {
        return preprocessing
    }

    void setPreprocessing(Preprocessing preprocessing) {
        this.preprocessing = preprocessing
    }

    def acquisition (@DelegatesTo(strategy = Closure.DELEGATE_ONLY, value = Acquisition) Closure cl) {
        Closure code = cl.rehydrate(this, this, this)
        code.resolveStrategy = Closure.DELEGATE_ONLY
        code()
    }

    def selection (@DelegatesTo(strategy = Closure.DELEGATE_ONLY, value = Selection) Closure cl) {
        Closure code = cl.rehydrate(this, this, this)
        code.resolveStrategy = Closure.DELEGATE_ONLY
        code()
    }

    def preprocessing (@DelegatesTo(strategy = Closure.DELEGATE_ONLY, value = Preprocessing) Closure cl) {
        Closure code = cl.rehydrate(this, this, this)
        code.resolveStrategy = Closure.DELEGATE_ONLY
        code()
    }

    @Override
    void accept(Visitor<StringBuffer> visitor) {
        visitor.visit(this)
    }
}
