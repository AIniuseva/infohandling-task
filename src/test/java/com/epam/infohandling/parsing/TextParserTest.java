package com.epam.infohandling.parsing;

import com.epam.infohandling.model.Composite;
import com.epam.infohandling.model.Leaf;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TextParserTest {

    private final String TEST_TEXT = "Some text. Some text.\nSome text. Some text. Some text.";

    @Test
    public void testWhenTextIsGiven() {
        //given
        TextParser textParser = new TextParser();
        //when
        Composite actualComposite = textParser.parse(TEST_TEXT);
        Composite expectedComposite = getCompositeForTest();
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

    private Composite getCompositeForTest() {
        Composite composite = new Composite();
        composite.add(new Leaf("Some text. Some text."));
        composite.add(new Leaf("Some text. Some text. Some text."));
        return composite;
    }
}
