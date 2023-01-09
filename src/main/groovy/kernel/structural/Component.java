package kernel.structural;

import dsl.ClassifAI_DSL_Binding;
import kernel.NamedElement;
import kernel.generator.Visitable;

public abstract class Component implements NamedElement, Visitable {

	private String name;
	private int pin;

    Component(String name) {
        this.name = name;
        ClassifAI_DSL_Binding.getInstance().setVariable(name, this);
    }
	public int getPin() {
		return pin;
	}

	public void pin(int pin) {
		this.pin = pin;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

}