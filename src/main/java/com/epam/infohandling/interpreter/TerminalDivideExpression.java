package com.epam.infohandling.interpreter;

public class TerminalDivideExpression implements Expression {

    @Override
    public void interpret(Context context) {
        int saveInt = context.popValue();
        context.pushValue(context.popValue() / saveInt);
    }
}
