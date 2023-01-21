package kernel.structural.dataProcessing

class Acquisition extends ProcessingStep {
    private String filePath
    private String setName

    String getFilePath() {
        return filePath
    }

    String getSetName() {
        return setName
    }

    void setFilePath (String filePath) {
        this.filePath = filePath
    }

    void setSetName (String setName) {
        this.setName = setName
    }

    String getCode(){
        return String.format("\"# Data acquisition\\n\",\n" +
                "    \"%s = pd.read_csv(%s)\\n\"",this.setName,this.filePath)
    }

}
