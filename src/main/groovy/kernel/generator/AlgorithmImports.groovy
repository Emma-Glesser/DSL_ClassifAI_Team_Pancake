package kernel.generator

enum AlgorithmImports {
    SVM_IMPORT("from sklearn import svm"),
    KNN_IMPORT("from sklearn.neighbors import KNeighborsClassifier"),
    RANDOM_FOREST("from sklearn.ensemble import RandomForestClassifier");

    private final String value

    private AlgorithmImports(String value) {
        this.value = value
    }

    String getValue() {
        return this.value
    }


}