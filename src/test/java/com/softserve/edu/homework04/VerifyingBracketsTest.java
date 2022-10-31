package test.java.com.softserve.edu.homework04;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class VerifyingBracketsTest {

    /**
     * Verifies correct brackets '()' quantity and order in any string
     *
     * @param input - string for input
     * @return - true if string contains right quantity and order, false - if not
     */
    boolean verifyBrackets(String input) {
        boolean result;
        ArrayList<Character> list = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '(' || input.charAt(i) == ')') {
                list.add(input.charAt(i));
            }
        }
        long char1 = list.stream().filter(e -> e.equals('(')).count();
        long char2 = list.stream().filter(e -> e.equals(')')).count();
        if (char1 != char2) {
            result = false;
        } else {
            int i = 0;
            while (i < list.size() && !list.get(0).equals(')')) {
                if (list.get(i).equals('(') && list.get(i + 1).equals(')')) {
                    list.remove(i);
                    list.remove(i);
                    i = 0;
                } else {
                    i++;
                }
            }
            result = list.isEmpty();
        }
        return result;
    }

    @DataProvider(name = "inputData")
    public Object[][] setupInputData() {
        return new Object[][]{
                {"90fh()))))", false},
                {")()()(lfk", false},
                {"dk(()(fg)(d)())jd", true},
                {"df(g5nh)", true}
        };
    }

    @Test(dataProvider = "inputData")
    public void testVerifyBrackets(String input, boolean expResult) {
        Assert.assertEquals(verifyBrackets(input), expResult, "Brackets in [" + input + "] don`t contain correct order/quantity!");
    }
}
