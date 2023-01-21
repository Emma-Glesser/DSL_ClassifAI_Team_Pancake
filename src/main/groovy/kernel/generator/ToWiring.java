package kernel.generator;

import dsl.ClassifAI_DSL;
import dsl.ClassifAI_DSL_Binding;
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

import kernel.structural.dataProcessing.Preprocessing;
import kernel.structural.dataProcessing.ProcessingStep;
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
        writeCodeCell(AlgorithmImports.DATA_SELECTION_IMPORT.getImports());
        writeCodeCell(AlgorithmImports.DATA_ACQUISTION_IMPORT.getImports());
        writeCodeCell(AlgorithmImports.DATA_PREPROCESSING_IMPORT.getImports());
        writeCodeCell(AlgorithmImports.DATA_VISUALISATION_IMPORT.getImports());
    }

	@Override
	public void visit(App app) {
        write("{\n" +
                " \"cells\": [");

        app.getProgram().accept(this);


        writeImport(app.getProgram().getAlgorithms());

        app.getProgram().getDataProcessing().accept(this);

        for (ClassifAIAlgorithm algo : app.getProgram().getAlgorithms()) {
            algo.accept(this);
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
	public void visit(DataProcessing dataProcessing) {
        writeMarkDownCell(
                "    \"### Data processing\\n\",\n"+
                        "    \"\\n\",\n"+
                        "    \"%s\"", dataProcessing.getComment());
        StringBuilder dataProcessingBuilder = new StringBuilder();
        int i=0;
        for (ProcessingStep processingStep : dataProcessing.getProcessingStepList()) {
            dataProcessingBuilder.append(String.format( "    %s\n", processingStep.getCode()));
            if(i!=dataProcessing.getProcessingStepList().size()-1) {
                dataProcessingBuilder.append(",\n");
            }
            i++;
        }
        writeCodeCell(dataProcessingBuilder.toString());
    }

    @Override
    public void visit(Acquisition acquisition) { }

    @Override
    public void visit(Selection selection) { }

    @Override
    public void visit(Preprocessing preprocessing) { }

    @Override
    public void visit(Visualization visualization) {
        if (visualization.getComment() != null) {
            writeMarkDownCell(
                    "    \"### Data visualization\\n\",\n"+
                            "    \"\\n\",\n"+
                            "    \"%s\"", visualization.getComment());
        } else {
            writeMarkDownCell("    \"### Data visualization\"");
        }

        if(visualization.getComparison_factor() == ClassifAI_DSL.Param.Accuracy){
            StringBuilder names = new StringBuilder();
            StringBuilder accuracyVar = new StringBuilder();
            names.append("    \"names=[");
            accuracyVar.append("    \"acc=[");
            for(String name : visualization.getAlgorithmNames()){
                names.append(String.format("\'%s\',", name));
                accuracyVar.append(String.format("%s_acc,", name));
            }
            names.deleteCharAt(names.lastIndexOf(","));
            names.append("]\\n\",\n");

            accuracyVar.deleteCharAt(accuracyVar.lastIndexOf(","));
            accuracyVar.append("]\\n\",\n");

            writeCodeCell(names.toString() +
                                accuracyVar.toString() +
                                "    \"plt.figure(figsize=(10,8))\\n\",\n" +
                                "    \"graph = plt.barh(names,acc)\\n\",\n" +
                                "    \"plt.xlabel('Accuracy')\\n\",\n" +
                                "    \"plt.ylabel('Models')\""
                    );
        }

        if(visualization.getComparison_factor() == ClassifAI_DSL.Param.ExecTime){
            StringBuilder names = new StringBuilder();
            StringBuilder time = new StringBuilder();
            names.append("    \"names=[");
            time.append("    \"times=[");
            for(String name : visualization.getAlgorithmNames()){
                names.append(String.format("\'%s\',", name));
                time.append(String.format("%s_time,", name));
            }
            names.deleteCharAt(names.lastIndexOf(","));
            names.append("]\\n\",\n");

            time.deleteCharAt(time.lastIndexOf(","));
            time.append("]\\n\",\n");

            writeCodeCell(names.toString() +
                    time.toString() +
                    "    \"plt.figure(figsize=(10,8))\\n\",\n" +
                    "    \"graph = plt.barh(names,acc)\\n\",\n" +
                    "    \"plt.xlabel('Execution Time')\\n\",\n" +
                    "    \"plt.ylabel('Models')\""
            );
        }

    }

    @Override
    public void visit(CNN cnn) {
        if (cnn.getComment() != null) {
            writeMarkDownCell(
                    "    \"### %s\\n\",\n"+
                            "    \"\\n\",\n"+
                            "    \"%s\"", cnn.getName(), cnn.getComment());
        } else {
            writeMarkDownCell(
                    "    \"### %s\"", cnn.getName());
        }

        StringBuilder cnnBuilder = new StringBuilder();

        Integer[] reshape = ClassifAI_DSL_Binding.getInstance().getClassifAI_DSLModel().getProgram().getDataProcessing().getLastShape();

        cnnBuilder.append(String.format(
                        "    \"# building the ConvNet\\n\",\n" +
                        "    \"x0=Input(shape=(%d,%d,%d))\\n\",\n" +
                        "    \"\\n\",\n" +
                        "    \"# layers\\n\",\n", reshape[0], reshape[1], reshape[2]));

        List<CNNLayer> layers = cnn.getLayers();
        for (int i = 0; i < layers.size(); i++) {
            cnnBuilder.append(String.format("    \"%s\\n\",\n",layers.get(i).getCode(i+1)));
        }

        cnnBuilder.append(String.format(
                                "    \"\\n\",\n" +
                                "    \"model=Model(inputs=x0,outputs=x%d)\\n\",\n" +
                                "    \"\\n\",\n" +
                                "    \"# compiling and fitting the model\\n\",\n" +
                                "    \"time1 = time.time()\\n\",\n" +
                                "    \"model.compile(optimizer='rmsprop',loss='categorical_crossentropy',metrics=['accuracy'])\\n\",\n" +
                                "    \"history = model.fit(X_train_CNN,Y_train_CNN,epochs=%d,batch_size=%d,validation_data=(X_test_CNN,Y_test_CNN))\\n\",\n"+
                                "    \"time2 = time.time()\\n\",\n"+
                                "    \"%s_acc = 0\\n\",\n"+
                                "    \"for acc in history.history[\'accuracy\'] :\\n\",\n"+
                                "    \"    %s_acc += acc\\n\",\n"+
                                "    \"%s_acc = %s_acc/len(history.history[\'accuracy\'])\\n\",\n"+
                                "    \"%s_time = time2-time1 * 1000.0\"" ,layers.size(), cnn.getEpochs(), cnn.getBatchSize(), cnn.getName(), cnn.getName(), cnn.getName(), cnn.getName(), cnn.getName())
        );

        writeCodeCell(cnnBuilder.toString());
    }

    @Override
    public void visit(SVM svm) {
        if (svm.getComment() != null) {
            writeMarkDownCell(
                    "    \"### %s\\n\",\n"+
                            "    \"\\n\",\n"+
                            "    \"%s\"", svm.getName(), svm.getComment());
        } else {
            writeMarkDownCell(
                    "    \"### %s\"", svm.getName());
        }

// https://www.kaggle.com/code/adoumtaiga/comparing-ml-models-for-classification
        writeCodeCell(
                    "    \"time1 = time.time()\\n\",\n" +
                        "    \"sv = LinearSVC(C=0.0001)\\n\",\n" +
                        "    \"%s = cross_val_score(sv, X_train, Y_train, cv = 8)\\n\",\n"+
                        "    \"time2 = time.time()\\n\",\n"+
                        "    \"%s_time = time2-time1 * 1000.0\\n\",\n"+
                        "    \"%s_acc = %s.mean()\"" , svm.getName(),svm.getName(),svm.getName(),svm.getName()
        );
    }

    @Override
    public void visit(KNN knn) {
        if (knn.getComment() != null) {
            writeMarkDownCell(
                    "    \"### %s\\n\",\n"+
                            "    \"\\n\",\n"+
                            "    \"%s\"", knn.getName(), knn.getComment());
        } else {
            writeMarkDownCell(
                    "    \"### %s\"", knn.getName());
        }
// https://www.kaggle.com/code/adoumtaiga/comparing-ml-models-for-classification
        writeCodeCell(
                    "    \"time1 = time.time()\\n\",\n" +
                        "    \"knn = KNeighborsClassifier(n_neighbors=%s)\\n\",\n" +
                        "    \"%s = cross_val_score(knn, X_train, Y_train, cv = 8)\\n\",\n"+
                        "    \"time2 = time.time()\\n\",\n"+
                        "    \"%s_time = time2-time1 * 1000.0\\n\",\n"+
                        "    \"%s_acc = %s.mean()\"",knn.getK(),knn.getName(),knn.getName(),knn.getName(),knn.getName()
        );
    }

    @Override
    public void visit(RandomForest randomForest) {
        if (randomForest.getComment() != null) {
            writeMarkDownCell(
                    "    \"### %s\\n\",\n"+
                            "    \"\\n\",\n"+
                            "    \"%s\"", randomForest.getName(), randomForest.getComment());
        } else {
            writeMarkDownCell(
                    "    \"### %s\"", randomForest.getName());
        }
// https://www.kaggle.com/code/adoumtaiga/comparing-ml-models-for-classification
        writeCodeCell(
                    "    \"time1 = time.time()\\n\",\n" +
                        "    \"rand = RandomForestClassifier(n_estimators=%s, max_depth=10)\\n\",\n" +
                        "    \"%s = cross_val_score(rand, X_train, Y_train, cv = 6)\\n\",\n"+
                        "    \"time2 = time.time()\\n\",\n"+
                        "    \"%s_time = time2-time1 * 1000.0\\n\",\n"+
                        "    \"%s_acc = %s.mean()\"" ,randomForest.getNbEstimators(),randomForest.getName(),randomForest.getName(),randomForest.getName(),randomForest.getName()
        );
    }
}
