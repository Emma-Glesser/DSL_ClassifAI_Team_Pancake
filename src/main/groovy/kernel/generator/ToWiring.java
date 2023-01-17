package kernel.generator;

import dsl.ClassifAI_DSL_Binding;
import groovy.lang.MissingPropertyException;
import kernel.App;
import kernel.structural.*;
import kernel.structural.Code;
import kernel.structural.dataProcessing.DataProcessing;
import java.util.ArrayList;
import java.util.List;

import kernel.structural.comparison.Comparison;
import kernel.structural.imports.Import;
/**
 * Quick and dirty visitor to support the generation of Wiring code
 */
public class ToWiring extends Visitor<StringBuffer> {
	enum PASS {ONE, TWO}

    List<AlgorithmImports> imports = new ArrayList<AlgorithmImports>();

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

        write("\n# States prototypes */\n");
		for(State state: app.getStates()){
			state.accept(this);
		}

		write("\n# Cellule de code \n");
		for(Code code: app.getProgram().getCodes()){
			code.accept(this);
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
		write("\n# Cellule d'import' \n");
        if(context.get("pass") == PASS.ONE) {
//            write("%s %s = %s;\n", variable.getType(), variable.getName(), variable.getValue());
        }
    }

    @Override
    public void visit(Comparison comparisonCode) {
		write("\n# Cellule de comparaison des performances des algorithmes \n");
        if(context.get("pass") == PASS.ONE) {
//            write("%s %s = %s;\n", variable.getType(), variable.getName(), variable.getValue());
        }
    }

	@Override
	public void visit(DataProcessing dataProcessing) {
		write("\n# Cellule de traitement des données \n");
		if(context.get("pass") == PASS.ONE) {
//            write("%s %s = %s;\n", variable.getType(), variable.getName(), variable.getValue());
		}
	}

    @Override
    public void visit(CNN cnn) {
        write("\n# CNN algorithm cell \n");
        if(context.get("pass") == PASS.ONE) {

        }
    }

    @Override
    public void visit(SVM svm) {
        write(svm.getComment());
        write("\n# SVM algorithm cell \n");
        if(!imports.contains(AlgorithmImports.SVM_IMPORT)) {
            imports.add(AlgorithmImports.SVM_IMPORT);
        }
        write("svm=svm.SVC()\n");
        write("model_1=svm.fit(X_train,y_train)\n");
        write("pred_1=model_1.predict(X_test)\n");
        write("print(classification_report(y_test,pred_1))\n");
    }

    @Override
    public void visit(KNN knn) {
        write(knn.getComment());
        write("\n# KNN algorithm cell \n");
        if(!imports.contains(AlgorithmImports.KNN_IMPORT)) {
            imports.add(AlgorithmImports.KNN_IMPORT);
        }
        write("classifier = KNeighborsClassifier(n_neighbors=k)\n");
        write("classifier = classifier.fit(train_images_k, train_labels_k)\n");
        write("pred = classifier.predict(test_images_k)\n");
        write("accuracy = accuracy_score(test_labels_k, pred)\n");
    }

    @Override
    public void visit(RandomForest randomForest) {
        write(randomForest.getComment());
        write("\n# RandomForest algorithm cell \n");
        if(!imports.contains(AlgorithmImports.RANDOM_FOREST)) {
            imports.add(AlgorithmImports.RANDOM_FOREST);
        }
        write("rf=RandomForestClassifier(n_estimators=100)\n");
        write("rf.fit(X_train,y_train)\n");
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
