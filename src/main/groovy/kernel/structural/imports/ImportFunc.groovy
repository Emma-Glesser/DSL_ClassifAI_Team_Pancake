package kernel.structural.imports

import kernel.generator.Visitor

class ImportFunc extends Import {

    private String funcName

    private ImportFrom importFrom

    @Override
    void accept(Visitor<StringBuffer> visitor) {
        visitor.visit(this)
    }

    setFuncName(String name) {
        this.funcName = name
    }

    getFuncName() {
        return funcName
    }

    setImportFrom(ImportFrom importFrom) {
        this.importFrom = importFrom
    }

    getImportFrom() {
        return importFrom
    }
}
