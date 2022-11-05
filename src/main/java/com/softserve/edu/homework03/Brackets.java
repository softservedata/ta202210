package com.softserve.edu.homework03;

import java.util.Scanner;

public class Brackets {

    public boolean verifyBrackets(String text) {
        int bracketsCount = 0;
        boolean result = false;
        for (int i = 0; (i < text.length() && bracketsCount >= 0); i++){
            char currentChar = text.charAt(i);
            switch(currentChar){
                case '(':
                    bracketsCount = bracketsCount + 1;
                    break;
                case ')':
                    bracketsCount = bracketsCount - 1;
                    break;
            }
        }
        if (bracketsCount == 0) {result = true;}
        System.out.println(result);
        return result;
    }
}


