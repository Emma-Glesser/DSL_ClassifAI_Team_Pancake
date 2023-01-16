package kernel.structural;

import kernel.generator.Visitor;

import java.util.List;

public class CNN extends ClassifAIAlgorithm {

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
