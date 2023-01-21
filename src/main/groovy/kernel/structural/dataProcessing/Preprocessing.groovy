package kernel.structural.dataProcessing

class Preprocessing extends ProcessingStep {
    private Integer[] reshape = new Integer[3]
    private Integer normalize

    Integer[] getReshape() {
        return reshape
    }

    Integer getNormalize() {
        return normalize
    }

    void setReshape (Integer[] reshape) {
        if (reshape.length != 3) {
            throw new RuntimeException("Reshape should have 3 dimensions")
        }
        this.reshape = reshape
    }

    void setNormalize (Integer normalize) {
        if (normalize < 0 || normalize > 255) {
            throw new RuntimeException("Normalization should be between 0 and 255")
        }
        this.normalize = normalize
    }

    String getCode() {
        String preprocessing = "    \"X_train_CNN, X_test_CNN, Y_train_CNN, Y_test_CNN = X_train, X_test, Y_train, Y_test\\n\",\n";
        if(reshape != null) {
            String reshapeLine = String.format("    \"X_train_CNN = X_train_CNN.values.reshape(-1, %d,%d,%d)\\n\",\n" +
                    "    \"X_test_CNN = X_test_CNN.values.reshape(-1, %d,%d,%d)\\n\"", reshape[0], reshape[1], reshape[2], reshape[0], reshape[1], reshape[2])
            preprocessing += reshapeLine
        }
        if(reshape != null && normalize != null) {
            preprocessing += "\n,\n";
        }
        if(normalize != null) {
            String normalizeLine = String.format("    \"X_train_CNN = X_train_CNN/(%d)\\n\",\n" +
                    "    \"X_test_CNN = X_test_CNN/(%d)\\n\"\n,\n", normalize, normalize)
            preprocessing += normalizeLine
        }
        preprocessing += String.format("    \"Y_train_CNN = pd.get_dummies(Y_train_CNN).values\\n\",\n" +
                "    \"Y_test_CNN = pd.get_dummies(Y_test_CNN).values\\n\"");

        return String.format("\"# Data preprocessing for CNN : reshape and normalization\\n\",\n%s",preprocessing);
    }
}
