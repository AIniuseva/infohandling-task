package com.epam.infohandling;

import com.epam.infohandling.model.Composite;
import com.epam.infohandling.model.Leaf;
import com.epam.infohandling.parsing.*;
import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TextProcessorTest {

    private final String TEST_FILE_PATH = "src/test/resources/textSample.txt";
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


    @Test
    public void testWhenTestIsGiven() {
        //given
        ChainBuilder chainBuilder = mock(ChainBuilder.class);
        TextParser textParser = new TextParser();
        ParagraphParser paragraphParser = new ParagraphParser();
        SentenceParser sentenceParser = new SentenceParser();
        //when
        textParser.setSuccessor(paragraphParser);
        paragraphParser.setSuccessor(sentenceParser);
        sentenceParser.parse(null);

        when(chainBuilder.build()).thenReturn(textParser);

        Composite actualComposite = textParser.parse(getTextForTest());
        Composite expectedComposite = getExpectedComposite();
        //then
        Assert.assertEquals(expectedComposite, actualComposite); //TODO: change
    }

    private String getTextForTest() {
        StringBuilder text = new StringBuilder();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(TEST_FILE_PATH))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                text.append(line).append("\n");
            }
        } catch (IOException e) {
            e.getCause();
        }

        return String.valueOf(text);
    }

    private Composite getExpectedComposite() {
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

    private Composite fillWithLeafs(String[] sentence) {
        Composite composite = new Composite();

        for (String word : sentence) {
            composite.add(new Leaf(word));
        }
        return composite;
    }
}
