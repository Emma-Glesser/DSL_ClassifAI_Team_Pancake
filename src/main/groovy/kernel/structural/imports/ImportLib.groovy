package kernel.structural.imports

import kernel.generator.Visitor

class ImportLib extends Import {

    private String libName

    private ImportAs importAs

    @Override
    void accept(Visitor<StringBuffer> visitor) {
        visitor.visit(this)
    }

    void setLibName(String name) {
        this.libName = name
    }

    String getLibName() {
        libName
    }

    void setImportAs(ImportAs importAs) {
        this.importAs = importAs
    }

    ImportAs getImportAs() {
        return importAs
    }
}
