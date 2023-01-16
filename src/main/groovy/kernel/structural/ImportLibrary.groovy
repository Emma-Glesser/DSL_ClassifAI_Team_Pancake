package kernel.structural

class ImportLibrary {
    private String libName
    private ImportLibraryAs importLibraryAs

    ImportLibraryAs lib(String libName) {
        this.libName = libName
        ImportLibraryAs x = new ImportLibraryAs()
        this.importLibraryAs = x
        x
    }
}
