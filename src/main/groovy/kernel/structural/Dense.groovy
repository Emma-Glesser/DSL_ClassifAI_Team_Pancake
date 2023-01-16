package kernel.structural

import kernel.generator.Visitor

class Dense extends CNNLayer {

    private Integer units;

    Dense(Integer units, String comment){
        super(comment);
    }

    Integer getUnits() {
        return units
    }

    void setUnits(Integer units) {
        this.units = units
    }

    @Override
    public void accept(Visitor<StringBuffer> visitor) {
        visitor.visit(this);
    }

    def units(Integer units) {
        this.units = units;
    }
}
