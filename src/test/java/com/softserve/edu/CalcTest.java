package test.java.com.softserve.edu;

import main.java.com.softserve.edu.Calc;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CalcTest {
    //@Test
    public void firstCheck() {
        //
        //int i = 0;
        double i = 0;
        i = 10 / i;
        //
        if (i == 0) {
            //throw new RuntimeException("hahaha");
        }
        System.out.println("done, i = " + i);
    }

    @Test
    public void checkAdd1() {
        Calc calc = new Calc();
        double actual = 0;
        double expected = 0;
        //
        actual = calc.add(4, 4);
        expected = 8.001;
        //
        // check
        Assert.assertEquals(expected, actual, 0.01);
    }

    @Test
    public void checkAdd2() {
        Calc calc = new Calc();
        double actual = 0;
        double expected = 0;
        //
        actual = calc.add(2, 4);
        expected = 6.001;
        //
        // check
        Assert.assertEquals(expected, actual, 0.01);
    }

    @Test
    public void checkDiv1() {
        Calc calc = new Calc();
        double actual = 0;
        double expected = 0;
        //
        actual = calc.div(20, 4);
        expected = 5;
        //
        // check
        Assert.assertEquals(expected, actual, 0.01);
    }

    @Test
    public void checkDiv2() {
        Calc calc = new Calc();
        double actual = 0;
        double expected = 0;
        //
        actual = calc.div(20, 8);
        expected = 2.5;
        //
        // check
        Assert.assertEquals(expected, actual, 0.01);
    }
}
