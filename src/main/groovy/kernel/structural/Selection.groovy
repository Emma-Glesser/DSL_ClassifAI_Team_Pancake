package kernel.structural

class Selection {
    private int testSize;
    private boolean shuffleData;

    boolean getShuffleData() {
        return shuffleData
    }

    void setShuffleData(boolean shuffleData) {
        this.shuffleData = shuffleData
    }

    int getTestSize() {
        return testSize
    }

    void setTestSize(int testSize) {
        this.testSize = testSize
    }

    public void testSize (int testSize) {
        this.testSize = testSize;
    }

    public void shuffleData (boolean shuffleData) {
        this.shuffleData = shuffleData;
    }
}
