package com.softserve.edu;

import org.testng.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


public class BracketsCheckTest {
    BracketsCheck br;

    @BeforeClass
    public void setup(){
         br = new BracketsCheck();
    }

    @DataProvider (name = "testbracketsvalid")
    public static Object [][] bracketSet(){
        return new Object[][] {
                {"())", false}, {")(", false}, {"(()", false},
                {"()", true}, {"(())", true}, {"(())()", true}, {"(1(name))(a)", true}
        };
    }

    @DataProvider (name = "testBracketsNum")
    public static Object [][] bracketNum(){
        return new Object[][]{
                {"()()", 4}, {"(()())", 6}, {"((()))()()", 10}
        };
    }


    @Test (testName = "hw2", description = "verify the all the brackets close",
            dataProvider = "testbracketsvalid" )
    public void testbracketsvalid(String strings, boolean value){
        Assert.assertEquals(br.verifyBracketsEquity(strings), value);
    }

    @Test
    public void testbracketsvalid11(){
        Assert.assertEquals(br.verifyBracketsEquity(")("), false);
    }

    @Test(description = "count the number of brackets",
    dataProvider = "testBracketsNum")
    public void numberBracketsTest(String strings, int num){
        Assert.assertEquals(br.numberBrackets(strings), num);
    }

    @Test (testName = "hw#3",
            description = "count the number of brackets with the spy equity method",
    dataProvider = "testBracketsNum" )
    public void numberBracketsTest2(String strings, int num){
        br = spy(BracketsCheck.class);
        when(br.verifyBracketsEquity(anyString())).thenReturn(true);
        Assert.assertEquals(br.numberBrackets(strings), num);
    }
}
