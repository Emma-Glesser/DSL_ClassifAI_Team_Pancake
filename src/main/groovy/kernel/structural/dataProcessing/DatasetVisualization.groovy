package kernel.structural.dataProcessing

class DatasetVisualization extends ProcessingStep {
    private String setToVisualize

    DatasetVisualization(String setToVisualize) {
        this.setToVisualize = setToVisualize
    }

    String getSetToVisualize() {
        return setToVisualize
    }

    @Override
    String getCode() {
        return String.format("    \"print(%s)\\n\"", setToVisualize)
    }
}
