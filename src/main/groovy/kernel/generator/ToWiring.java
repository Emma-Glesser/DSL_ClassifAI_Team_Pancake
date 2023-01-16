package kernel.generator;

import java.util.List;

import dsl.ClassifAI_DSL_Binding;
import groovy.lang.MissingPropertyException;
import kernel.App;
import kernel.behavioral.Action;
import kernel.behavioral.ActionActuator;
import kernel.behavioral.ActionPause;
import kernel.behavioral.ActionVariable;
import kernel.behavioral.State;
import kernel.behavioral.Transition;
import kernel.condition.Condition;
import kernel.condition.LogicalCondition;
import kernel.condition.RelationalCondition;
import kernel.condition.UnaryCondition;
import kernel.structural.arduino.Actuator;
import kernel.structural.arduino.Component;
import kernel.structural.Program;
import kernel.structural.arduino.Sensor;
import kernel.structural.arduino.Variable;
import kernel.structural.Actuator;
import kernel.structural.Comparison;
import kernel.structural.Component;
import kernel.structural.Import;
import kernel.structural.Sensor;
import kernel.structural.Variable;

/**
 * Quick and dirty visitor to support the generation of Wiring code
 */
public class ToWiring extends Visitor<StringBuffer> {
	enum PASS {ONE, TWO}


	public ToWiring() {
		this.result = new StringBuffer();
	}

	private void write(String s) {
		result.append(String.format("%s",s));
	}

    private void write(String string, Object... args) {
        result.append(String.format(string, args));
    }
	@Override
	public void visit(App app) {
		//first pass, create global vars
		context.put("pass", PASS.ONE);

        app.getProgram().accept(this);

        write("\n/* Components */\n");
        for(Component component : app.getComponents()){
            component.accept(this);
        }

        write("\n/* Variables */\n");
        for(Variable variable: app.getVariables()) {
            variable.accept(this);
        }

        write("\n/* States prototypes */\n");
		for(State state: app.getStates()){
			state.accept(this);
		}

		//second pass, setup and loop
		context.put("pass",PASS.TWO);
		write("\nvoid setup(){\n");
		for(Component component : app.getComponents()){
			component.accept(this);
		}
		write("}\n");

		write("\nvoid loop() {\n");
        write("\t%s();\n", app.getProgram().getInitialState().getName());
        write("}\n");

        for(State state: app.getStates()){
            write("\n");
            state.accept(this);
		}
	}

    @Override
    public void visit(Program program) {
        if(context.get("pass") == PASS.ONE) {
            write("/**** Program %s ****/\n", program.getName());
        }
    }

    @Override
    public void visit(Import importCode) {
        if(context.get("pass") == PASS.ONE) {
//            write("%s %s = %s;\n", variable.getType(), variable.getName(), variable.getValue());
        }
    }

    @Override
    public void visit(Comparison comparisonCode) {
        if(context.get("pass") == PASS.ONE) {
//            write("%s %s = %s;\n", variable.getType(), variable.getName(), variable.getValue());
        }
    }

    @Override
	public void visit(Actuator actuator) {
		if(context.get("pass") == PASS.ONE) {
            write("const byte %s = %d;\n", actuator.getName(), actuator.getPin());
		}
		if(context.get("pass") == PASS.TWO) {
			write("\tpinMode(%s, OUTPUT);\n", actuator.getName());
		}
	}


	@Override
	public void visit(Sensor sensor) {
		if(context.get("pass") == PASS.ONE) {
            write("const byte %s = %d;\n", sensor.getName(), sensor.getPin());
		}
		if(context.get("pass") == PASS.TWO) {
			write("\tpinMode(%s, INPUT);\n", sensor.getName());
		}
	}

	@Override
	public void visit(State state) {
		if(context.get("pass") == PASS.ONE){
			write("void %s();\n", state.getName());
		}
		if(context.get("pass") == PASS.TWO) {
			write("void %s() {\n", state.getName());
            write("\tdelay(300);\n");
            write("\twhile(1) {\n");

			for (Action action : state.getActions()) {
				action.accept(this);
			}

            for (Transition transition : state.getTransitions()) {
                transition.accept(this);
            }

            write("\t}\n}\n");
		}
	}

	@Override
	public void visit(Transition transition) {
		if(context.get("pass") == PASS.TWO) {
            if (transition.getCondition() != null) {
                write("\t\tif (");
                transition.getCondition().accept(this);
                write(") {\n");
                write("\t");
            }
            try {
                Object variable = ClassifAI_DSL_Binding.getInstance().getVariable(transition.getNextStateName());
                if (!(variable instanceof State)) {
                    throw new RuntimeException(String.format("Identifiers `%s` is not a State. Is `%s`", transition.getNextStateName(), variable.getClass().getName()));
                }
            } catch (MissingPropertyException ignored) {
                throw new RuntimeException(String.format("State `%s` is not defined", transition.getNextStateName()));
            }
            write("\t\t%s();\n", transition.getNextStateName());
            if (transition.getCondition() != null) {
                write("\t\t}\n");
            }
		}
	}

    @Override
    public void visit(UnaryCondition condition) {
        if(context.get("pass") == PASS.TWO) {
            write("!");
            if (condition.getCondition() != null) {
                condition.getCondition().accept(this);
            }
            else {
                write("%s", condition.getVariable().getName());
            }
        }
    }

    @Override
    public void visit(LogicalCondition condition) {
        if(context.get("pass") == PASS.TWO) {
            write("(");
            List<Condition> conditions = condition.getConditions();
            for (int i = 0; i < conditions.size(); i++) {
                conditions.get(i).accept(this);
                if (i != conditions.size() - 1) {
                    write(" %s ", OperatorToString.convert(condition.getOperator().toString()));
                }
            }
            write(")");
        }
    }

    @Override
    public void visit(RelationalCondition condition) {
        if(context.get("pass") == PASS.TWO) {
            write("%s %s %s", condition.getLeft(), OperatorToString.convert(condition.getOperator().toString()), condition.getRight());
        }
    }

	@Override
	public void visit(ActionActuator action) {
		if(context.get("pass") == PASS.TWO) {
			write(String.format("\t\tdigitalWrite(%s, %s);\n",action.getActuator().getName(), action.getValue()));
		}
	}

    @Override
    public void visit(ActionPause action) {
        if(context.get("pass") == PASS.TWO) {
            write(String.format("\t\tdelay(%d);\n",action.getTime()));
        }
    }

    @Override
    public void visit(ActionVariable action) {
        if(context.get("pass") == PASS.TWO) {
            write(String.format("\t\t%s = %s;\n",action.getVariable().getName(), action.getNewVariable()));
        }
    }
}
