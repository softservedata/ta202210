package com.softserve.edu.homework04;

import org.testng.Assert;
import org.testng.annotations.*;

public class VerifyBracketsOrderTest {

    private BracketsOrder brackets;

    @DataProvider(name = "inputData")
    public Object[][] setupInputData() {
        return new Object[][]{
                {"90fh()))))", false},
                {")()()(lfk", false},
                {"dk(()(fg)(d)())jd", true},
                {"df(g5nh)", true}
        };
    }

    @BeforeClass
    public void setup() {
        System.out.println("BeforeClass method was called ...");
    }

    @BeforeMethod
    public void testDataInitialization() {
        System.out.println("Test Data for tests will be taken from DataProvider ...");
    }

    @Test(dataProvider = "inputData")
    public void testVerifyBrackets(String input, boolean expResult) {
        brackets = new BracketsOrder();
        Assert.assertEquals(brackets.verifyBrackets(input), expResult,
                "Brackets in [" + input + "] don`t contain correct order/quantity!");
    }

    @Test(dataProvider = "inputData")
    public void testCountBracketsWithMock() {

    }

    @AfterClass(alwaysRun = true)
    public void resetConfigs() {
        System.out.println("Cleaning up after ...");
    }
}
