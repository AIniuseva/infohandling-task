package com.epam.infohandling.interpreter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExpressionCalculator {

    public int calculate(String expression, Map<String, Integer> variables) {
        if (expression == null || "".equals(expression)) {
            return 0;
        }
        Context context = new Context();
        List<Expression> expressions = parse(expression, variables);

        for (Expression terminal : expressions) {
            terminal.interpret(context);
        }
        return context.popValue();
    }

    private List<Expression> parse(String expression, Map<String, Integer> variables) {
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
                    } else if (lexeme.matches("[a-z]")) {
                        expressions.add(new NonterminalExpression(variables.get(lexeme)));
                    }
                    break;
            }
        }

        return expressions;
    }
}
