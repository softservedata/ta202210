package com.softserve.edu;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.Parameters;

public class BracketsTest {
    @Test
    @Parameters (value = "number")
    public void testOne(){
        System.out.println("(())");
        Assert.assertTrue(true, "test true");
    }
    @Test
    public void testTwo(){
        System.out.println("(())()");
        Assert.assertTrue(true, "test true");
    }

    @Test
    public void testThree(){
        System.out.println("(()");
        Assert.assertFalse(false, "test failure");
    }
    @Test
    public void testFour(){
        System.out.println(")(");
        Assert.assertFalse(false, "test failure");
    }
}
