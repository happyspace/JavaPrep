package com.happyspace.prep;

import java.util.Scanner;

/**
 * Fibonacci
 */
public class Fibonacci {


    public static long fibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        else return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        System.out.println(fibonacci(n));

    }
}
