package dsl;

import java.util.Map;

import groovy.lang.Binding;
import groovy.lang.Script;
import kernel.structural.Invalid_DSL_SyntaxeException;

public class ClassifAI_DSL_Binding extends Binding {
	// can be useful to return the script in case of syntax trick
	private Script script;
	
	private ClassifAI_DSL_Model model;

    private static final ClassifAI_DSL_Binding instance = new ClassifAI_DSL_Binding();
    public static ClassifAI_DSL_Binding getInstance() {
        return instance;
    }

	private ClassifAI_DSL_Binding() {
		super();
	}
	
	public ClassifAI_DSL_Binding(Map variables) {
		super(variables);
	}
	
	public ClassifAI_DSL_Binding(Script script) {
		super();
		this.script = script;
	}
	
	public void setScript(Script script) {
		this.script = script;
	}
	
	public void setClassifAI_DSL_Model(ClassifAI_DSL_Model model) {
		this.model = model;
	}
	
	public Object getVariable(String name) {
		return super.getVariable(name);
	}
	
	public void setVariable(String name, Object value) {
        try {
            this.getVariable(name);
        } catch (Exception e) {
            super.setVariable(name, value);
            return;
        }
        throw new Invalid_DSL_SyntaxeException("Already existing identifiers : " + name);
    }
	
	public ClassifAI_DSL_Model getClassifAI_DSLModel() {
		return this.model;
	}
}
