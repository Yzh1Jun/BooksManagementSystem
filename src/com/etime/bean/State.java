package com.etime.bean;

public class State {
    private int state;

    @Override
    public String toString() {
        return "State{" +
                "state=" + state +
                '}';
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public State() {
    }

    public State(int state) {
        this.state = state;
    }
}
