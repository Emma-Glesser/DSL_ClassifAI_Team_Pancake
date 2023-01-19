package kernel.generator;

import java.util.HashMap;
import java.util.Map;

import kernel.App;
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
import kernel.structural.comparison.Comparison;
import kernel.structural.dataProcessing.Acquisition;
import kernel.structural.dataProcessing.DataProcessing;
import kernel.structural.Program;
import kernel.structural.dataProcessing.Preprocessing;
import kernel.structural.dataProcessing.Selection;
import kernel.structural.imports.ImportFunc;
import kernel.structural.imports.ImportLib;
import kernel.structural.visualization.Visualization;

public abstract class Visitor<T> {

	public abstract void visit(App app);
    public abstract void visit(Program program);

    public abstract void visit(Comparison comparison);
	public abstract void visit(DataProcessing dataProcessing);
	public abstract void visit(Acquisition acquisition);
	public abstract void visit(Selection selection);
	public abstract void visit(Preprocessing preprocessinf);
	public abstract void visit(Visualization visualization);

    public abstract void visit(CNN cnn);
    public abstract void visit(SVM svm);
    public abstract void visit(KNN knn);
    public abstract void visit(RandomForest randomForest);

    public abstract void visit(Convolution convolution);
    public abstract void visit(Dense dense);
    public abstract void visit(Dropout dropout);
    public abstract void visit(Flatten flatten);
    public abstract void visit(Normalization normalization);
    public abstract void visit(Pooling pooling);

    public abstract void visit(ImportLib importLib);
    public abstract void visit(ImportFunc importFunc);


	/***********************
	 ** Helper mechanisms **
	 ***********************/

	protected Map<String,Object> context = new HashMap<>();

	protected T result;

	public T getResult() {
		return result;
	}

}

