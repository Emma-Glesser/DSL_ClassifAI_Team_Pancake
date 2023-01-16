package kernel.structural

class Acquisition {
    private String filePath;
    private String setName;

    String getFilePath() {
        return filePath
    }

    void setFilePath(String filePath) {
        this.filePath = filePath
    }

    String getSetName() {
        return setName
    }

    void setSetName(String setName) {
        this.setName = setName
    }

    public void filePath (String filePath) {
        this.filePath = filePath;
    }

    public void setName (String setName) {
        this.setName = setName;
    }
}
