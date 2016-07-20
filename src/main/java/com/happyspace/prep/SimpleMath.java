package com.happyspace.prep;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 * hackerrank
 */
public class SimpleMath {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        double mealCost = scan.nextDouble(); // original meal price
        int tipPercent = scan.nextInt(); // tip percentage
        int taxPercent = scan.nextInt(); // tax percentage
        scan.close();

        // Write your calculation code here.
        BigDecimal meal = BigDecimal.valueOf(mealCost);

        BigDecimal tip = BigDecimal.valueOf(tipPercent).divide(BigDecimal.valueOf(100)).multiply(meal);
        BigDecimal tax = BigDecimal.valueOf(taxPercent).divide(BigDecimal.valueOf(100)).multiply(meal);
        BigDecimal total = BigDecimal.valueOf(mealCost).add(tip).add(tax);

        // cast the result of the rounding operation to an int and save it as totalCost
        int totalCost = (int) Math.round(total.doubleValue());

        // Print your result
        System.out.format("The total meal cost is %d dollars.", totalCost);
    }
}
