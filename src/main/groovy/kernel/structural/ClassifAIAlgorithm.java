package kernel.structural;

public abstract class ClassifAIAlgorithm extends Code {
    String name;
    ClassifAIAlgorithm(String name, String comment) {
        super(comment);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
