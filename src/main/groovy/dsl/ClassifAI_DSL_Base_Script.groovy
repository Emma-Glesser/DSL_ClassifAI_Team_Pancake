package dsl

import kernel.structural.Program

abstract class ClassifAI_DSL_Base_Script extends Script {

    Program program(String name) {
        Program program = new Program()
        program.name = name
        ((ClassifAI_DSL_Binding)this.getBinding()).getClassifAI_DSLModel().setProgram(program)
        return program
    }

	// disable run method while running
	int count = 0
	abstract void scriptBody()
	def run() {
        if(count == 0) {
			count++
			scriptBody()
            String name = ((ClassifAI_DSL_Binding) this.getBinding()).getClassifAI_DSLModel().getProgram().getName()
            String result = ((ClassifAI_DSL_Binding) this.getBinding()).getClassifAI_DSLModel().generateCode().toString()
            File resultFile = new File("./results/" + name + ".ipynb")
            resultFile.delete()
            resultFile.createNewFile()
            resultFile << result
		} else {
			println "Run method is disabled"
		}
	}
}
