package kernel.structural;

abstract class ClassifAIAlgorithm extends Code {
    String name;
    ClassifAIAlgorithm(String name, String comment) {
        super(comment);
        this.name = name;
    }

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    def cnn(String name, @DelegatesTo(strategy=Closure.DELEGATE_ONLY, value=CNN) Closure cl) {
        this.name = name;
        Closure code = cl.rehydrate(this, this, this)
        code.resolveStrategy = Closure.DELEGATE_ONLY
        code()
    }

    def svm(String name, @DelegatesTo(strategy=Closure.DELEGATE_ONLY, value=SVM) Closure cl) {
        this.name = name;
        Closure code = cl.rehydrate(this, this, this)
        code.resolveStrategy = Closure.DELEGATE_ONLY
        code()
    }

    def knn(String name, @DelegatesTo(strategy=Closure.DELEGATE_ONLY, value=KNN) Closure cl) {
        this.name = name;
        Closure code = cl.rehydrate(this, this, this)
        code.resolveStrategy = Closure.DELEGATE_ONLY
        code()
    }

    def randomForest(String name, @DelegatesTo(strategy=Closure.DELEGATE_ONLY, value=RandomForest) Closure cl) {
        this.name = name;
        Closure code = cl.rehydrate(this, this, this)
        code.resolveStrategy = Closure.DELEGATE_ONLY
        code()
    }
}
