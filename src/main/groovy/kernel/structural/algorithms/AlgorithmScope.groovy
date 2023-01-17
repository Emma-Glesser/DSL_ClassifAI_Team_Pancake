package kernel.structural.algorithms

class AlgorithmScope {
    private List<ClassifAIAlgorithm> algorithms

    private static final AlgorithmScope instance = new AlgorithmScope()

    static AlgorithmScope getInstance() {
        return instance
    }

    AlgorithmScope() {
        this.algorithms = new ArrayList<>()
    }

    def cnn(String name, @DelegatesTo(strategy=Closure.DELEGATE_ONLY, value=CNN) Closure cl) {
        ClassifAIAlgorithm algo = new CNN()
        algo.name = name
        algo.with(cl)
        this.algorithms.add(algo)
    }

    def svm(String name, @DelegatesTo(strategy=Closure.DELEGATE_ONLY, value=SVM) Closure cl) {
        ClassifAIAlgorithm algo = new SVM()
        algo.name = name
        algo.with(cl)
        this.algorithms.add(algo)
    }

    def knn(String name, @DelegatesTo(strategy=Closure.DELEGATE_ONLY, value=KNN) Closure cl) {
        ClassifAIAlgorithm algo = new KNN()
        algo.name = name
        algo.with(cl)
        this.algorithms.add(algo)
    }

    def randomForest(String name, @DelegatesTo(strategy=Closure.DELEGATE_ONLY, value=RandomForest) Closure cl) {
        ClassifAIAlgorithm algo = new RandomForest()
        algo.name = name
        algo.with(cl)
        this.algorithms.add(algo)
    }

    List<ClassifAIAlgorithm> getAlgorithmList() {
        return algorithms
    }
}
