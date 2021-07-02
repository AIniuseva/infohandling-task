package com.epam.infohandling.parsing;

import com.epam.infohandling.util.TestData;
import com.epam.infohandling.model.Composite;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class SentenceParserTest {

    private static final String TEST_SENTENCE = "Lorem ipsum [5 8 +] dolor sit amet, consectetur adipiscing elit";
    private SentenceParser sentenceParser;
    private TestData testData;

    @Before
    public void setUp() {
        sentenceParser = new SentenceParser();
        testData = new TestData();
    }

    @Test
    public void testWhenTextIsGiven() {
        //when
        Composite actualComposite = sentenceParser.parse(TEST_SENTENCE);
        Composite expectedComposite = testData.getCompositeForSentenceTest();
        //then
        for (int i = 0; i <= 8; i++) {
            assertEquals(expectedComposite.getChild(i), actualComposite.getChild(i));
        }
    }

    @Test
    public void testWhenNull() {
        //when
        Composite actualComposite = sentenceParser.parse(null);
        //then
        assertNull(actualComposite);
    }

    @Test
    public void testWhenEmpty() {
        //when
        Composite actualComposite = sentenceParser.parse("");
        //then
        assertNull(actualComposite);
    }
}
