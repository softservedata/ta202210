package com.softserve.edu.hw03;

import java.util.ArrayList;
import java.util.List;

public class Brackets {

    public boolean verifyBrackets(String text) {
        List<Character> stack = new ArrayList<>();
        boolean result = true;
        for (int i = 0; (i < text.length()) && result; i++) {
            char current = text.charAt(i);
            if (current == '(') {
                stack.add(')');
            }
            if (current == ')') {
                int lastIndex = stack.size() - 1;
                char lastElement = lastIndex >= 0 ? stack.get(lastIndex) : '_';
                result = current == lastElement;
                if (lastIndex >= 0) {
                    stack.remove(lastIndex);
                }
            }
        }
        return result && stack.isEmpty();
    }
}
