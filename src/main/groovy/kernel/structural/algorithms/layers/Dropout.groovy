package kernel.structural.algorithms.layers

import kernel.structural.Invalid_DSL_SyntaxeException

class Dropout extends CNNLayer{
    private Float rateOfDisabledNeurons

    void setRateOfDisabledNeurons(Float rateOfDisabledNeurons) {
        if (this.rateOfDisabledNeurons != null) {
            throw new Invalid_DSL_SyntaxeException("Can't call `setRateOfDisabledNeurons` twice in a same layer")
        }
        this.rateOfDisabledNeurons = rateOfDisabledNeurons
    }

    @Override
    String getCode(int layerNumber) {
        if (rateOfDisabledNeurons == null) {
            throw new Invalid_DSL_SyntaxeException("Dropout layer should have a rate_of_disabled_neurons value")
        }
        if (rateOfDisabledNeurons < 0 || rateOfDisabledNeurons > 1) {
            throw new Invalid_DSL_SyntaxeException("Dropout layer should have a rate_of_disabled_neurons value between 0 and 1")
        }
        return String.format(Locale.US,"x%d = Dropout(%.2f)(x%d)", layerNumber, rateOfDisabledNeurons, layerNumber-1)
    }
}
