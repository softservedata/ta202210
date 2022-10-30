package com.softserve.edu;

import org.testng.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class BracketsCheckTest {

    @DataProvider (name = "brackets")
    public static Object [][] bracketSet(){
        return new Object[][] {
                {"())", false}, {")(", false}, {"(()", false},
                {"()", true}, {"(())", true}, {"(())()", true}
        };
    }


    @Test (dataProvider = "brackets" )
    public void testBracketsNegative(String strings, boolean value){
        BracketsCheck br = new BracketsCheck();
        Assert.assertEquals(value, br.verifyBrackets(strings));
    }


}
