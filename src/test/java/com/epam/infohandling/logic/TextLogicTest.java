package com.epam.infohandling.logic;

import com.epam.infohandling.util.TestData;
import com.epam.infohandling.interpreter.ExpressionCalculator;
import com.epam.infohandling.model.Composite;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TextLogicTest {

    private static final String TEST_FILE_PATH = "src/test/resources/textSample.txt";
    private TextLogic textLogic;
    private TestData testData;

    @Before
    public void setUp() {
        textLogic = new TextLogic();
        testData = new TestData();
    }

    @Test
    public void testTextCalculator() {
        //when
        ExpressionCalculator expressionCalculator = mock(ExpressionCalculator.class);
        textLogic = new TextLogic(expressionCalculator);

        when(expressionCalculator.calculate("[13 x +]")).thenReturn(15);
        when(expressionCalculator.calculate("[3 5 +]")).thenReturn(8);
        when(expressionCalculator.calculate("[8 9 + 2 *]")).thenReturn(34);
        when(expressionCalculator.calculate("[3 4 - 9 * 6 +]")).thenReturn(-3);
        when(expressionCalculator.calculate("[ 20 1 - ]")).thenReturn(19);
        when(expressionCalculator.calculate("[6 2 /]")).thenReturn(3);
        when(expressionCalculator.calculate("[71 2 * 3 +]")).thenReturn(145);
        when(expressionCalculator.calculate("[5 y + 120 *]")).thenReturn(1560);
        when(expressionCalculator.calculate("[80 2 / 3 +]")).thenReturn(43);

        Composite actualComposite = textLogic.calculate(testData.getSimpleComposite());
        Composite expectedComposite = testData.getCalculatedComposite();
        //then
        assertComposite(expectedComposite, actualComposite);
    }

    @Test
    public void testTextRestoration() {
        //when
        String actualText = textLogic.restore(testData.getCompositeTextTree());
        String expectedText = testData.getTextForTest(TEST_FILE_PATH);
        //then
        assertEquals(expectedText, actualText);
    }

    @Test
    public void testCountSentencesWithEqualWords() {
        //when
        int actualResult = textLogic.countSentencesWithEqualWords(testData.getCompositeTextTree());
        //then
        assertEquals(4, actualResult);
    }

    @Test
    public void testReverseLexemes() {
        //when
        Composite actualComposite = testData.getSimpleComposite();
        textLogic.reverseLexemes(actualComposite);
        Composite expectedComposite = testData.getReverseComposite();
        //then
        assertComposite(expectedComposite, actualComposite);
    }

    @Test
    public void testRemovingWithGivenLength() {
        //when
        Composite actualComposite = testData.getSimpleComposite();
        textLogic.removeWordsWithGivenLength(actualComposite, 4);
        Composite expectedComposite = testData.getCompositeWithRemovedWords();
        //then
        assertComposite(expectedComposite, actualComposite);
    }

    @Test
    public void testRemovingWithGivenLetter() {
        //when
        Composite actualComposite = testData.getSimpleComposite();
        textLogic.removeWordsWithGivenLetter(actualComposite, 'w');
        Composite expectedComposite = testData.getCompositeWithRemovedWords();
        //then
        assertComposite(expectedComposite, actualComposite);
    }

    @Test
    public void testSortInAlphabeticalOrder() {
        //when
        Composite actualComposite = testData.getCompositeWithWordsOnly();
        textLogic.sortInAlphabeticalOrder(actualComposite);
        Composite expectedComposite = testData.getAlphabeticallySortedComposite();
        //then
        assertComposite(expectedComposite, actualComposite);
    }

    @Test
    public void testSortSentencesLexemeInDescending() {
        //when
        Composite actualComposite = testData.getCompositeWithWordsOnly();
        textLogic.sortSentencesLexemeInDescending(actualComposite);
        Composite expectedComposite = testData.getSortedSentencesInDescending();
        //then
        assertComposite(expectedComposite, actualComposite);
    }

    @Test
    public void testSortByLetterCountInDescending() {
        //when
        Composite actualComposite = testData.getCompositeWithWordsOnly();
        textLogic.sortByLetterCountInDescending(actualComposite);
        Composite expectedComposite = testData.getSortedByLetterCountInDescending();
        //then
        assertComposite(expectedComposite, actualComposite);
    }

    private void assertComposite(Composite expectedComposite, Composite actualComposite) {
        assertEquals(3, actualComposite.getComponents().size());
        assertEquals(((Composite) expectedComposite.getChild(0).getChild(0)).getComponents(),
                ((Composite) actualComposite.getChild(0).getChild(0)).getComponents());
        assertEquals(((Composite) expectedComposite.getChild(1).getChild(0)).getComponents(),
                ((Composite) actualComposite.getChild(1).getChild(0)).getComponents());
        assertEquals(((Composite) expectedComposite.getChild(2).getChild(0)).getComponents(),
                ((Composite) actualComposite.getChild(2).getChild(0)).getComponents());
    }
}
