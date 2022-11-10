package com.softserve.edu.hw5;

import com.softserve.edu.hm5.Brackets;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class TestNumberBrackets {
    private static Brackets mock;
    @BeforeClass
    public static void beforeClass() {
        mock = new Brackets();
        mock = Mockito.mock(Brackets.class);
    }

    @Test
    public void positiveScenario () {
        when(mock.verifyBrackets(anyString())).thenReturn(false);
        mock.numberBrackets("()(())");
        Assert.assertTrue(true);
    }
    @Test
    public void negativeScenario () {
        when(mock.verifyBrackets(anyString())).thenReturn(true);
        mock.numberBrackets("()(())");
        Assert.assertFalse(false);
    }

}
