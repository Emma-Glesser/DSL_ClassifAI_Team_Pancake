package kernel.structural;

import kernel.generator.Visitor;

class CNN extends ClassifAIAlgorithm {

    List<CNNLayer> layers;

    CNN (String name, String comment, List<CNNLayer> layers) {
        super(name, comment);
        this.layers = layers;
    }

    @Override
    public void accept(Visitor<StringBuffer> visitor) {
        visitor.visit(this);
    }
}
