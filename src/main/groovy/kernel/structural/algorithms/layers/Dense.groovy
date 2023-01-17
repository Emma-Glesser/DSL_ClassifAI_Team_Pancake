package kernel.structural.algorithms.layers

import kernel.generator.Visitor

class Dense extends CNNLayer {

    private Integer units

    Integer getUnits() {
        return units
    }

    void setUnits(Integer units) {
        this.units = units
    }

    @Override
    void accept(Visitor<StringBuffer> visitor) {
        visitor.visit(this)
    }

    def units(Integer units) {
        this.units = units
    }
}
