package kernel.structural.arduino

import kernel.behavioral.State

class StateScope {
    private List<State> states

    private static final StateScope instance = new StateScope()

    static StateScope getInstance() {
        return instance
    }

    StateScope() {
        this.states = new ArrayList<>()
    }

    State state(String name) {
        State state = new State(name)
        state.setName(name)
        this.states.add(state)
        return state
    }

    List<State> getStates() {
        return states
    }
}
