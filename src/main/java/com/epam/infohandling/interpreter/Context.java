package com.epam.infohandling.interpreter;

import java.util.ArrayDeque;
import java.util.Deque;

public class Context {

    private final Deque<Integer> contextValue = new ArrayDeque<>();

    public Integer popValue() {
        return contextValue.pop();
    }

    public void pushValue(Integer value) {
        this.contextValue.push(value);
    }
}