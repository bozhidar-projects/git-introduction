package com.softacad.snippets.math;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ComplexNumberTest {
    private ComplexNumber currentResult;
    private static ComplexNumber complexNumber1;
    private static ComplexNumber complexNumber2;

    @BeforeClass
    public static void beforeAllTests() {
        complexNumber1 = new ComplexNumber(3, 4);
        complexNumber2 = new ComplexNumber(5, 3);
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("All tests have run");
    }

    @Before
    public void beforeTest() {
        currentResult = new ComplexNumber(0, 0);
    }

    @After
    public void afterEachTest() {
        System.out.println("Test has run");
    }

    @Test
    public void testAddTwoComplexNumbers() {
        System.out.println("Before test current result is " + currentResult);
        ComplexNumber result = complexNumber1.add(complexNumber2);
        currentResult = result;
        System.out.println("After test currentResult is " + currentResult);

        Assert.assertTrue(
                result.getRealPart() == 8 && result.getImaginaryPart() == 7);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addShouldThrowIllegalArgumentExceptionOnNullArgument() {
        complexNumber1.add(null);
    }

    @Test
    public void testMultiplyTwoComplexNumbers() {
        System.out.println("Before test current result is " + currentResult);
        ComplexNumber result = complexNumber1.multiply(complexNumber2);
        currentResult = result;
        System.out.println("After test currentResult is " + currentResult);

        Assert.assertTrue(
                result.getRealPart() == 3 && result.getImaginaryPart() == 29);
    }

    @Test(expected = IllegalArgumentException.class)
    public void multiplyShouldThrowIllegalArgumentExceptionOnNullArgument() {
        complexNumber1.multiply(null);
    }
}
