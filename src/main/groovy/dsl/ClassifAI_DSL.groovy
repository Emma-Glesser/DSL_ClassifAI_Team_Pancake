package dsl

import groovy.transform.TypeChecked
import kernel.structural.Program
import kernel.structural.ProgramBeing
import org.codehaus.groovy.control.CompilerConfiguration
import org.codehaus.groovy.control.customizers.SecureASTCustomizer

class ClassifAI_DSL {
    private GroovyShell shell
    private CompilerConfiguration configuration
    private ClassifAI_DSL_Binding binding

    ClassifAI_DSL() {
        binding = ClassifAI_DSL_Binding.instance
        binding.setClassifAI_DSL_Model(new ClassifAI_DSL_Model())
        configuration = getDSLConfiguration()
        configuration.setScriptBaseClass("dsl.ClassifAI_DSL_Base_Script")
        shell = new GroovyShell(configuration)
    }

    private static CompilerConfiguration getDSLConfiguration() {
        def secure = new SecureASTCustomizer()
        secure.with {
            //disallow closure creation
            closuresAllowed = true
            //disallow method definitions
            methodDefinitionAllowed = true
            //empty white list => forbid imports
            importsWhitelist = []
            staticImportsWhitelist = ['dsl.ClassifAI_DSL']
            staticStarImportsWhitelist= []
            //language tokens disallowed
//			tokensBlacklist= []
            //language tokens allowed
            tokensWhitelist= []
            //types allowed to be used  (including primitive types)
            constantTypesClassesWhiteList= [
                    int, Integer, Number, String, boolean, Object, SIGNAL
            ]
            //classes who are allowed to be receivers of method calls
            receiversClassesWhiteList= [
                    int, Integer, Number, String, boolean, Object, SIGNAL
            ]
        }

        def configuration = new CompilerConfiguration()
        configuration.addCompilationCustomizers(secure)

        return configuration
    }

    static ProgramBeing program(String _) {
        // Note: this is a static method to import in scripts
        return null
    }

    static enum SIGNAL { HIGH, LOW }

    @TypeChecked
    void eval(File scriptFile) {
        Script script = shell.parse(scriptFile)

        binding.setScript(script)
        script.setBinding(binding)

        script.run()
    }
}
