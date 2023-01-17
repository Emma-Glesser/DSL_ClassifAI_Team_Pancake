package kernel.structural.imports

class ImportFrom {
    private String name
    private ImportAs importAs

    ImportAs from(String name) {
        this.name = name
        ImportAs importAs = new ImportAs()
        this.importAs = importAs
        importAs
    }
}
