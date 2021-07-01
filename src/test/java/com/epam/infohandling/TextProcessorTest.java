package com.epam.infohandling;

import com.epam.infohandling.model.Composite;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TextProcessorTest {

    private final String TEST_FILE_PATH = "src/test/resources/textSample.txt";
    private TestData testData;

    @Before
    public void setUp() {
        testData = new TestData();
    }

    @Test
    public void testWhenTextIsGiven() {
        //given
        TextProcessor textProcessor = new TextProcessor();
        //when
        Composite actualComposite = textProcessor.parseText(testData.getTextForTest(TEST_FILE_PATH));
        Composite expectedComposite = testData.getCompositeTextTree();
        //then
        //Assert.assertEquals(expectedComposite, actualComposite); //TODO: change?
        Assert.assertEquals(3, actualComposite.getComponents().size());
        Assert.assertEquals(2, ((Composite) actualComposite.getChild(0)).getComponents().size());
        Assert.assertEquals(2, ((Composite) actualComposite.getChild(1)).getComponents().size());
        Assert.assertEquals(1, ((Composite) actualComposite.getChild(2)).getComponents().size());

        Assert.assertEquals(((Composite) expectedComposite.getChild(0).getChild(0)).getComponents()
                , ((Composite) actualComposite.getChild(0).getChild(0)).getComponents());
        Assert.assertEquals(((Composite) expectedComposite.getChild(0).getChild(1)).getComponents()
                , ((Composite) actualComposite.getChild(0).getChild(1)).getComponents());
        Assert.assertEquals(((Composite) expectedComposite.getChild(1).getChild(0)).getComponents()
                , ((Composite) actualComposite.getChild(1).getChild(0)).getComponents());
        Assert.assertEquals(((Composite) expectedComposite.getChild(1).getChild(1)).getComponents()
                , ((Composite) actualComposite.getChild(1).getChild(1)).getComponents());
        Assert.assertEquals(((Composite) expectedComposite.getChild(2).getChild(0)).getComponents()
                , ((Composite) actualComposite.getChild(2).getChild(0)).getComponents());
    }
}
