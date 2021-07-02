package com.epam.infohandling;

import com.epam.infohandling.model.Composite;
import com.epam.infohandling.util.TestData;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TextProcessorTest {

    private static final String TEST_FILE_PATH = "src/test/resources/textSample.txt";
    private TextProcessor textProcessor;
    private TestData testData;

    @Before
    public void setUp() {
        textProcessor = new TextProcessor();
        testData = new TestData();
    }

    @Test
    public void testWhenTextIsGiven() {
        //when
        Composite actualComposite = textProcessor.parseText(testData.getTextForTest(TEST_FILE_PATH));
        Composite expectedComposite = testData.getCompositeTextTree();
        //then
        assertEquals(3, actualComposite.getComponents().size());
        assertEquals(2, ((Composite) actualComposite.getChild(0)).getComponents().size());
        assertEquals(2, ((Composite) actualComposite.getChild(1)).getComponents().size());
        assertEquals(1, ((Composite) actualComposite.getChild(2)).getComponents().size());

        assertEquals(((Composite) expectedComposite.getChild(0).getChild(0)).getComponents()
                , ((Composite) actualComposite.getChild(0).getChild(0)).getComponents());
        assertEquals(((Composite) expectedComposite.getChild(0).getChild(1)).getComponents()
                , ((Composite) actualComposite.getChild(0).getChild(1)).getComponents());
        assertEquals(((Composite) expectedComposite.getChild(1).getChild(0)).getComponents()
                , ((Composite) actualComposite.getChild(1).getChild(0)).getComponents());
        assertEquals(((Composite) expectedComposite.getChild(1).getChild(1)).getComponents()
                , ((Composite) actualComposite.getChild(1).getChild(1)).getComponents());
        assertEquals(((Composite) expectedComposite.getChild(2).getChild(0)).getComponents()
                , ((Composite) actualComposite.getChild(2).getChild(0)).getComponents());
    }

    @Test
    public void testWhenTextIsEmpty() {
        //when
        Composite actualComposite = textProcessor.parseText("");
        //then
        assertNull(actualComposite);
    }

    @Test
    public void testWhenTextIsNull() {
        //when
        Composite actualComposite = textProcessor.parseText(null);
        //then
        assertNull(actualComposite);
    }
}
