package com.softserve.edu;
import java.util.Scanner;

public class Appl {
    public static void main(String[] args) {
        //
        Calc calc = new Calc();
        //
        double a = 0;
        double b = 0;
        double c = 0;
        //
        Scanner sc = new Scanner(System.in);
        System.out.print("a = ");
        a = sc.nextDouble();
        System.out.print("b = ");
        b = sc.nextDouble();
        //
        // Operation
        //c = a + b; // no testabiliti
        c = calc.add(a, b);
        //
        // Result
        System.out.println("c = " + c);
        //
        System.out.println("All is ready");
    }
}
