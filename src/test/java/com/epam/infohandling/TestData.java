package com.epam.infohandling;

import com.epam.infohandling.logic.TextLogic;
import com.epam.infohandling.model.Composite;
import com.epam.infohandling.model.Leaf;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TestData {

    private final String[] FIRST_SENTENCE = new String[]{"It", "has", "survived", "-", "not", "only", "(five)", "centuries,",
            "but", "also", "the", "leap", "into", "[13 2 +]", "electronic", "typesetting,", "remaining", "[3 5 +]", "essentially", "[3 4 – 9 * 6 +]", "unchanged"};
    private final String[] SECOND_SENTENCE = new String[]{"It", "was", "popularised", "in", "the", "[ 20 1 - ]", "with", "the", "release",
            "of", "Letraset", "sheets", "containing", "Lorem", "Ipsum", "passages,", "and", "more", "recently", "with", "desktop", "publishing",
            "software", "like", "Aldus", "PageMaker", "including", "versions", "of", "Lorem", "Ipsum"};
    private final String[] THIRD_SENTENCE = new String[]{"It", "is", "a", "long", "established", "fact", "that", "a", "reader", "will",
            "be", "distracted", "by", "the", "readable", "content", "of", "a", "page", "when", "looking", "at", "its", "layout"};
    private final String[] FOURTH_SENTENCE = new String[]{"The", "point", "of", "using", "[71 2 * 3 +]", "Ipsum", "is", "that", "it", "has",
            "a", "more-or-less", "normal", "distribution", "of", "letters,", "as", "opposed", "to", "using", "(Content", "here),", "content", "here',"
            , "making", "it", "look", "like", "readable", "English"};
    private final String[] FIFTH_SENTENCE = new String[]{"It", "is", "a", "[5 8 + 120 *]", "established", "fact", "that", "a", "reader",
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

    public Composite getCalculatedCompositeTextTree() { //TODO: change
        Composite composite = new Composite();
        Composite firstParagraph = new Composite();
        Composite secondParagraph = new Composite();
        Composite thirdParagraph = new Composite();

        composite.add(firstParagraph);
        composite.add(secondParagraph);
        composite.add(thirdParagraph);

        String[] firstCalculatedSentence = FIRST_SENTENCE;
        firstCalculatedSentence[13] = "15";
        firstCalculatedSentence[17] = "8";
        firstCalculatedSentence[19] = "-3";
        Composite firstParFirstSentence = fillWithLeafs(firstCalculatedSentence);
        String[] secondCalculatedSentence = SECOND_SENTENCE;
        secondCalculatedSentence[5] = "19";
        Composite firstParSecondSentence = fillWithLeafs(secondCalculatedSentence);

        firstParagraph.add(firstParFirstSentence);
        firstParagraph.add(firstParSecondSentence);

        Composite secondParFirstSentence = fillWithLeafs(THIRD_SENTENCE);
        String[] fourthCalculatedSentence = FOURTH_SENTENCE;
        fourthCalculatedSentence[4] = "145";
        Composite secondParSecondSentence = fillWithLeafs(fourthCalculatedSentence);

        secondParagraph.add(secondParFirstSentence);
        secondParagraph.add(secondParSecondSentence);

        String[] fifthCalculatedSentence = FIFTH_SENTENCE;
        fifthCalculatedSentence[3] = "1560";
        Composite thirdParFirstSentence = fillWithLeafs(fifthCalculatedSentence);
        thirdParagraph.add(thirdParFirstSentence);

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

    private Composite fillWithLeafs(String[] sentence) {
        Composite composite = new Composite();

        for (String word : sentence) {
            composite.add(new Leaf(word));
        }
        return composite;
    }
}
