package kernel.structural

import dsl.ClassifAI_DSL.Param
import kernel.NamedElement
import kernel.generator.Visitable
import kernel.generator.Visitor
import kernel.structural.algorithms.AlgorithmScope
import kernel.structural.algorithms.ClassifAIAlgorithm
import kernel.structural.dataProcessing.DataProcessing
import kernel.structural.imports.Import
import kernel.structural.imports.ImportScope
import kernel.structural.visualization.Visualization

class Program implements NamedElement, Visitable {
    private String name

    private List<Import> importList
    private List<ClassifAIAlgorithm> algorithmList
    private DataProcessing dataProcessing
    private Visualization visualization
    public Param comparisonParameter

    private boolean importsDefined = false
    private boolean algorithmsDefined = false

    Program() {
        this.importList = new ArrayList<>()
        this.algorithmList = new ArrayList<>()
    }


    @Override
    void setName(String name) {
        this.name = name
    }

    String getName() {
        return name
    }

    @Override
    void accept(Visitor visitor) {
        visitor.visit(this)
    }

    void name(String name) {
        this.name = name
    }

    def imports(@DelegatesTo(strategy = Closure.DELEGATE_ONLY, value = ImportScope) Closure cl) {
        if (importsDefined) {
            throw new RuntimeException("Imports can only be defined once")
        }
        ImportScope importScope = ImportScope.instance
        importScope.with(cl)
        this.importList = importScope.getImportList()
        importsDefined = true
    }

    def dataProcessing(@DelegatesTo(strategy = Closure.DELEGATE_ONLY, value = DataProcessing) Closure cl) {
        if (dataProcessing != null) {
            throw new RuntimeException("Data processing can only be defined once")
        }
        DataProcessing dataProcessing = new DataProcessing()
        dataProcessing.with(cl)
        this.dataProcessing = dataProcessing
    }

    def algorithms(@DelegatesTo(strategy = Closure.DELEGATE_ONLY, value = AlgorithmScope) Closure cl) {
        if (algorithmsDefined) {
            throw new RuntimeException("Algorithms can only be defined once")
        }
        AlgorithmScope algorithmScope = AlgorithmScope.instance
        algorithmScope.with(cl)
        this.algorithmList = algorithmScope.getAlgorithmList()
        algorithmsDefined = true
    }

    def visualization(@DelegatesTo(strategy = Closure.DELEGATE_ONLY, value = Visualization) Closure cl) {
        if (visualization != null) {
            throw new RuntimeException("Visualization can only be defined once")
        }
        Visualization visualization = new Visualization()
        visualization.with(cl)
        this.visualization = visualization
    }

    List<Import> getImport() {
        importList
    }

    List<ClassifAIAlgorithm> getAlgorithms() {
        algorithmList
    }

    DataProcessing getDataProcessing() {
        dataProcessing
    }

    Visualization getVisualization() {
        visualization
    }
}
