package com.softserve.edu;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


public class BracketsCheckTest {
    @ParameterizedTest
    @ValueSource (strings = {"())",")(","(()"})
    public void testBracketsNegative(String strings){
        BracketsCheck br = new BracketsCheck();
        Assert.assertEquals(false, br.verifyBrackets(strings));
    }

    @ParameterizedTest
    @ValueSource(strings = {"()","(())","(())()"})
    public void testBrackets(String strings){
        BracketsCheck br = new BracketsCheck();
        Assert.assertEquals(true, br.verifyBrackets(strings));
    }

}
