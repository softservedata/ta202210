package com.softserve.homework04;

import com.softserve.edu.VerifyBrackets;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class VerifyBracketsParametersTest {
    private static VerifyBrackets verifyBrackets;
    @BeforeClass
    public static void setVerifyBrackets(){
        verifyBrackets = new VerifyBrackets();
    }
    @Test
    public void testVerifyBrackets (){
        String text = "(())";
        boolean actual = verifyBrackets.verifyBrackets(text);
        Assert.assertTrue(false);
    }

}
