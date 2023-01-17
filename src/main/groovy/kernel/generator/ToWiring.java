package kernel.generator;

import kernel.App;
import kernel.structural.*;
import kernel.structural.algorithms.CNN;
import kernel.structural.algorithms.KNN;
import kernel.structural.algorithms.RandomForest;
import kernel.structural.algorithms.SVM;
import kernel.structural.algorithms.layers.Convolution;
import kernel.structural.algorithms.layers.Dense;
import kernel.structural.algorithms.layers.Dropout;
import kernel.structural.algorithms.layers.Flatten;
import kernel.structural.algorithms.layers.Normalization;
import kernel.structural.algorithms.layers.Pooling;
import kernel.structural.dataProcessing.DataProcessing;
import java.util.ArrayList;
import java.util.List;

import kernel.structural.comparison.Comparison;
import kernel.structural.imports.Import;
import kernel.structural.imports.ImportFunc;
import kernel.structural.imports.ImportLib;
import kernel.structural.visualization.Visualization;

/**
 * Quick and dirty visitor to support the generation of Wiring code
 */
public class ToWiring extends Visitor<StringBuffer> {
	enum PASS {ONE, TWO}

    List<AlgorithmImports> imports = new ArrayList<>();

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

        write("{\n" +
                " \"cells\": [");

        app.getProgram().accept(this);

//		write("\n# Cellule de code \n");
//		for(Import imp: app.getProgram().getImport()){
//			imp.accept(this);
//		}

        write("\n ],\n" +
                " \"metadata\": {\n" +
                "  \"kernelspec\": {\n" +
                "   \"display_name\": \"Python 3 (ipykernel)\",\n" +
                "   \"language\": \"python\",\n" +
                "   \"name\": \"python3\"\n" +
                "  },\n" +
                "  \"language_info\": {\n" +
                "   \"codemirror_mode\": {\n" +
                "    \"name\": \"ipython\",\n" +
                "    \"version\": 3\n" +
                "   },\n" +
                "   \"file_extension\": \".py\",\n" +
                "   \"mimetype\": \"text/x-python\",\n" +
                "   \"name\": \"python\",\n" +
                "   \"nbconvert_exporter\": \"python\",\n" +
                "   \"pygments_lexer\": \"ipython3\",\n" +
                "   \"version\": \"3.6.8\"\n" +
                "  }\n" +
                " },\n" +
                " \"nbformat\": 4,\n" +
                " \"nbformat_minor\": 4\n" +
                "}\n");
	}

    @Override
    public void visit(Program program) {
        if(context.get("pass") == PASS.ONE) {

            write("\n  {\n" +
                    "   \"cell_type\": \"markdown\",\n" +
                    "   \"source\": [\n" +
                    "    \"# %s\\n\",\n" +
                    "    \"## MNIST Data Mining Algorithm Comparison\"\n" +
                    "   ],\n" +
                    "   \"metadata\": {}\n" +
                    "  }", program.getName());
        }
    }

    @Override
    public void visit(ImportLib importLib) {
		write("\n# Cellule d'import' \n");
        if(context.get("pass") == PASS.ONE) {
//            write("%s %s = %s;\n", variable.getType(), variable.getName(), variable.getValue());
        }
    }

    @Override
    public void visit(ImportFunc importFunc) {
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
    public void visit(Visualization visualization) {

    }

    @Override
    public void visit(Convolution convolution) {

    }

    @Override
    public void visit(Dense dense) {

    }

    @Override
    public void visit(Dropout dropout) {

    }

    @Override
    public void visit(Flatten flatten) {

    }

    @Override
    public void visit(Normalization normalization) {

    }

    @Override
    public void visit(Pooling pooling) {

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
}
