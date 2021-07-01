package com.epam.infohandling.logic;

import com.epam.infohandling.TestData;
import com.epam.infohandling.interpreter.ExpressionCalculator;
import com.epam.infohandling.model.Composite;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TextLogicTest {

    private static final String TEST_FILE_PATH = "src/test/resources/textSample.txt";
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
        Map<String, Integer> variables = testData.getVariables();

        when(expressionCalculator.calculate("[13 x +]", variables)).thenReturn(15);
        when(expressionCalculator.calculate("[3 5 +]", variables)).thenReturn(8);
        when(expressionCalculator.calculate("[8 9 + 2 *]", variables)).thenReturn(34);
        when(expressionCalculator.calculate("[3 4 – 9 * 6 +]", variables)).thenReturn(-3);
        when(expressionCalculator.calculate("[ 20 1 - ]", variables)).thenReturn(19);
        when(expressionCalculator.calculate("[6 2 /]", variables)).thenReturn(3);
        when(expressionCalculator.calculate("[71 2 * 3 +]", variables)).thenReturn(145);
        when(expressionCalculator.calculate("[5 y + 120 *]", variables)).thenReturn(1560);
        when(expressionCalculator.calculate("[80 2 / + 3]", variables)).thenReturn(43);

        Composite actualComposite = textLogic.calculate(testData.getSimpleComposite(), variables);
        Composite expectedComposite = testData.getCalculatedComposite();

        //Assert.assertEquals(expectedComposite, actualComposite);

        Assert.assertEquals(3, actualComposite.getComponents().size());
        Assert.assertEquals(((Composite) expectedComposite.getChild(0).getChild(0)).getComponents(),
                ((Composite) actualComposite.getChild(0).getChild(0)).getComponents());
        Assert.assertEquals(((Composite) expectedComposite.getChild(1).getChild(0)).getComponents(),
                ((Composite) actualComposite.getChild(1).getChild(0)).getComponents());
        Assert.assertEquals(((Composite) expectedComposite.getChild(2).getChild(0)).getComponents(),
                ((Composite) actualComposite.getChild(2).getChild(0)).getComponents());
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

    @Test
    public void testCountSentencesWithEqualWords() {
        //given
        textLogic = new TextLogic();
        //when
        int actualResult = textLogic.countSentencesWithEqualWords(testData.getCompositeTextTree());
        //when
        Assert.assertEquals(4, actualResult);
    }

    @Test
    public void testReverseLexemes() {
        //given
        textLogic = new TextLogic();
        //when
        Composite actualComposite = testData.getSimpleComposite();
        textLogic.reverseLexemes(actualComposite);

        Composite expectedComposite = testData.getReverseComposite();
        //then
        Assert.assertEquals(expectedComposite, actualComposite);
    }

    @Test
    public void testRemovingWithGivenLength() {
        //given
        textLogic = new TextLogic();
        //when
        Composite actualComposite = testData.getSimpleComposite();
        textLogic.removeWordsWithGivenLength(actualComposite, 4);

        Composite expectedComposite = testData.getCompositeWithRemovedWords();
        //then
        Assert.assertEquals(expectedComposite, actualComposite);
    }

    @Test
    public void testRemovingWithGivenLetter() {
        //given
        textLogic = new TextLogic();
        //when
        Composite actualComposite = testData.getSimpleComposite();
        textLogic.removeWordsWithGivenLetter(actualComposite, 'w');

        Composite expectedComposite = testData.getCompositeWithRemovedWords();
        //then
        Assert.assertEquals(expectedComposite, actualComposite);
    }

    @Test
    public void testSortInAlphabeticalOrder() {
        //given
        textLogic = new TextLogic();
        //when
        Composite actualComposite = testData.getCompositeWithWordsOnly();
        textLogic.sortInAlphabeticalOrder(actualComposite);

        Composite expectedComposite = testData.getAlphabeticallySortedComposite();
        //then
        Assert.assertEquals(expectedComposite, actualComposite);
    }

    @Test
    public void testSortSentencesLexemeInDescending() {
        //given
        textLogic = new TextLogic();
        //when
        Composite actualComposite = testData.getCompositeWithWordsOnly();
        textLogic.sortSentencesLexemeInDescending(actualComposite);

        Composite expectedComposite = testData.getSortedSentencesInDescending();
        //then
        Assert.assertEquals(expectedComposite, actualComposite);
    }

    @Test
    public void testSortByLetterCountInDescending() {
        //given
        textLogic = new TextLogic();
        //when
        Composite actualComposite = testData.getCompositeWithWordsOnly();
        textLogic.sortByLetterCountInDescending(actualComposite);

        Composite expectedComposite = testData.getSortedByLetterCountInDescending();
        //then
        Assert.assertEquals(expectedComposite, actualComposite);
    }
}
