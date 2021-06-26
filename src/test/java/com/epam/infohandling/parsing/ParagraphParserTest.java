package com.epam.infohandling.parsing;

import com.epam.infohandling.model.Composite;
import com.epam.infohandling.model.Leaf;
import org.junit.Assert;
import org.junit.Test;

public class ParagraphParserTest {

    private final String TEST_PARAGRAPH = "Some text. Some text. Some text1.";

    @Test
    public void testWhenTextIsGiven() {
        //given
        ParagraphParser paragraphParser = new ParagraphParser();
        //when
        Composite actualComposite = paragraphParser.parse(TEST_PARAGRAPH);
        Composite expectedComposite = getCompositeForTest();
        //then
        Assert.assertEquals(expectedComposite.getChild(0), actualComposite.getChild(0));
        Assert.assertEquals(expectedComposite.getChild(1), actualComposite.getChild(1));
        Assert.assertEquals(expectedComposite.getChild(2), actualComposite.getChild(2));
    }

    @Test
    public void testWhenNull() {
        //given
        ParagraphParser paragraphParser = new ParagraphParser();
        //when
        Composite actualComposite = paragraphParser.parse(null);
        //then
        Assert.assertNull(actualComposite);
    }

    @Test
    public void testWhenEmpty() {
        //given
        ParagraphParser paragraphParser = new ParagraphParser();
        //when
        Composite actualComposite = paragraphParser.parse("");
        //then
        Assert.assertNull(actualComposite);
    }

    public Composite getCompositeForTest() {
        Composite composite = new Composite();
        composite.add(new Leaf("Some text"));
        composite.add(new Leaf("Some text"));
        composite.add(new Leaf("Some text1"));
        return composite;
    }
}
