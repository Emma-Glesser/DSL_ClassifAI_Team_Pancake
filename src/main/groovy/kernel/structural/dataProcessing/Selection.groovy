package kernel.structural.dataProcessing

import kernel.generator.Visitable
import kernel.generator.Visitor

class Selection implements Visitable {
    private int testSize
    private boolean shuffleData

    boolean getShuffleData() {
        return shuffleData
    }

    int getTestSize() {
        return testSize
    }

    void testSize (int testSize) {
        this.testSize = testSize
    }

    void shuffleData (boolean shuffleData) {
        this.shuffleData = shuffleData
    }

    void accept(Visitor<StringBuffer> visitor) {
        visitor.visit(this)
    }

    String getCode() {
        return String.format("\"# Data selection\\n\"\n" +
                "    \"X_train, X_test, y_train, y_test = train_test_split(X, y, test_size = %f, random_state = SEED)\\n\"\n",this.getTestSize());
    }
}
