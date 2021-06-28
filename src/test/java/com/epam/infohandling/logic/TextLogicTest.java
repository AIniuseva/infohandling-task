package com.epam.infohandling.logic;

import com.epam.infohandling.TestData;
import com.epam.infohandling.interpreter.ExpressionCalculator;
import com.epam.infohandling.model.Composite;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TextLogicTest {

    private final String TEST_FILE_PATH = "src/test/resources/textSample.txt";
    private TextLogic textLogic;
    private TestData testData;

    @Before
    public void setUp() {
        testData = new TestData();
    }

    @Test
    public void testTextCalculator() {
        //when
        ExpressionCalculator expressionCalculator = mock(ExpressionCalculator.class);
        textLogic = new TextLogic(expressionCalculator);

        when(expressionCalculator.calculate("[13 2 +]")).thenReturn(15);
        when(expressionCalculator.calculate("[3 5 +]")).thenReturn(8);
        when(expressionCalculator.calculate("[3 4 – 9 * 6 +]")).thenReturn(-3);
        when(expressionCalculator.calculate("[ 20 1 - ]")).thenReturn(19);
        when(expressionCalculator.calculate("[71 2 * 3 +]")).thenReturn(145);
        when(expressionCalculator.calculate("[5 8 + 120 *]")).thenReturn(1560);

        Composite actualComposite = textLogic.calculate(testData.getCompositeTextTree());
        Composite expectedComposite = testData.getCalculatedCompositeTextTree();
        //then
        Assert.assertEquals(expectedComposite, actualComposite);
    }

    @Test
    public void testTextRestoration() {
        //given
        textLogic = new TextLogic();
        //when
        String actualText = textLogic.restore(testData.getCompositeTextTree());
        String expectedText = testData.getTextForTest(TEST_FILE_PATH);
        //then
        Assert.assertEquals(expectedText, actualText);
    }
}
