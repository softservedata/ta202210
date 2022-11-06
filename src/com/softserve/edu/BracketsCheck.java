package com.softserve.edu;

public class BracketsCheck {
    private final String start = "(";
    private final String finish = ")";


    public boolean verifyBracketsEquity(String chars) {
        int length = chars.length();
        int startNum = 0;
        int finishNum = 0;

        for (int i = 0; i < length ; i++) {
            String sign = Character.toString(chars.charAt(i));
        if (Character.toString(chars.charAt(0)).equals(finish)){
            return false;
        }

            if (sign.equals(start)){
                startNum+=1;
            } else if (sign.equals(finish)) {
                finishNum+=1;
            }
        }

        return startNum == finishNum;
    }

    public int numberBrackets(String chars){
        int num = 0;

        boolean isEqual = verifyBracketsEquity(chars);
        if (!isEqual){
            throw new RuntimeException();
        }

        for (int i = 0; i < chars.length(); i++) {
            String symbol = Character.toString(chars.charAt(i));
            if((symbol.equals(start)) || (symbol.contains(finish))){
                num++;
            }
        }
        return num;
    }


}
