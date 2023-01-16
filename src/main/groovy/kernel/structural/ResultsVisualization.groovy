package kernel.structural

class ResultsVisualization extends Visualization {
    private List<String> algorithms_to_visualize;

    List<String> getAlgorithms_to_visualize() {
        return algorithms_to_visualize
    }

    void setAlgorithms_to_visualize(List<String> algorithms_to_visualize) {
        this.algorithms_to_visualize = algorithms_to_visualize
    }
}
