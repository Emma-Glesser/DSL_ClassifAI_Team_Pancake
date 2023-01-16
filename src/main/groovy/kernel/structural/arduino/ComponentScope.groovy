package kernel.structural.arduino

class ComponentScope {
    private List<Component> components

    private static final ComponentScope instance = new ComponentScope()

    static ComponentScope getInstance() {
        return instance
    }

    ComponentScope() {
        this.components = new ArrayList<>()
    }

    Sensor sensor(String name) {
        Sensor sensor = new Sensor(name)
        this.components.add(sensor)
        return sensor
    }

    Actuator actuator(String name) {
        Actuator actuator = new Actuator(name)
        this.components.add(actuator)
        return actuator
    }

    List<Component> getComponents() {
        return components
    }
}
