package com.softserve.edu.homework03;

import org.junit.BeforeClass;
import org.junit.Test;
import org.testng.Assert;

public class BracketsTest {
    private static Brackets brackets;

    @BeforeClass
    public static void setUp() {
        brackets = new Brackets();
    }

    @Test
    public void checkValid1() {
        String text = "(aa()bb)";
        boolean actual = brackets.verifyBrackets(text);
        Assert.assertTrue(actual);
    }

    @Test
    public void checkInValid2() {
        String text = "(aa()bb";
        boolean actual = brackets.verifyBrackets(text);
        Assert.assertFalse(actual);
    }

    @Test
    public void checkInValid3() {
        String text = "adasd)(aa()bb";
        boolean actual = brackets.verifyBrackets(text);
        Assert.assertFalse(actual);
    }

    @Test
    public void checkInValid4() {
        String text = ")adasd(aa()bb";
        boolean actual = brackets.verifyBrackets(text);
        Assert.assertFalse(actual);
    }

    @Test
    public void checkInValid5() {
        String text = "(adasd(aa()b)b)a)a";
        boolean actual = brackets.verifyBrackets(text);
        Assert.assertFalse(actual);
    }

    @Test
    public void checkInValid6() {
        String text = "(adasd(aa()b)ba";
        boolean actual = brackets.verifyBrackets(text);
        Assert.assertFalse(actual);
    }

    @Test
    public void checkInValid7() {
        String text = "(a)dasd(a)a(()b)b)(a";
        boolean actual = brackets.verifyBrackets(text);
        Assert.assertFalse(actual);
    }
}


