package com.softserve.edu.hw3;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;


public class BracketsTest {
    private static Brackets brackets;

    @BeforeClass
    public static void setUp(){
        brackets = new Brackets();
    }

    @Test
    public void checkValid(){
        String text = "(aabb)";
        boolean actual = brackets.verifyBrackets(text);
        Assert.assertTrue(actual);
    }

    @Test
    public void checkValid1(){
        String text = "(aa()bb)";
        boolean actual = brackets.verifyBrackets(text);
        Assert.assertTrue(actual);
    }

    @Test
    public void checkValid2(){
        String text = "(a(a))b(b)";
        boolean actual = brackets.verifyBrackets(text);
        Assert.assertTrue(actual);
    }

    @Test
    public void checkInValid(){
        String text = "(aa()b";
        boolean actual = brackets.verifyBrackets(text);
        Assert.assertFalse(actual);
    }

    @Test
    public void checkInValid2(){
        String text = ")aa(b";
        boolean actual = brackets.verifyBrackets(text);
        Assert.assertFalse(actual);
    }

    @Test
    public void checkInValid3(){
        String text = "(aas))b";
        boolean actual = brackets.verifyBrackets(text);
        Assert.assertFalse(actual);
    }

}
