package kernel.structural.arduino;

import kernel.generator.Visitor;

public class Sensor extends Component {
    Sensor(String name) {
        super(name);
    }

    @Override
	public void accept(Visitor<StringBuffer> visitor) {
		visitor.visit(this);
	}
}
