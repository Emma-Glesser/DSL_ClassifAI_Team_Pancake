package kernel.structural

import kernel.generator.Visitor

class Comparison extends Code {
    private List<Parameter> parameterList;

    Comparison(String comment) {
        super(comment)
    }

    @Override
    void accept(Visitor<StringBuffer> visitor) {
        visitor.visit(this)
    }

    List<Parameter> getParameterList() {
        return parameterList
    }

    void setParameterList(List<Parameter> parameterList) {
        this.parameterList = parameterList
    }
}
