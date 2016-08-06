import java.math.BigInteger;
import java.util.Scanner;

public class FibonacciLastDigit {
    private static int getFibonacciLastDigit(int n) {
        int length;
        if( n < 0) {
            n = 0;
        }
        if(n <= 2){
            length = 3;
        }
        else {
            length = n + 1;
        }
        int[] lastDigit = new int[length];
        // Fibonacci starts with 0 1 1
        BigInteger x = BigInteger.ZERO;
        BigInteger y = BigInteger.ONE;
        BigInteger z = BigInteger.ONE;
        lastDigit[0] = x.intValue();
        lastDigit[1] = y.intValue();
        lastDigit[2] = z.intValue();
        for (int i = 3; i <= n; i++) {
            x = y;
            y = z;
            z = x.and(y);
            lastDigit[i] = z.mod(BigInteger.TEN).intValue();
        }
        return lastDigit[n];
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int c = getFibonacciLastDigit(n);
        System.out.println(c);
    }
}

