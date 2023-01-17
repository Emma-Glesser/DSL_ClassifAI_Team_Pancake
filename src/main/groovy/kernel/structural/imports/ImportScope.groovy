package kernel.structural.imports

class ImportScope {
    private List<Import> imports

    private static final ImportScope instance = new ImportScope()

    static ImportScope getInstance() {
        return instance
    }

    ImportScope() {
        this.imports = new ArrayList<>()
    }

    ImportAs lib(String libName) {
        Import i = new ImportLib()
        ImportAs importAs = new ImportAs()
        i.libName = libName
        i.importAs = importAs
        this.imports.add(i)
        importAs
    }

    ImportFrom func(String funcName) {
        Import i = new ImportFunc()
        ImportFrom importFrom = new ImportFrom()
        i.funcName = funcName
        i.importFrom = importFrom
        this.imports.add(i)
        importFrom
    }

    getImportList() {
        return imports
    }
}
