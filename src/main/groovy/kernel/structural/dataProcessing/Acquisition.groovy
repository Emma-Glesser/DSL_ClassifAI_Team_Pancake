package kernel.structural.dataProcessing

class Acquisition extends ProcessingStep {
    private String filePath

    String getFilePath() {
        return filePath
    }


    void setFilePath (String filePath) {
        this.filePath = filePath
    }

    String getCode(){
        return String.format("\"# Data acquisition\\n\",\n" +
                "    \"dataset = pd.read_csv(%s)\\n \\n\"",this.filePath)
    }

}
