package kernel.structural

import kernel.generator.Visitor

class Pooling extends CNNLayer{
    private Integer[] strides = new Integer[2];

    Pooling(Integer[] strides, String comment){
        super(comment);
        this.strides = strides;
    }

    Integer[] getStrides() {
        return strides
    }

    void setStrides(Integer[] strides) {
        this.strides = strides
    }

    @Override
    public void accept(Visitor<StringBuffer> visitor) {
        visitor.visit(this);
    }

    def strides (Integer[] strides) {
        this.strides = strides;
    }

}
