package kernel.generator;

import java.util.HashMap;
import java.util.Map;

import kernel.App;
import kernel.structural.arduino.Actuator;
import kernel.structural.arduino.Program;
import kernel.structural.arduino.Sensor;
import kernel.structural.arduino.Variable;

public abstract class Visitor<T> {

	public abstract void visit(App app);
	public abstract void visit(Variable app);

    public abstract void visit(Program program);

	public abstract void visit(Import state);
    public abstract void visit(Comparison comparison);


	public abstract void visit(Actuator actuator);
	public abstract void visit(Sensor sensor);


	/***********************
	 ** Helper mechanisms **
	 ***********************/

	protected Map<String,Object> context = new HashMap<>();

	protected T result;

	public T getResult() {
		return result;
	}

}

