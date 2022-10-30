package com.softserve.edu;



import org.testng.Assert;
import org.testng.annotations.Test;



public class BracketsTest {

    @Test
    public void testOne(){
        System.out.println("(())");
        Assert.assertTrue(true, "test true");
    }

    @Test
    public void testTwo(){
        System.out.println("(()");
        Assert.assertFalse(false, "test failure");
    }

}
