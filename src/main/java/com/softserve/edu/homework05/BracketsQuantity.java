package com.softserve.edu.homework05;

import com.softserve.edu.homework04.BracketsOrder;

import java.util.ArrayList;

public class BracketsQuantity {
    private BracketsOrder bracketsOrder;

    public BracketsQuantity(BracketsOrder bracketsOrderObject) {
        System.out.println("Constructor : dependency injection ...");
        this.bracketsOrder = bracketsOrderObject;
    }

    public BracketsQuantity() {
        System.out.println("Constructor: .....");
        bracketsOrder = new BracketsOrder();
    }

    /**
     * Verifies brackets quantity
     * @param input     - string, where brackets will be counted
     * @return          - total number of brackets
     */
    public int countBrackets(String input) {
        try {
            bracketsOrder.verifyBrackets(input);
        } catch (RuntimeException e) {
            System.out.println("Method verifyBrackets() returns 'false'.\n Hmmm... Let`s count brackets quantity :");

        }
        ArrayList<Character> list = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '(' || input.charAt(i) == ')') {
                list.add(input.charAt(i));
            }
        }
        return list.size();
    }
}
