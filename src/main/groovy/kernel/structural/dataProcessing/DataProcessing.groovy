package kernel.structural.dataProcessing

import kernel.generator.Visitor
import kernel.structural.Code
import kernel.structural.visualization.DatasetVisualization

class DataProcessing extends Code {

    private Acquisition dataAcquisition
    private Selection dataSelection
    private Preprocessing preprocessing
    private DatasetVisualization datasetVisualization

    Acquisition getDataAcquisition() {
        return dataAcquisition
    }

    Selection getDataSelection() {
        return dataSelection
    }

    Preprocessing getPreprocessing() {
        return preprocessing
    }

    def acquisition(@DelegatesTo(strategy = Closure.DELEGATE_ONLY, value = Acquisition) Closure cl) {
        if (dataAcquisition != null) {
            throw new RuntimeException("Data acquisition can only be defined once")
        }
        Acquisition acquisition = new Acquisition()
        acquisition.with(cl)
        this.dataAcquisition = acquisition
    }

    def selection(@DelegatesTo(strategy = Closure.DELEGATE_ONLY, value = Selection) Closure cl) {
        if (dataSelection != null) {
            throw new RuntimeException("Data selection can only be defined once")
        }
        Selection selection = new Selection()
        selection.with(cl)
        this.dataSelection = selection
    }

    def preprocessing(@DelegatesTo(strategy = Closure.DELEGATE_ONLY, value = Preprocessing) Closure cl) {
        if (this.preprocessing != null) {
            throw new RuntimeException("Data preprocessing can only be defined once")
        }
        Preprocessing preprocessing = new Preprocessing()
        preprocessing.with(cl)
        this.preprocessing = preprocessing
    }

    def visualizeDataset (String dataset) {
        DatasetVisualization datasetVisualization = new DatasetVisualization(dataset)
        this.datasetVisualization = datasetVisualization

    }

    @Override
    void accept(Visitor<StringBuffer> visitor) {
        visitor.visit(this)
    }
}
