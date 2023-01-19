package kernel.structural.algorithms.layers;

public enum ActivationFunction {
    RELU("relu"),
    SIGMOID("sigmoid"),
    SOFTMAX("softmax");

    private final String value;

    private ActivationFunction(String value) {
        this.value = value;
    }

    String getValue() {
        return this.value;
    }
}
