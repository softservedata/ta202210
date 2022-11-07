package com.softserve.edu.homework04;

import java.util.ArrayList;

public class BracketsOrder {

    /**
     * Verifies correct brackets '()' order in any string
     *
     * @param input - string for input
     * @return - true if string contains right order, false - if not
     */
    public boolean verifyBrackets(String input) {
        boolean result;
        ArrayList<Character> list = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '(' || input.charAt(i) == ')') {
                list.add(input.charAt(i));
            }
        }
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
        return result;
    }
}
