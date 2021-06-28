package com.epam.infohandling.parsing;

import com.epam.infohandling.TestData;
import com.epam.infohandling.model.Composite;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TextParserTest {

    private final String TEST_TEXT = "Some text. Some text.\nSome text. Some text. Some text.";
    private TestData testData;

    @Before
    public void setUp() {
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
        Assert.assertEquals(expectedComposite.getChild(0), actualComposite.getChild(0));
        Assert.assertEquals(expectedComposite.getChild(1), actualComposite.getChild(1));
    }

    @Test
    public void testWhenNull() {
        //given
        TextParser textParser = new TextParser();
        //when
        Composite actualComposite = textParser.parse(null);
        //then
        Assert.assertNull(actualComposite);
    }

    @Test
    public void testWhenEmpty() {
        //given
        TextParser textParser = new TextParser();
        //when
        Composite actualComposite = textParser.parse("");
        //then
        Assert.assertNull(actualComposite);
    }
}
