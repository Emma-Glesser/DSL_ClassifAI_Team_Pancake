package kernel.structural.dataProcessing

class Acquisition {
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
}
