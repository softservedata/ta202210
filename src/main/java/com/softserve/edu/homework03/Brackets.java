package com.softserve.edu.homework03;

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

    public int numberBrackets(String text){
        RuntimeException e = new RuntimeException("Incorrect brackets");
        if (verifyBrackets(text) == false){
            throw e;
        };
        int bracketsCount = 0;
        for (int i = 0; i < text.length(); i++){
            char currentChar = text.charAt(i);
            if (currentChar == '(' || currentChar == ')'){
                    bracketsCount = bracketsCount + 1;
            }
        };
        System.out.println(bracketsCount);
        return bracketsCount;
    };

}
