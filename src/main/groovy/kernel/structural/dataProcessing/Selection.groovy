package kernel.structural.dataProcessing

class Selection extends ProcessingStep {
    private Float testSize
    private Boolean shuffleData

    boolean getShuffleData() {
        return shuffleData
    }

    float getTestSize() {
        return testSize
    }

    void setTestSize(Float testSize) {
        if (this.testSize != null) {
            throw new RuntimeException("Test size can only be defined once")
        }
        if (testSize < 0 || testSize > 1) {
            throw new RuntimeException("Test size must be between 0 and 1")
        }
        this.testSize = testSize
    }

    void setShuffleData (boolean shuffleData) {
        this.shuffleData = shuffleData
    }

    String getCode() {
        return String.format("\"# Data selection\\n\",\n" +
                "    \"Y = dataset[\'label\']\\n\",\n" +
                "    \"X = dataset.drop(columns=[\'label\'])\\n\",\n" +
                "    \"X_train, X_test, Y_train, Y_test = train_test_split(X, Y, test_size = %f, shuffle=%s)\\n\\n\"",
                this.getTestSize(), this.getShuffleData() ? "True" : "False")
    }
}