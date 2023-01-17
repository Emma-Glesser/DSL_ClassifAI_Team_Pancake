package kernel.generator;

import java.util.HashMap;
import java.util.Map;

import kernel.App;
import kernel.structural.comparison.Comparison;
import kernel.structural.dataProcessing.DataProcessing;
import kernel.structural.Program;

public abstract class Visitor<T> {

	public abstract void visit(App app);
    public abstract void visit(Program program);

    public abstract void visit(Comparison comparison);
	public abstract void visit(DataProcessing dataProcessing);


	/***********************
	 ** Helper mechanisms **
	 ***********************/

	protected Map<String,Object> context = new HashMap<>();

	protected T result;

	public T getResult() {
		return result;
	}

}

