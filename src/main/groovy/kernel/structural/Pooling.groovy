package kernel.structural

import kernel.generator.Visitor

class Pooling extends CNNLayer{
    public int[] strides = new Integer[2];

    Pooling(int[] strides){
        this.strides = strides;
    }

    @Override
    public void accept(Visitor<StringBuffer> visitor) {
        visitor.visit(this);
    }


}
