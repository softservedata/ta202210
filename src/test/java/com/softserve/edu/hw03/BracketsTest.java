package com.softserve.edu.hw03;

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
    public void chackValid() {
        String text = "(aa()bb)";
        boolean actual = brackets.verifyBrackets(text);
        Assert.assertTrue(actual);
    }

    @Test
    public void chackInValid2() {
        String text = "(aa()bb";
        boolean actual = brackets.verifyBrackets(text);
        Assert.assertFalse(actual);
    }

    @Test
    public void chackInValid3() {
        String text = "adasd)(aa()bb";
        boolean actual = brackets.verifyBrackets(text);
        Assert.assertFalse(actual);
    }

    @Test
    public void chackInValid4() {
        String text = ")adasd(aa()bb";
        boolean actual = brackets.verifyBrackets(text);
        Assert.assertFalse(actual);
    }

    @Test
    public void chackInValid5() {
        String text = "(adasd(aa()b)b)a)a";
        boolean actual = brackets.verifyBrackets(text);
        Assert.assertFalse(actual);
    }

    @Test
    public void chackInValid6() {
        String text = "(adasd(aa()b)ba";
        boolean actual = brackets.verifyBrackets(text);
        Assert.assertFalse(actual);
    }
}
