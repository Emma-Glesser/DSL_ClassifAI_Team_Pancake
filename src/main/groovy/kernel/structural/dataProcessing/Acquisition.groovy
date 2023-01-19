package kernel.structural.dataProcessing

import kernel.generator.Visitable
import kernel.generator.Visitor

class Acquisition implements Visitable {
    private String filePath
    private String setName

    String getFilePath() {
        return filePath
    }

    String getSetName() {
        return setName
    }

    void filePath (String filePath) {
        this.filePath = filePath
    }

    void setName (String setName) {
        this.setName = setName
    }

    void accept(Visitor<StringBuffer> visitor) {
        visitor.visit(this)
    }
}
