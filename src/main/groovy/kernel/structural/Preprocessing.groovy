package kernel.structural

class Preprocessing {
    private boolean reshape;
    private boolean normalize;

    boolean getReshape() {
        return reshape
    }

    void setReshape(boolean reshape) {
        this.reshape = reshape
    }

    boolean getNormalize() {
        return normalize
    }

    void setNormalize(boolean normalize) {
        this.normalize = normalize
    }

    public void reshape (boolean reshape) {
        this.reshape = reshape;
    }

    public void normalize (boolean normalize) {
        this.normalize = normalize;
    }

}
