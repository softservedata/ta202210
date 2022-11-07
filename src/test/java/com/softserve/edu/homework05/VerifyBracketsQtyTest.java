package com.softserve.edu.homework05;

import com.softserve.edu.homework04.BracketsOrder;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.testng.PowerMockObjectFactory;
import org.testng.Assert;
import org.testng.annotations.*;

@PrepareForTest(BracketsOrder.class)
public class VerifyBracketsQtyTest {

    @ObjectFactory
    public PowerMockObjectFactory setObjectFactory() {
        return new PowerMockObjectFactory();
    }

    @DataProvider(name = "inputData")
    public Object[][] setupEvenBracketsQty() {
        return new Object[][]{
                {"90fh()))))", 6},
                {")()()(lfk", 6}
        };
    }

    @DataProvider(name = "inputData2")
    public Object[][] setupOddBracketsQty() {
        return new Object[][]{
                {"dk(()(fg)(d)())jd", 10},
                {"df(g5nh)", 2}
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
    public void testCountEvenBracketsWithPowerMockito(String input, int expectedQty) throws Exception {
        BracketsOrder bracketsOrder = PowerMockito.mock(BracketsOrder.class);
        PowerMockito.when(bracketsOrder.verifyBrackets(Mockito.anyString())).thenReturn(true);
        PowerMockito.whenNew(BracketsOrder.class).withNoArguments().thenReturn(bracketsOrder);
        BracketsQuantity bracketsQty = new BracketsQuantity();

        // Verify expected and actual quantity
        int actualQty = bracketsQty.countBrackets(input);
        Assert.assertEquals(actualQty, expectedQty, actualQty + " should be equal to " + expectedQty);
    }

    @Test(dataProvider = "inputData2")
    public void testCountOddBracketsWithDependencyInjection(String input, int expectedQty) {
        BracketsOrder bracketsOrder = PowerMockito.mock(BracketsOrder.class);
        PowerMockito.when(bracketsOrder.verifyBrackets(Mockito.anyString())).thenReturn(false);
        BracketsQuantity bracketsQty = new BracketsQuantity(bracketsOrder);

        // Verify expected and actual quantity
        int actualQty = bracketsQty.countBrackets(input);
        Assert.assertEquals(actualQty, expectedQty, actualQty + " should be equal to " + expectedQty);
    }

    @AfterClass(alwaysRun = true)
    public void resetConfigs() {
        System.out.println("Cleaning up after ...");
    }
}
