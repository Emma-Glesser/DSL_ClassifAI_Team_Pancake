package kernel.structural

import kernel.generator.Visitor

class Visualization extends Code {

    def Visualization(String comment) {
        super(comment)
    }

    def datasetVisualization (@DelegatesTo(strategy = Closure.DELEGATE_ONLY, value = DatasetVisualization) Closure cl) {
        Closure code = cl.rehydrate(this, this, this)
        code.resolveStrategy = Closure.DELEGATE_ONLY
        code()
    }

    def resultsVisualization (@DelegatesTo(strategy = Closure.DELEGATE_ONLY, value = ResultsVisualization) Closure cl) {
        Closure code = cl.rehydrate(this, this, this)
        code.resolveStrategy = Closure.DELEGATE_ONLY
        code()
    }

    @Override
    void accept(Visitor<StringBuffer> visitor) {
        visitor.visit(this)
    }
}
