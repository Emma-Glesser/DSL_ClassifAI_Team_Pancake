package kernel.structural.algorithms.layers

class Dropout extends CNNLayer{
    private Float rateOfDisabledNeurons

    void setRateOfDisabledNeurons(Float rateOfDisabledNeurons) {
        if (this.rateOfDisabledNeurons != null) {
            throw new RuntimeException("Can't call `setRateOfDisabledNeurons` twice in a same layer")
        }
        this.rateOfDisabledNeurons = rateOfDisabledNeurons
    }

    @Override
    String getCode(int layerNumber) {
        if (rateOfDisabledNeurons == null) {
            throw new RuntimeException("Dropout layer should have a rate_of_disabled_neurons value")
        }
        if (rateOfDisabledNeurons < 0 || rateOfDisabledNeurons > 1) {
            throw new RuntimeException("Dropout layer should have a rate_of_disabled_neurons value between 0 and 1")
        }
        return String.format("x%i = Dropout(%f)(x%i)", layerNumber, rateOfDisabledNeurons, layerNumber-1)
    }
}
