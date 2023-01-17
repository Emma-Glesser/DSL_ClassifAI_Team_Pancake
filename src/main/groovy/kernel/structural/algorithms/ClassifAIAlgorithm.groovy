package kernel.structural.algorithms

import kernel.structural.Code

abstract class ClassifAIAlgorithm extends Code {
    String name

    String getName() {
        return name
    }

    void setName(String name) {
        this.name = name
    }


}
