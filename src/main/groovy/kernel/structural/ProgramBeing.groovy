package kernel.structural

class ProgramBeing {
    def being (@DelegatesTo(strategy=Closure.DELEGATE_ONLY, value=Program) Closure cl) {
        Closure code = cl.rehydrate(this, this, this)
        code.resolveStrategy = Closure.DELEGATE_ONLY
        code()
    }
}
