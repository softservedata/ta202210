package com.softserve.edu;

public class VerifyBrackets {

        public static boolean verifyBrackets(String exp) {

            // Initialising variables
            boolean flag = true;
            int count = 0;

            // Traversing the expression
            for (int i = 0; i < exp.length(); i++) {
                if (exp.charAt(i) == '(') {
                    count++;
                } else {

                    // It is a closing parenthesis
                    count--;
                }
                if (count < 0) {

                    // This means there are
                    // more Closing parenthesis
                    // than opening ones
                    flag = false;
                    break;
                }
            }

            // If count is not zero,
            // It means there are
            // more opening parenthesis
            if (count != 0) {
                flag = false;
            }
            return flag;
        }

 /*          public static void main(String[] args) {
            String exp1 = ")(())";

            if (verifyBrackets(exp1))
                System.out.println("Balanced");
            else
                System.out.println("Not Balanced");

            String exp2 = "()()()";

            if (verifyBrackets(exp2))
                System.out.println("Balanced");
            else
                System.out.println("Not Balanced");
        }*/
    }

