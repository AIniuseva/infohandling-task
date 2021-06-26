package com.epam.infohandling.parsing;

import com.epam.infohandling.model.Composite;
import com.epam.infohandling.model.Leaf;
import org.junit.Assert;
import org.junit.Test;

public class SentenceParserTest {

    private final String TEST_SENTENCE = "Lorem ipsum [5 8 +] dolor sit amet, consectetur adipiscing elit";

    @Test
    public void testWhenTextIsGiven() {
        //given
        SentenceParser sentenceParser = new SentenceParser();
        //when
        Composite actualComposite = sentenceParser.parse(TEST_SENTENCE);
        Composite expectedComposite = getCompositeForTest();
        //then
        Assert.assertEquals(expectedComposite.getChild(0), actualComposite.getChild(0));
        Assert.assertEquals(expectedComposite.getChild(1), actualComposite.getChild(1));
        Assert.assertEquals(expectedComposite.getChild(2), actualComposite.getChild(2));
        Assert.assertEquals(expectedComposite.getChild(3), actualComposite.getChild(3));
        Assert.assertEquals(expectedComposite.getChild(4), actualComposite.getChild(4));
        Assert.assertEquals(expectedComposite.getChild(5), actualComposite.getChild(5));
        Assert.assertEquals(expectedComposite.getChild(6), actualComposite.getChild(6));
        Assert.assertEquals(expectedComposite.getChild(7), actualComposite.getChild(7));
        Assert.assertEquals(expectedComposite.getChild(8), actualComposite.getChild(8));
    }


    @Test
    public void testWhenNull() {
        //given
        SentenceParser sentenceParser = new SentenceParser();
        //when
        Composite actualComposite = sentenceParser.parse(null);
        //then
        Assert.assertNull(actualComposite);
    }

    @Test
    public void testWhenEmpty() {
        //given
        SentenceParser sentenceParser = new SentenceParser();
        //when
        Composite actualComposite = sentenceParser.parse("");
        //then
        Assert.assertNull(actualComposite);
    }


    private Composite getCompositeForTest() {
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
}
