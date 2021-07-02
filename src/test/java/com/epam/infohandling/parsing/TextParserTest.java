package com.epam.infohandling.parsing;

import com.epam.infohandling.util.TestData;
import com.epam.infohandling.model.Composite;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TextParserTest {

    private static final String TEST_TEXT = "Some text. Some text.\nSome text. Some text. Some text.";
    private TextParser textParser;
    private TestData testData;

    @Before
    public void setUp() {
        textParser = new TextParser();
        testData = new TestData();
    }

    @Test
    public void testWhenTextIsGiven() {
        //given
        TextParser textParser = new TextParser();
        //when
        Composite actualComposite = textParser.parse(TEST_TEXT);
        Composite expectedComposite = testData.getCompositeForTextTest();
        //then
        assertEquals(expectedComposite.getChild(0), actualComposite.getChild(0));
        assertEquals(expectedComposite.getChild(1), actualComposite.getChild(1));
    }

    @Test
    public void testWhenTextIsNull() {
        //when
        Composite actualComposite = textParser.parse(null);
        //then
        assertNull(actualComposite);
    }

    @Test
    public void testWhenTextIsEmpty() {
        //when
        Composite actualComposite = textParser.parse("");
        //then
        assertNull(actualComposite);
    }
}
