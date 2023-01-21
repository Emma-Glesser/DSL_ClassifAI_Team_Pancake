package kernel.structural.dataProcessing

import kernel.structural.Invalid_DSL_SyntaxeException

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
            throw new Invalid_DSL_SyntaxeException("Reshape should have 3 dimensions")
        }
        this.reshape = reshape
    }

    void setNormalize (Integer normalize) {
        if (normalize < 0 || normalize > 255) {
            throw new Invalid_DSL_SyntaxeException("Normalization should be between 0 and 255")
        }
        this.normalize = normalize
    }

    String getCode() {
        String preprocessing = new String()
        if(reshape != null) {
            String reshapeLine = String.format("    \"X_train = X_train.values.reshape(-1, %d,%d,%d)\\n\",\n" +
                    "    \"X_test = X_test.values.reshape(-1, %d,%d,%d)\\n\"", reshape[0], reshape[1], reshape[2], reshape[0], reshape[1], reshape[2])
            preprocessing += reshapeLine
        }
        if(reshape != null && normalize != null) {
            preprocessing += "\n,\n";
        }
        if(normalize != null) {
            String normalizeLine = String.format("    \"X_train = X_train/(%d)\\n\",\n" +
                    "    \"X_test = X_test/(%d)\\n\"", normalize, normalize)
            preprocessing += normalizeLine
        }
        return String.format("\"# Data preprocessing : reshape and normalization\\n\",\n%s", preprocessing);
    }
}
