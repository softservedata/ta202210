package com.softserve.edu.homework05;

import com.softserve.edu.homework03.Brackets;

import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.testng.PowerMockTestCase;
import org.testng.Assert;
import org.testng.annotations.Test;

@PrepareForTest(Brackets.class)
public class UnitTest extends PowerMockTestCase{
    @Test
    public void checkBracketsNumberPositive() throws Exception{
        Brackets bracketsMock = PowerMockito.mock(Brackets.class);
        PowerMockito.when(bracketsMock.verifyBrackets(Mockito.anyString())).thenReturn(true);
        PowerMockito.when(bracketsMock.numberBrackets(Mockito.anyString())).thenCallRealMethod();
        //
        int actual = bracketsMock.numberBrackets("aa()bb");
        int expected = 2;
        Assert.assertEquals(actual, expected);
}
    @Test
    public void checkBracketsNumberNegative() throws Exception{
        Brackets bracketsMock = PowerMockito.mock(Brackets.class);
        PowerMockito.when(bracketsMock.verifyBrackets(Mockito.anyString())).thenReturn(false);
        PowerMockito.when(bracketsMock.numberBrackets(Mockito.anyString())).thenCallRealMethod();
        //
        //Assert.assertThrows(RuntimeException.class, () -> bracketsMock.numberBrackets("aa()bb"));
        RuntimeException exception = null;
        try {
            bracketsMock.numberBrackets("aa()bb");
        } catch (RuntimeException e) {
            exception = e;
        }
        Assert.assertNotNull(exception);
        Assert.assertEquals(exception.getMessage(), "Incorrect brackets");
    }
}
