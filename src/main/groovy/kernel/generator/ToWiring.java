package kernel.generator;

import dsl.ClassifAI_DSL;
import kernel.App;
import kernel.structural.*;
import kernel.structural.algorithms.CNN;
import kernel.structural.algorithms.ClassifAIAlgorithm;
import kernel.structural.algorithms.KNN;
import kernel.structural.algorithms.RandomForest;
import kernel.structural.algorithms.SVM;
import kernel.structural.algorithms.layers.CNNLayer;
import kernel.structural.dataProcessing.Acquisition;
import kernel.structural.dataProcessing.DataProcessing;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import kernel.structural.comparison.Comparison;
import kernel.structural.dataProcessing.Preprocessing;
import kernel.structural.dataProcessing.Selection;
import kernel.structural.imports.ImportFunc;
import kernel.structural.imports.ImportLib;
import kernel.structural.visualization.Visualization;

/**
 * Quick and dirty visitor to support the generation of Wiring code
 */
public class ToWiring extends Visitor<StringBuffer> {
	enum PASS {ONE, TWO}

    private enum CellType {
        Markdown {
            @Override
            public String toString() {
                return "markdown";
            }
        },
        Code {
            @Override
            public String toString() {
                return "code";
            }
        }
    }

	public ToWiring() {
		this.result = new StringBuffer();
	}

	private void write(String s) {
		result.append(String.format("%s",s));
	}

    private void write(String string, Object... args) {
        result.append(String.format(string, args));
    }

    private void writeMarkDownCell(String string, Object... args) {
       writeCell(string, CellType.Markdown, args);
    }

    private void writeCodeCell(String string, Object... args) {
        writeCell(string,CellType.Code, args);
    }

    private void writeCell(String string, CellType type, Object... args) {
        result.append(String.format("\n  {\n" +
                "   \"cell_type\": \"%s\",\n" +
                "   \"source\": [\n" +
                "%s\n" +
                "   ],\n" +
                "   \"metadata\": {}\n" +
                "  },",type.toString(), String.format(string, args)));
    }

    private void removeLastComma() {
        int lastComma = result.lastIndexOf(",");
        result.deleteCharAt(lastComma);
    }

    private final Set<AlgorithmImports> imports = new HashSet<>();
    private void writeImport(List<ClassifAIAlgorithm> algorithms){
        for (ClassifAIAlgorithm algorithm : algorithms) {
            switch (algorithm.getClass().getSimpleName()) {
                case "SVM":
                    imports.add(AlgorithmImports.SVM_IMPORT);
                    break;
                case "KNN":
                    imports.add(AlgorithmImports.KNN_IMPORT);
                    break;
                case "RandomForest":
                    imports.add(AlgorithmImports.RANDOM_FOREST_IMPORT);
                    break;
                case "CNN":
                    imports.add(AlgorithmImports.CNN_IMPORT);
                    break;
            }
        }
        if (imports.contains(AlgorithmImports.CNN_IMPORT)) {
            writeCodeCell(AlgorithmImports.CNN_IMPORT.getImports());
        }
        if (imports.contains(AlgorithmImports.KNN_IMPORT)) {
            writeCodeCell(AlgorithmImports.KNN_IMPORT.getImports());
        }
        if (imports.contains(AlgorithmImports.RANDOM_FOREST_IMPORT)) {
            writeCodeCell(AlgorithmImports.RANDOM_FOREST_IMPORT.getImports());
        }
        if (imports.contains(AlgorithmImports.SVM_IMPORT)) {
            writeCodeCell(AlgorithmImports.SVM_IMPORT.getImports());
        }
        writeCodeCell(AlgorithmImports.PANDAS_IMPORT.getImports());
        writeCodeCell(AlgorithmImports.DATA_SELECTION_IMPORT.getImports());

    }

	@Override
	public void visit(App app) {
        write("{\n" +
                " \"cells\": [");

        app.getProgram().accept(this);


        writeImport(app.getProgram().getAlgorithms());

//      for(Import imp : app.getProgram().getImport()){
//			imp.accept(this);
//		}

        app.getProgram().getDataProcessing().accept(this);

        for (ClassifAIAlgorithm algo : app.getProgram().getAlgorithms()) {
            algo.accept(this);
        }

        if (app.getProgram().comparisonParameter == ClassifAI_DSL.Param.Accuracy) {

        }
        else if (app.getProgram().comparisonParameter == ClassifAI_DSL.Param.ExecTime) {

        }
        app.getProgram().getVisualization().accept(this);

        removeLastComma();

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
        writeMarkDownCell("    \"# %s\\n\",\n" +
                "    \"## MNIST Data Mining Algorithm Comparison\"", program.getName());
    }

    @Override
    public void visit(ImportLib importLib) {
		write("\n# Cellule d'import de librairie' \n");
    }

    @Override
    public void visit(ImportFunc importFunc) {
        write("\n# Cellule d'import de fonction' \n");
    }

    @Override
    public void visit(Comparison comparisonCode) {
		write("\n# Cellule de comparaison des performances des algorithmes \n");
    }

	@Override
	public void visit(DataProcessing dataProcessing) {
        writeMarkDownCell("### %s\n",dataProcessing.getComment());
        writeCodeCell("    \"# Data processing \"%s\\n\\n\"\n" +
                "    \"%s\\n\"\n" +
                "    \"%s\\n\"\n"+
                "    \"%s\\n\"\n", dataProcessing.getDataAcquisition().getCode(), dataProcessing.getDataSelection().getCode(), dataProcessing.getPreprocessing().getCode());
	}

    @Override
    public void visit(Acquisition acquisition) { }

    @Override
    public void visit(Selection selection) { }

    @Override
    public void visit(Preprocessing preprocessing) { }

    @Override
    public void visit(Visualization visualization) {

    }

    @Override
    public void visit(CNN cnn) {
        writeMarkDownCell(
                "### %s\n"+
                        "\n"+
                        "%s", cnn.getName(), cnn.getComment());


        StringBuilder cnnBuilder = new StringBuilder();

        cnnBuilder.append(
                        "    \"# building the ConvNet\\n\",\n" +
                        "    \"x0=Input(shape=(28,28,1))\\n\",\n" +
                        "    \"\\n\",\n" +
                        "    \"# convolutional layers\\n\",\n"
        );

        List<CNNLayer> layers = cnn.getLayers();
        for (int i = 0; i < layers.size(); i++) {
            cnnBuilder.append(String.format("    \"%s\\n\",\n",layers.get(i).getCode(i)));
        }

        cnnBuilder.append(
                        "    \"\\n\",\n" +
                        "    \"model=Model(inputs=x0,outputs=x7)\\n\",\n" +
                        "    \"\\n\",\n" +
                        "    \"# compiling and fitting the model\\n\",\n" +
                        "    \"model.compile(optimizer='rmsprop',loss='categorical_crossentropy',metrics=['accuracy'])\\n\",\n" +
                        "    \"model.fit(X_train,y_train,epochs=4,batch_size=64,validation_data=(X_test,y_test))\""
        );

        writeCodeCell(cnnBuilder.toString());
    }

    @Override
    public void visit(SVM svm) {
        writeMarkDownCell(
                "### %s\n"+
                        "\n"+
                        "%s",svm.getName(),svm.getComment());

        // Code de vincent SVM
        writeCodeCell(
                "\"param_grid = {'C': [0.001, 0.01, 0.1, 1, 10, 100, 1000],\\n\"\n" +
                        "\"              'gamma': [1, 0.1, 0.01, 0.001, 0.0001],\\n\"\n" +
                        "\"              'kernel': ['poly']}\\n\"\n" +
                        "\"classifier  = GridSearchCV(SVC(), param_grid, refit = True, verbose = 3)\\n\"\n" +
                        "\"classifier.fit(X_train, Y_train)\\n\"\n" +
                        "\"classifier.score(X_test, Y_test)\""
        );
    }

    @Override
    public void visit(KNN knn) {
        writeMarkDownCell(
                "### %s\n"+
                        "\n"+
                        "%s",knn.getName(),knn.getComment());

        // Code de vincent knn
        writeCodeCell(
                " \"classifier = KNeighborsClassifier(n_neighbors=%s)\\n\"\n" +
                        "  \"classifier = classifier.fit(X_train, Y_train)\\n\"\n" +
                        "  \"pred = classifier.predict(X_test)\\n\"\n" +
                        "  \"accuracy = accuracy_score(Y_test, pred)\\n\"",knn.getK()
        );
    }

    @Override
    public void visit(RandomForest randomForest) {
        writeMarkDownCell(
                "### %s\n"+
                        "\n"+
                        "%s",randomForest.getName(),randomForest.getComment());
        writeCodeCell(
                " \"classifier = RandomForestClassifier(n_estimators=%s)\\n\"\n" +
                        "  \"classifier = classifier.fit(X_train, Y_train)\\n\"\n" +
                        "  \"pred = classifier.predict(X_test)\\n\"\n" +
                        "  \"accuracy = accuracy_score(Y_test, pred)\"",randomForest.getNb_estimators()
        );
    }
}
