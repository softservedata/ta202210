package com.softserve.edu;

import java.util.HashMap;
import java.util.Map;

public class Brackets {

    public static boolean verifyBrackets(String str) {
        int i = 0;
        int j = str.length() - 1;

        Map<Character, Character> hashMap = new HashMap<>();
        hashMap.put('(', ')');

        while (i < j) {
            if (hashMap.get(str.charAt(i)) != str.charAt(j))
                return false;
            i++;
            j--;
        }
        return true;
    }

    public static void main(String[] args) {
        String str = "()";
        if (verifyBrackets(str))
            System.out.println("Balanced ");
        else
            System.out.println("Not Balanced ");
    }
}
