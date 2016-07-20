package com.happyspace.prep;

import java.util.Scanner;

/**
 * hackerrank stuff
 */
public class Printing {

    public static void main(String[] args) {
        int i = 4;
        double d = 4.0;
        String s = "HackerRank ";

        Scanner scan = new Scanner(System.in);

        int j;
        double k;
        String l = "";

        j = scan.nextInt();
        k = scan.nextDouble();
        l = scan.next();
        l = l + scan.nextLine();

        int add_int = i + j;
        double add_double = k + d;

        System.out.format("%d%n", add_int);
        System.out.format("%.1f%n", add_double);
        System.out.format("%s", s + l);

        scan.close();
    }
}
