package kernel.structural.imports

import kernel.generator.Visitor

class ImportFunc extends Import {

    private String funcName

    private ImportFrom importFrom

    @Override
    void accept(Visitor<StringBuffer> visitor) {
        visitor.visit(this)
    }

    void setFuncName(String name) {
        this.funcName = name
    }

    String getFuncName() {
        return funcName
    }

    void setImportFrom(ImportFrom importFrom) {
        this.importFrom = importFrom
    }

    ImportFrom getImportFrom() {
        return importFrom
    }
}
