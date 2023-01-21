package kernel.structural.visualization

class DatasetVisualization extends Visualization {
    private String setToVisualize

    DatasetVisualization(String setToVisualize) {
        this.setToVisualize = setToVisualize
    }

    String getSetToVisualize() {
        return setToVisualize
    }
}
