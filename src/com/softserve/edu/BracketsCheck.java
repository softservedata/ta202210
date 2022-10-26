package com.softserve.edu;

import org.junit.Assert;
import org.junit.Test;

public class BracketsCheck {
    private final String start = "(";
    private final String finish = ")";


    public boolean verifyBrackets(String chars) {
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
            } else {
                finishNum+=1;
            }
        }

        return startNum == finishNum;
    }


}
