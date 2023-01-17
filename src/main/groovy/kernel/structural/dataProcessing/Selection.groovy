package kernel.structural.dataProcessing

class Selection {
    private int testSize;
    private boolean shuffleData;

    boolean getShuffleData() {
        return shuffleData
    }

    int getTestSize() {
        return testSize
    }

    void testSize (int testSize) {
        this.testSize = testSize;
    }

    void shuffleData (boolean shuffleData) {
        this.shuffleData = shuffleData;
    }
}
