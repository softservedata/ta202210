package com.softserve.edu;

import org.junit.Assert;
import org.junit.Test;

public class BracketsCheckTest {
    @Test
    public void testBrackets(){
        BracketsCheck br = new BracketsCheck();

        Assert.assertEquals(false, br.verifyBrackets("())"));
        Assert.assertEquals(false, br.verifyBrackets(")("));
        Assert.assertEquals(false, br.verifyBrackets("(()"));
        Assert.assertEquals(true, br.verifyBrackets("()"));
        Assert.assertEquals(true, br.verifyBrackets("(())"));
        Assert.assertEquals(true, br.verifyBrackets("(())()"));


    }
}
