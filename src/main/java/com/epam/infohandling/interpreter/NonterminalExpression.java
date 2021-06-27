package com.epam.infohandling.interpreter;

public class NonterminalExpression implements Expression {

    private final int number;

    public NonterminalExpression(int number) {
        this.number = number;
    }

    @Override
    public void interpret(Context context) {
        context.pushValue(number);
    }
}