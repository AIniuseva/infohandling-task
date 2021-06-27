package com.epam.infohandling.interpreter;

public class TerminalMinusExpression implements Expression {

    @Override
    public void interpret(Context context) {
        int saveInt = context.popValue();
        context.pushValue(context.popValue() - saveInt);
    }
}
