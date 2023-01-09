package kernel.generator;

import java.util.HashMap;
import java.util.Map;

import kernel.App;
import kernel.behavioral.ActionActuator;
import kernel.behavioral.ActionPause;
import kernel.behavioral.ActionVariable;
import kernel.behavioral.State;
import kernel.behavioral.Transition;
import kernel.condition.LogicalCondition;
import kernel.condition.RelationalCondition;
import kernel.condition.UnaryCondition;
import kernel.structural.Actuator;
import kernel.structural.Program;
import kernel.structural.Sensor;
import kernel.structural.Variable;

public abstract class Visitor<T> {

	public abstract void visit(App app);
	public abstract void visit(Variable app);

    public abstract void visit(Program program);

	public abstract void visit(State state);
	public abstract void visit(Transition transition);
	public abstract void visit(ActionActuator action);
	public abstract void visit(ActionVariable action);
	public abstract void visit(ActionPause action);
	public abstract void visit(LogicalCondition condition);
	public abstract void visit(RelationalCondition condition);
	public abstract void visit(UnaryCondition condition);

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

