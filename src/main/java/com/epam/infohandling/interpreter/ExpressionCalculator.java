package com.epam.infohandling.interpreter;

import java.util.ArrayList;
import java.util.List;

public class ExpressionCalculator {

    public int calculate(String expression) {
        if (expression == null || "".equals(expression)) {
            return 0;
        }
        Context context = new Context();
        List<Expression> expressions = parse(expression);

        for (Expression terminal : expressions) {
            terminal.interpret(context);
        }
        return context.popValue();
    }

    private List<Expression> parse(String expression) {
        List<Expression> expressions = new ArrayList<>();
        expression = expression.replaceAll("[\\[\\]]", "");
        expression = expression.replaceAll("[\\–]", "-");

        for (String lexeme : expression.split("\\s+")) {
            if (lexeme.isEmpty()) {
                continue;
            }

            switch (lexeme) {
                case "+":
                    expressions.add(new TerminalPlusExpression());
                    break;
                case "-":
                    expressions.add(new TerminalMinusExpression());
                    break;
                case "*":
                    expressions.add(new TerminalMultiplyExpression());
                    break;
                case "/":
                    expressions.add(new TerminalDivideExpression());
                    break;
                default:
                    if (lexeme.matches("\\d+")) {
                        expressions.add(new NonterminalExpression(Integer.parseInt(lexeme)));
                    }
                    break;
            }
        }

        return expressions;
    }
}
