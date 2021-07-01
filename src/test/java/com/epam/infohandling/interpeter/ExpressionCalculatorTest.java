package com.epam.infohandling.interpeter;

import com.epam.infohandling.TestData;
import com.epam.infohandling.interpreter.ExpressionCalculator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

public class ExpressionCalculatorTest {

    private ExpressionCalculator expressionCalculator;

    @Before
    public void setUp() {
        expressionCalculator = new ExpressionCalculator();
    }

    @Test
    public void testCalculatorWhenExpressionIsGiven() {
        //when
        TestData testData = new TestData();
        Map<String, Integer> variables = testData.getVariables();

        int firstActualResult = expressionCalculator.calculate("[5 8 + 120 *]", variables);
        int secondActualResult = expressionCalculator.calculate("[13 x +]", variables);
        int thirdActualResult = expressionCalculator.calculate("[3 4 – 9 * 6 +]", variables);
        int fourthActualResult = expressionCalculator.calculate("[71 2 * 3 +]", variables);
        int fifthActualResult = expressionCalculator.calculate("[72 2 / 3 +]", variables);
        int sixthActualResult = expressionCalculator.calculate("[5 y + 120 *]", variables);
        //then
        Assert.assertEquals(1560, firstActualResult);
        Assert.assertEquals(15, secondActualResult);
        Assert.assertEquals(-3, thirdActualResult);
        Assert.assertEquals(145, fourthActualResult);
        Assert.assertEquals(39, fifthActualResult);
        Assert.assertEquals(1560, sixthActualResult);
    }

    @Test
    public void testCalculatorWhenNull() {
        //when
        int actualResult = expressionCalculator.calculate(null, null);
        //then
        Assert.assertEquals(0, actualResult);
    }

    @Test
    public void testCalculatorWhenEmpty() {
        //when
        int actualResult = expressionCalculator.calculate("", null);
        //then
        Assert.assertEquals(0, actualResult);
    }
}
