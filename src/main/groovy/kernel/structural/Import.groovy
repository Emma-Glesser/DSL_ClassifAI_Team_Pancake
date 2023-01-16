package kernel.structural

import kernel.generator.Visitor

class Import extends Code {

    Import(String comment) {
        super(comment)
    }

    @Override
    void accept(Visitor<StringBuffer> visitor) {
        visitor.visit(this)
    }

    def import_librairies(@DelegatesTo(strategy = Closure.DELEGATE_ONLY, value = ImportLibrary) Closure cl) {

    }
}
