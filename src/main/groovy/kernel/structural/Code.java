package kernel.structural;

import kernel.generator.Visitable;

public abstract class Code implements Visitable {

    private String comment;

    Code(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void comment (String comment) {
        this.comment = comment;
    }
}
