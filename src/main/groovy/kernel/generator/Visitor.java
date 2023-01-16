package kernel.generator;

import java.util.HashMap;
import java.util.Map;

import kernel.App;
<<<<<<< Updated upstream
import kernel.structural.Acquisition;
import kernel.structural.Comparison;
import kernel.structural.DataProcessing;
=======
import kernel.structural.Comparison;
>>>>>>> Stashed changes
import kernel.structural.Import;
import kernel.structural.arduino.Actuator;
import kernel.structural.Program;
import kernel.structural.arduino.Sensor;
import kernel.structural.arduino.Variable;

public abstract class Visitor<T> {

	public abstract void visit(App app);
	public abstract void visit(Variable app);

    public abstract void visit(Program program);

	public abstract void visit(Import state);
    public abstract void visit(Comparison comparison);
	public abstract void visit(DataProcessing dataProcessing);

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

