package com.epam.infohandling.interpeter;

import com.epam.infohandling.interpreter.ExpressionCalculator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ExpressionCalculatorTest {

    private ExpressionCalculator expressionCalculator;

    @Before
    public void setUp() {
        expressionCalculator = new ExpressionCalculator();
    }

    @Test
    public void testCalculatorWhenExpressionIsGiven() {
        //when
        int firstActualResult = expressionCalculator.calculate("[5 8 + 120 *]");
        int secondActualResult = expressionCalculator.calculate("[13 2 +]");
        int thirdActualResult = expressionCalculator.calculate("[3 4 – 9 * 6 +]");
        int fourthActualResult = expressionCalculator.calculate("[71 2 * 3 +]");
        int fifthActualResult = expressionCalculator.calculate("[72 2 / 3 +]");
        //then
        Assert.assertEquals(1560, firstActualResult);
        Assert.assertEquals(15, secondActualResult);
        Assert.assertEquals(-3, thirdActualResult);
        Assert.assertEquals(145, fourthActualResult);
        Assert.assertEquals(39, fifthActualResult);
    }

    @Test
    public void testCalculatorWhenNull() {
        //when
        int actualResult = expressionCalculator.calculate(null);
        //then
        Assert.assertEquals(0, actualResult);
    }

    @Test
    public void testCalculatorWhenEmpty() {
        //when
        int actualResult = expressionCalculator.calculate("");
        //then
        Assert.assertEquals(0, actualResult);
    }
}
