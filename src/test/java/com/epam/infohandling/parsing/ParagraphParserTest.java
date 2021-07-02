package com.epam.infohandling.parsing;

import com.epam.infohandling.util.TestData;
import com.epam.infohandling.model.Composite;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ParagraphParserTest {

    private static final String TEST_PARAGRAPH = "Some text. Some text. Some text1.";
    private ParagraphParser paragraphParser;
    private TestData testData;

    @Before
    public void setUp() {
        paragraphParser = new ParagraphParser();
        testData = new TestData();
    }

    @Test
    public void testWhenTextIsGiven() {
        //when
        Composite actualComposite = paragraphParser.parse(TEST_PARAGRAPH);
        Composite expectedComposite = testData.getCompositeForParagraphTest();
        //then
        assertEquals(expectedComposite.getChild(0), actualComposite.getChild(0));
        assertEquals(expectedComposite.getChild(1), actualComposite.getChild(1));
        assertEquals(expectedComposite.getChild(2), actualComposite.getChild(2));
    }

    @Test
    public void testWhenNull() {
        //when
        Composite actualComposite = paragraphParser.parse(null);
        //then
        assertNull(actualComposite);
    }

    @Test
    public void testWhenEmpty() {
        //when
        Composite actualComposite = paragraphParser.parse("");
        //then
        assertNull(actualComposite);
    }
}
