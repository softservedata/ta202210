package com.softserve.edu.homework04;

import com.softserve.edu.homework03.Brackets;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class BracketsParamTest {
    @DataProvider
    public Object[][] validDataProvider() {
        return new Object[][] {
                { "(aa(aa()bb)()bb)", true },  { "(aa()bb", false },
                { "adasd)(aa()bb", false },  { ")adasd(aa()bb", false },
                { "(adasd(aa()b)b)a)a", false },  { "(adasd(aa()b)ba", false },
                { "(a)dasd(a)a(()b)b)(a", false }
        };
    }

    @Test(dataProvider = "validDataProvider")
    public void BracketsTest(String text, boolean expectResult) {
        boolean result = Brackets.verifyBrackets(text);
        Assert.assertEquals(result, expectResult);
        System.out.println("+++done ID= " + Thread.currentThread().getId());
    }
}
