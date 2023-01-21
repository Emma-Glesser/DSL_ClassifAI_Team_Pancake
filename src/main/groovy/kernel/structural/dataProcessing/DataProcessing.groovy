package kernel.structural.dataProcessing

import kernel.generator.Visitor
import kernel.structural.Code
import kernel.structural.Invalid_DSL_SyntaxeException

class DataProcessing extends Code {

    private List<ProcessingStep> processingStepList

    private boolean acquisitionDone = false
    private boolean selectionDone = false

    DataProcessing() {
        this.processingStepList = new ArrayList<ProcessingStep>()
    }

    List<ProcessingStep> getProcessingStepList() {
        return processingStepList
    }

    def acquisition(@DelegatesTo(strategy = Closure.DELEGATE_ONLY, value = Acquisition) Closure cl) {
        if (acquisitionDone) {
            throw new Invalid_DSL_SyntaxeException("Data acquisition can only be defined once")
        }
        Acquisition acquisition = new Acquisition()
        acquisition.with(cl)
        this.processingStepList.add(acquisition)
        acquisitionDone = true
    }

    def selection(@DelegatesTo(strategy = Closure.DELEGATE_ONLY, value = Selection) Closure cl) {
        if (!acquisitionDone) {
            throw new Invalid_DSL_SyntaxeException("Data selection must be defined after data acquisition")
        }
        if (selectionDone) {
            throw new Invalid_DSL_SyntaxeException("Data selection can only be defined once")
        }
        Selection selection = new Selection()
        selection.with(cl)
        this.processingStepList.add(selection)
        selectionDone = true
    }

    def preprocessing(@DelegatesTo(strategy = Closure.DELEGATE_ONLY, value = Preprocessing) Closure cl) {
        if (!acquisitionDone) {
            throw new Invalid_DSL_SyntaxeException("Data preprocessing must be defined after data acquisition")
        }
        if (!selectionDone) {
            throw new Invalid_DSL_SyntaxeException("Data preprocessing must be defined after data selection")
        }
        Preprocessing preprocessing = new Preprocessing()
        preprocessing.with(cl)
        this.processingStepList.add(preprocessing)
    }

    def visualizeDataset(String dataset) {
        if (!acquisitionDone) {
            throw new Invalid_DSL_SyntaxeException("Dataset visualization must be defined after data acquisition")
        }
        DatasetVisualization datasetVisualization = new DatasetVisualization(dataset)
        this.processingStepList.add(datasetVisualization)
    }

    @Override
    void accept(Visitor<StringBuffer> visitor) {
        visitor.visit(this)
    }

    // return last object of type Preprocessing of the list
    Integer[] getLastShape() {
        Preprocessing lastPreprocessing = this.processingStepList
                .findAll { it instanceof Preprocessing }
                .last() as Preprocessing
        return lastPreprocessing.getReshape()
    }
}