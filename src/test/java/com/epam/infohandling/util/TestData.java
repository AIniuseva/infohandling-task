package com.epam.infohandling.util;

import com.epam.infohandling.model.Composite;
import com.epam.infohandling.model.Leaf;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TestData {

    private final String[] FIRST_SENTENCE = new String[]{"It", "has", "survived", "-", "not", "only", "(five)", "centuries,",
            "but", "also", "the", "leap", "into", "[13 x +]", "electronic", "typesetting,", "remaining", "[3 5 +]", "essentially", "[3 4 - 9 * 6 +]", "unchanged"};
    private final String[] SECOND_SENTENCE = new String[]{"It", "was", "popularised", "in", "the", "[ 20 1 - ]", "with", "the", "release",
            "of", "Letraset", "sheets", "containing", "Lorem", "Ipsum", "passages,", "and", "more", "recently", "with", "desktop", "publishing",
            "software", "like", "Aldus", "PageMaker", "including", "versions", "of", "Lorem", "Ipsum"};
    private final String[] THIRD_SENTENCE = new String[]{"It", "is", "a", "long", "established", "fact", "that", "a", "reader", "will",
            "be", "distracted", "by", "the", "readable", "content", "of", "a", "page", "when", "looking", "at", "its", "layout"};
    private final String[] FOURTH_SENTENCE = new String[]{"The", "point", "of", "using", "[71 2 * 3 +]", "Ipsum", "is", "that", "it", "has",
            "a", "more-or-less", "normal", "distribution", "of", "letters,", "as", "opposed", "to", "using", "(Content", "here),", "content", "here',"
            , "making", "it", "look", "like", "readable", "English"};
    private final String[] FIFTH_SENTENCE = new String[]{"It", "is", "a", "[5 y + 120 *]", "established", "fact", "that", "a", "reader",
            "will", "be", "of", "a", "page", "when", "looking", "at", "its", "layout"};

    public Composite getCompositeForTextTest() {
        Composite composite = new Composite();
        composite.add(new Leaf("Some text. Some text."));
        composite.add(new Leaf("Some text. Some text. Some text."));
        return composite;
    }

    public Composite getCompositeForParagraphTest() {
        Composite composite = new Composite();
        composite.add(new Leaf("Some text"));
        composite.add(new Leaf("Some text"));
        composite.add(new Leaf("Some text1"));
        return composite;
    }

    public Composite getCompositeForSentenceTest() {
        Composite composite = new Composite();

        composite.add(new Leaf("Lorem"));
        composite.add(new Leaf("ipsum"));
        composite.add(new Leaf("[5 8 +]"));
        composite.add(new Leaf("dolor"));
        composite.add(new Leaf("sit"));
        composite.add(new Leaf("amet,"));
        composite.add(new Leaf("consectetur"));
        composite.add(new Leaf("adipiscing"));
        composite.add(new Leaf("elit"));

        return composite;
    }

    public Composite getCompositeTextTree() {
        Composite composite = new Composite();
        Composite firstParagraph = new Composite();
        Composite secondParagraph = new Composite();
        Composite thirdParagraph = new Composite();

        composite.add(firstParagraph);
        composite.add(secondParagraph);
        composite.add(thirdParagraph);

        Composite firstParFirstSentence = fillWithLeafs(FIRST_SENTENCE);
        Composite firstParSecondSentence = fillWithLeafs(SECOND_SENTENCE);

        firstParagraph.add(firstParFirstSentence);
        firstParagraph.add(firstParSecondSentence);

        Composite secondParFirstSentence = fillWithLeafs(THIRD_SENTENCE);
        Composite secondParSecondSentence = fillWithLeafs(FOURTH_SENTENCE);

        secondParagraph.add(secondParFirstSentence);
        secondParagraph.add(secondParSecondSentence);

        Composite thirdParFirstSentence = fillWithLeafs(FIFTH_SENTENCE);
        thirdParagraph.add(thirdParFirstSentence);

        return composite;
    }

    public Composite getSimpleComposite() {
        Composite composite = getCompositeTemplate();

        Composite firstParFirstSentence = fillWithLeafs(new String[]{"[13 x +]", "[3 5 +]", "lorem", "[8 9 + 2 *]"});
        Composite secondParFirstSentence = fillWithLeafs(new String[]{"Lorem", "[3 4 - 9 * 6 +]", "ipsum", "[ 20 1 - ]", "[6 2 /]", "word"});
        Composite thirdParFirstSentence = fillWithLeafs(new String[]{"[71 2 * 3 +]", "word", "[5 y + 120 *]", "word", "[80 2 / 3 +]", "lorem", "ipsum"});

        composite.getComponents().get(0).add(firstParFirstSentence);
        composite.getComponents().get(1).add(secondParFirstSentence);
        composite.getComponents().get(2).add(thirdParFirstSentence);

        return composite;
    }

    public Composite getCalculatedComposite() {
        Composite composite = getCompositeTemplate();

        Composite firstParFirstSentence = fillWithLeafs(new String[]{"15", "8", "lorem", "34"});
        Composite secondParFirstSentence = fillWithLeafs(new String[]{"Lorem", "-3", "ipsum", "19", "3", "word"});
        Composite thirdParFirstSentence = fillWithLeafs(new String[]{"145", "word", "1560", "word", "43", "lorem", "ipsum"});

        composite.getComponents().get(0).add(firstParFirstSentence);
        composite.getComponents().get(1).add(secondParFirstSentence);
        composite.getComponents().get(2).add(thirdParFirstSentence);

        return composite;
    }

    public Composite getReverseComposite() {
        Composite composite = getCompositeTemplate();

        Composite firstParSentence = fillWithLeafs(new String[]{"[8 9 + 2 *]", "lorem", "[3 5 +]", "[13 x +]"});
        Composite secondParSentence = fillWithLeafs(new String[]{"word", "[6 2 /]", "[ 20 1 - ]", "ipsum", "[3 4 - 9 * 6 +]", "Lorem"});
        Composite thirdParSentence = fillWithLeafs(new String[]{"ipsum", "lorem", "[80 2 / 3 +]", "word", "[5 y + 120 *]", "word", "[71 2 * 3 +]"});

        composite.getComponents().get(0).add(firstParSentence);
        composite.getComponents().get(1).add(secondParSentence);
        composite.getComponents().get(2).add(thirdParSentence);

        return composite;
    }


    public Composite getCompositeWithRemovedWords() {
        Composite composite = getCompositeTemplate();

        Composite firstParSentence = fillWithLeafs(new String[]{"[13 x +]", "[3 5 +]", "lorem", "[8 9 + 2 *]"});
        Composite secondParSentence = fillWithLeafs(new String[]{"Lorem", "[3 4 - 9 * 6 +]", "ipsum", "[ 20 1 - ]", "[6 2 /]"});
        Composite thirdParSentence = fillWithLeafs(new String[]{"[71 2 * 3 +]", "[5 y + 120 *]", "[80 2 / 3 +]", "lorem", "ipsum"});

        composite.getComponents().get(0).add(firstParSentence);
        composite.getComponents().get(1).add(secondParSentence);
        composite.getComponents().get(2).add(thirdParSentence);

        return composite;

    }

    public Composite getCompositeWithWordsOnly() {
        Composite composite = getCompositeTemplate();

        Composite firstParFirstSentence = fillWithLeafs(new String[]{"Lorem", "ipsum", "dolor", "sit", "amet,", "consectetur", "adipiscing", "elit"});
        Composite firstParSecondSentence = fillWithLeafs(new String[]{"Mauris", "ac", "risus", "mattis", "nulla", "pharetra", "rhoncus"});

        Composite secondParFirstSentence = fillWithLeafs(new String[]{"Donec", "sollicitudin", "neque", "in", "lectus", "lobortis", "luctus"});
        Composite secondParSecondSentence = fillWithLeafs(new String[]{"Nulla", "eget", "mauris", "quis", "velit", "ullamcorper", "fermentum", "id"});

        Composite thirdParFirstSentence = fillWithLeafs(new String[]{"Mauris", "aliquam", "dictum", "leo"});
        Composite thirdParSecondSentence = fillWithLeafs(new String[]{"Duis", "faucibus", "pulvinar", "orci,", "ac", "tempor", "ex", "finibus", "id"});

        composite.getComponents().get(0).add(firstParFirstSentence);
        composite.getComponents().get(0).add(firstParSecondSentence);
        composite.getComponents().get(1).add(secondParFirstSentence);
        composite.getComponents().get(1).add(secondParSecondSentence);
        composite.getComponents().get(2).add(thirdParFirstSentence);
        composite.getComponents().get(2).add(thirdParSecondSentence);

        return composite;
    }

    public Composite getAlphabeticallySortedComposite() {
        Composite composite = getCompositeTemplate();

        Composite firstParFirstSentence = fillWithLeafs(new String[]{"Lorem", "adipiscing", "amet,", "consectetur", "dolor", "elit", "ipsum", "sit"});
        Composite firstParSecondSentence = fillWithLeafs(new String[]{"Mauris", "ac", "mattis", "nulla", "pharetra", "rhoncus", "risus"});

        Composite secondParFirstSentence = fillWithLeafs(new String[]{"Donec", "in", "lectus", "lobortis", "luctus", "neque", "sollicitudin"});
        Composite secondParSecondSentence = fillWithLeafs(new String[]{"Nulla", "eget", "fermentum", "id", "mauris", "quis", "ullamcorper", "velit"});

        Composite thirdParFirstSentence = fillWithLeafs(new String[]{"Mauris", "aliquam", "dictum", "leo"});
        Composite thirdParSecondSentence = fillWithLeafs(new String[]{"Duis", "ac", "ex", "faucibus", "finibus", "id", "orci,", "pulvinar", "tempor"});

        composite.getComponents().get(0).add(firstParFirstSentence);
        composite.getComponents().get(0).add(firstParSecondSentence);
        composite.getComponents().get(1).add(secondParFirstSentence);
        composite.getComponents().get(1).add(secondParSecondSentence);
        composite.getComponents().get(2).add(thirdParFirstSentence);
        composite.getComponents().get(2).add(thirdParSecondSentence);

        return composite;
    }

    public Composite getSortedSentencesInDescending() {
        Composite composite = getCompositeTemplate();

        Composite firstParFirstSentence = fillWithLeafs(new String[]{"Lorem", "ipsum", "dolor", "sit", "amet,", "consectetur", "adipiscing", "elit"});
        Composite firstParSecondSentence = fillWithLeafs(new String[]{"Mauris", "ac", "risus", "mattis", "nulla", "pharetra", "rhoncus"});

        Composite secondParFirstSentence = fillWithLeafs(new String[]{"Nulla", "eget", "mauris", "quis", "velit", "ullamcorper", "fermentum", "id"});
        Composite secondParSecondSentence = fillWithLeafs(new String[]{"Donec", "sollicitudin", "neque", "in", "lectus", "lobortis", "luctus"});

        Composite thirdParFirstSentence = fillWithLeafs(new String[]{"Duis", "faucibus", "pulvinar", "orci,", "ac", "tempor", "ex", "finibus", "id"});
        Composite thirdParSecondSentence = fillWithLeafs(new String[]{"Mauris", "aliquam", "dictum", "leo"});

        composite.getComponents().get(0).add(firstParFirstSentence);
        composite.getComponents().get(0).add(firstParSecondSentence);
        composite.getComponents().get(1).add(secondParFirstSentence);
        composite.getComponents().get(1).add(secondParSecondSentence);
        composite.getComponents().get(2).add(thirdParFirstSentence);
        composite.getComponents().get(2).add(thirdParSecondSentence);

        return composite;
    }

    public Composite getSortedByLetterCountInDescending() {
        Composite composite = getCompositeTemplate();

        Composite firstParFirstSentence = fillWithLeafs(new String[]{"consectetur", "adipiscing", "Lorem", "ipsum", "dolor", "amet,", "elit", "sit"});
        Composite firstParSecondSentence = fillWithLeafs(new String[]{"pharetra", "rhoncus", "Mauris", "mattis", "risus", "nulla", "ac"});

        Composite secondParFirstSentence = fillWithLeafs(new String[]{"sollicitudin", "lobortis", "lectus", "luctus", "Donec", "neque", "in"});
        Composite secondParSecondSentence = fillWithLeafs(new String[]{"ullamcorper", "fermentum", "mauris", "Nulla", "velit", "eget", "quis", "id"});

        Composite thirdParFirstSentence = fillWithLeafs(new String[]{"aliquam", "Mauris", "dictum", "leo"});
        Composite thirdParSecondSentence = fillWithLeafs(new String[]{"faucibus", "pulvinar", "finibus", "tempor", "orci,", "Duis", "ac", "ex", "id"});


        composite.getComponents().get(0).add(firstParFirstSentence);
        composite.getComponents().get(0).add(firstParSecondSentence);
        composite.getComponents().get(1).add(secondParFirstSentence);
        composite.getComponents().get(1).add(secondParSecondSentence);
        composite.getComponents().get(2).add(thirdParFirstSentence);
        composite.getComponents().get(2).add(thirdParSecondSentence);

        return composite;
    }

    public String getTextForTest(String path) {
        StringBuilder text = new StringBuilder();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                text.append(line).append("\n");
            }
        } catch (IOException e) {
            e.getCause();
        }

        return String.valueOf(text);
    }

    public Map<String, Integer> getVariables() {
        Map<String, Integer> variables = new HashMap<>();
        variables.put("x", 2);
        variables.put("y", 8);
        return variables;
    }

    private Composite getCompositeTemplate() {
        Composite composite = new Composite();
        Composite firstParagraph = new Composite();
        Composite secondParagraph = new Composite();
        Composite thirdParagraph = new Composite();

        composite.add(firstParagraph);
        composite.add(secondParagraph);
        composite.add(thirdParagraph);
        return composite;
    }

    private Composite fillWithLeafs(String[] sentence) {
        Composite composite = new Composite();

        for (String word : sentence) {
            composite.add(new Leaf(word));
        }
        return composite;
    }
}
