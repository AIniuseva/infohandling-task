package com.epam.infohandling.logic;

import com.epam.infohandling.interpreter.ExpressionCalculator;
import com.epam.infohandling.model.Component;
import com.epam.infohandling.model.Composite;
import com.epam.infohandling.model.Leaf;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class TextLogic {

    private static final String EXPRESSION_PATTERN = "(\\[.*?\\])";
    private static final Logger LOGGER = LogManager.getLogger(TextLogic.class);
    private final ExpressionCalculator expressionCalculator;

    public TextLogic() {
        this.expressionCalculator = new ExpressionCalculator();
    }

    public TextLogic(ExpressionCalculator expressionCalculator) {
        this.expressionCalculator = expressionCalculator;
    }

    public Composite calculate(Composite text) {

        if (text == null || text.getComponents().isEmpty()) {
            LOGGER.error("Insufficient data to calculate the text.");
            return null;
        }

        Composite calculatedComposite = new Composite(text.getComponents());

        processCalculation(calculatedComposite);

        return calculatedComposite;
    }

    public String restore(Composite text) {

        if (text == null || text.getComponents().isEmpty()) {
            LOGGER.error("Insufficient data to restore the text.");
            return null;
        }

        return processRestoration(text, 0);
    }

    public int countSentencesWithEqualWords(Composite text) {

        if (text == null || text.getComponents().isEmpty()) {
            LOGGER.error("Insufficient data to count sentences with equal words.");
            return 0;
        }

        int count = 0;

        for (Component component : text.getComponents()) {
            if (component.getChild(0) instanceof Leaf) {
                count += findEqualWords((Composite) component);
            } else {
                count += countSentencesWithEqualWords((Composite) component);
            }
        }

        return count;
    }

    public void sortSentencesLexemeInDescending(Composite text) {

        if (text == null || text.getComponents().isEmpty()) {
            LOGGER.error("Insufficient data for sorting.");
            return;
        }

        for (Component component : text.getComponents()) {
            Collections.sort(((Composite) component).getComponents(), new Comparator<Component>() {
                @Override
                public int compare(Component o1, Component o2) {
                    if (((Composite) o1).getComponents().size() < ((Composite) o2).getComponents().size()) {
                        return 1;
                    } else if (((Composite) o1).getComponents().size() > ((Composite) o2).getComponents().size()) {
                        return -1;
                    }
                    return 0;
                }
            });
        }
    }

    public void sortInAlphabeticalOrder(Composite text) {

        if (text == null || text.getComponents().isEmpty()) {
            LOGGER.error("Insufficient data for sorting.");
            return;
        }

        for (Component component : text.getComponents()) {
            if (component.getChild(0) instanceof Leaf) {
                Collections.sort(((Composite) component).getComponents(), new Comparator<Component>() {
                    @Override
                    public int compare(Component o1, Component o2) {
                        return ((Leaf) o1).getValue().compareTo(((Leaf) o2).getValue());
                    }
                });
            } else {
                sortInAlphabeticalOrder((Composite) component);
            }
        }
    }

    public void sortByLetterCountInDescending(Composite text) {

        if (text == null || text.getComponents().isEmpty()) {
            LOGGER.error("Insufficient data for sorting.");
            return;
        }

        for (Component component : text.getComponents()) {
            if (component.getChild(0) instanceof Leaf) {
                Collections.sort(((Composite) component).getComponents(), new Comparator<Component>() {
                    @Override
                    public int compare(Component o1, Component o2) {
                        if (((Leaf) o1).getValue().length() < ((Leaf) o2).getValue().length()) {
                            return 1;
                        } else if (((Leaf) o1).getValue().length() > ((Leaf) o2).getValue().length()) {
                            return -1;
                        }
                        return 0;
                    }
                });
            } else {
                sortByLetterCountInDescending((Composite) component);
            }
        }
    }

    public void removeWordsWithGivenLength(Composite text, int length) {

        if (text == null || text.getComponents().isEmpty() || length == 0) {
            LOGGER.error("Insufficient data for words removing.");
            return;
        }

        for (Component component : text.getComponents()) {
            if (component.getChild(0) instanceof Leaf) {
                processRemovingByLength((Composite) component, length);
            } else {
                removeWordsWithGivenLength((Composite) component, length);
            }
        }
    }


    public void removeWordsWithGivenLetter(Composite text, char letter) {

        if (text == null || text.getComponents().isEmpty()) {
            LOGGER.error("Insufficient data for words removing.");
            return;
        }

        for (Component component : text.getComponents()) {
            if (component.getChild(0) instanceof Leaf) {
                processRemovingByLetter((Composite) component, letter);
            } else {
                removeWordsWithGivenLetter((Composite) component, letter);
            }
        }
    }

    public void reverseLexemes(Composite text) {

        if (text == null || text.getComponents().isEmpty()) {
            LOGGER.error("Insufficient data for reversing.");
            return;
        }

        for (Component component : text.getComponents()) {
            if (component.getChild(0) instanceof Leaf) {
                Collections.reverse(((Composite) component).getComponents());
            } else {
                reverseLexemes((Composite) component);
            }
        }
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

    private int findEqualWords(Composite composite) {
        if (composite.getChild(0) instanceof Composite) {
            return 0;
        }
        String saveValue;
        int shift = 0;
        for (int i = 0; i < composite.getComponents().size(); i++) {
            saveValue = ((Leaf) composite.getComponents().get(i)).getValue();
            shift++;
            for (int j = shift; j < composite.getComponents().size(); j++) {
                String word = ((Leaf) composite.getComponents().get(j)).getValue().replace(",", "")
                        .toLowerCase(Locale.ROOT);
                if (saveValue.equals(word)) {
                    return 1;
                }
            }
        }
        return 0;
    }

    private void processRemovingByLength(Composite text, int length) {
        List<Leaf> wordsToDelete = new ArrayList<>();

        for (Component word : text.getComponents()) {
            String stringWord = ((Leaf) word).getValue();
            if (stringWord.length() == length && !stringWord.matches(EXPRESSION_PATTERN)) {
                wordsToDelete.add((Leaf) word);
            }
        }

        for (Leaf leaf : wordsToDelete) {
            text.remove(leaf);
        }
    }

    private void processRemovingByLetter(Composite text, char letter) {
        List<Leaf> wordsToDelete = new ArrayList<>();

        for (Component word : text.getComponents()) {
            String stringWord = ((Leaf) word).getValue();
            if (String.valueOf(stringWord.charAt(0)).equalsIgnoreCase(String.valueOf(letter))) {
                wordsToDelete.add((Leaf) word);
            }
        }

        for (Leaf leaf : wordsToDelete) {
            text.remove(leaf);
        }
    }
}
