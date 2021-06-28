package com.epam.infohandling.logic;

import com.epam.infohandling.interpreter.ExpressionCalculator;
import com.epam.infohandling.model.Component;
import com.epam.infohandling.model.Composite;
import com.epam.infohandling.model.Leaf;

public class TextLogic {

    private final ExpressionCalculator expressionCalculator;
    private final String EXPRESSION_PATTERN = "(\\[.*?\\])";

    public TextLogic() {
        this.expressionCalculator = new ExpressionCalculator();
    }

    public TextLogic(ExpressionCalculator expressionCalculator) {
        this.expressionCalculator = expressionCalculator;
    }

    public Composite calculate(Composite text) {
        Composite calculatedComposite = new Composite(text.getComponents());

        processCalculation(calculatedComposite);

        return calculatedComposite;
    }

    public String restore(Composite text) {
        return processRestoration(text, 0);
    }

    private String processRestoration(Composite text, int depth) {
        StringBuilder restoredText = new StringBuilder();

        for (Component component : text.getComponents()) {
            if (component instanceof Composite) {
                restoredText.append(processRestoration((Composite) component, depth + 1));
                if (depth == 0) {
                    restoredText.replace(restoredText.length() - 1, restoredText.length(), "\n");
                } else if (depth == 1) {
                    restoredText.replace(restoredText.length() - 1, restoredText.length(), ". ");
                }
            } else if (component instanceof Leaf) {
                String word = ((Leaf) component).getValue();
                restoredText.append(word).append(" ");
            }
        }

        return String.valueOf(restoredText);
    }

    private void processCalculation(Composite composite) {
        for (Component component : composite.getComponents()) {
            if (component instanceof Composite) {
                processCalculation((Composite) component);
            } else if (component instanceof Leaf && ((Leaf) component).getValue().matches(EXPRESSION_PATTERN)) {
                int calculatedValue = expressionCalculator.calculate(((Leaf) component).getValue());
                ((Leaf) component).setValue(String.valueOf(calculatedValue));
            }
        }
    }
}
