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
        Assert.assertEquals(expectedComposite, actualComposite); //TODO: change?
    }
}
