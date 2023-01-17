package kernel.structural.imports

import kernel.generator.Visitor

class ImportLib extends Import {

    private String libName

    private ImportAs importAs

    @Override
    void accept(Visitor<StringBuffer> visitor) {
        visitor.visit(this)
    }

    setLibName(String name) {
        this.libName = name
    }

    getLibName() {
        libName
    }

    setImportAs(ImportAs importAs) {
        this.importAs = importAs
    }

    getImportAs() {
        return importAs
    }
}
