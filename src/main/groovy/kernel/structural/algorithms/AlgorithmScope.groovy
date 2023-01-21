package kernel.structural.algorithms

import dsl.ClassifAI_DSL_Binding

class AlgorithmScope {
    private List<ClassifAIAlgorithm> algorithms

    private static final AlgorithmScope instance = new AlgorithmScope()

    static AlgorithmScope getInstance() {
        return instance
    }

    AlgorithmScope() {
        this.algorithms = new ArrayList<>()
    }

    CNN cnn(String name) {
        ClassifAIAlgorithm algo = new CNN()
        algo.name = name
        ClassifAI_DSL_Binding.instance.setVariable(name, algo)
        this.algorithms.add(algo)
        algo
    }

    def svm(String name) {
        ClassifAIAlgorithm algo = new SVM()
        algo.name = name
        ClassifAI_DSL_Binding.instance.setVariable(name, algo)
        this.algorithms.add(algo)
        algo
    }

    KNN knn(String name) {
        ClassifAIAlgorithm algo = new KNN()
        algo.name = name
        ClassifAI_DSL_Binding.instance.setVariable(name, algo)
        this.algorithms.add(algo)
        algo
    }

    RandomForest randomForest(String name) {
        ClassifAIAlgorithm algo = new RandomForest()
        algo.name = name
        ClassifAI_DSL_Binding.instance.setVariable(name, algo)
        this.algorithms.add(algo)
        algo
    }

    List<ClassifAIAlgorithm> getAlgorithmList() {
        return algorithms
    }
}
