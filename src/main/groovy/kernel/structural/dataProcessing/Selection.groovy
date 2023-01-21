package kernel.structural.dataProcessing

class Selection extends ProcessingStep {
    private int testSize
    private boolean shuffleData

    boolean getShuffleData() {
        return shuffleData
    }

    int getTestSize() {
        return testSize
    }

    void setTestSize (int testSize) {
        this.testSize = testSize
    }

    void setShuffleData (boolean shuffleData) {
        this.shuffleData = shuffleData
    }

    String getCode() {
        return String.format("\"# Data selection\\n\"\n" +
                "    \"X_train, X_test, y_train, y_test = train_test_split(X, y, test_size = %f, random_state = SEED)\\n\"\n",this.getTestSize())
    }
}
