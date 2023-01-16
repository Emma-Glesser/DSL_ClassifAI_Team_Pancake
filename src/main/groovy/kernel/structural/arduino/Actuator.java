package kernel.structural.arduino;

import kernel.generator.Visitor;

public class Actuator extends Component {
    Actuator(String name) {
        super(name);
    }

    @Override
	public void accept(Visitor<StringBuffer> visitor) {
		visitor.visit(this);
	}
}
