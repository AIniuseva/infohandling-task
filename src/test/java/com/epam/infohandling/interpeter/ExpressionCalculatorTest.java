package com.epam.infohandling.interpeter;

import com.epam.infohandling.util.TestData;
import com.epam.infohandling.interpreter.ExpressionCalculator;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;

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
        expressionCalculator = new ExpressionCalculator(variables);

        int firstActualResult = expressionCalculator.calculate("[5 8 + 120 *]");
        int secondActualResult = expressionCalculator.calculate("[13 x +]");
        int thirdActualResult = expressionCalculator.calculate("[3 4 - 9 * 6 +]");
        int fourthActualResult = expressionCalculator.calculate("[71 2 * 3 +]");
        int fifthActualResult = expressionCalculator.calculate("[72 2 / 3 +]");
        int sixthActualResult = expressionCalculator.calculate("[5 y + 120 *]");
        //then
        assertEquals(1560, firstActualResult);
        assertEquals(15, secondActualResult);
        assertEquals(-3, thirdActualResult);
        assertEquals(145, fourthActualResult);
        assertEquals(39, fifthActualResult);
        assertEquals(1560, sixthActualResult);
    }

    @Test
    public void testCalculatorWhenExpressionIsNull() {
        //when
        int actualResult = expressionCalculator.calculate(null);
        //then
        assertEquals(0, actualResult);
    }

    @Test
    public void testCalculatorWhenExpressionIsEmpty() {
        //when
        int actualResult = expressionCalculator.calculate("");
        //then
        assertEquals(0, actualResult);
    }
}
