import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static java.math.BigInteger.ONE;
import static java.math.BigInteger.TEN;
import static java.math.BigInteger.ZERO;

/**
 * Calculate Fibonacci using Dijkstra's recurrence:
 *    F(2N-1)  = F(N-1)^2 + F(N)^2
 *    F(2N)    = (2 F(N-1) + F(N)) F(N)
 */
public class FibonacciLastDigitDijkstra {
    // remember values that have been calculated
    private static final Map<BigInteger, BigInteger> memoize = new HashMap<>();

    private static BigInteger fibonacciD(BigInteger n){
        if (n.equals(ZERO))
            return ZERO;
        if (n.equals(ONE))
            return ONE;
        if(memoize.containsKey(n))
            return memoize.get(n);

        // check if n is odd
        if(n.testBit(0)) {
            BigInteger n2 = n.shiftRight(1);
            BigInteger n3 = n2.add(ONE);
            BigInteger result = fibonacciD(n2).multiply(fibonacciD(n2)).add(fibonacciD(n3).multiply(fibonacciD(n3)));
            memoize.put(n, result);
            return result;
        }
        else {
            BigInteger n2 = n.shiftRight(1);
            BigInteger n3 = n2.subtract(ONE);
            BigInteger result = fibonacciD(n2).multiply(fibonacciD(n2).add(fibonacciD(n3).add(fibonacciD(n3))));
            memoize.put(n, result);
            return result;

        }
    }

    private static int getFibonacciLastDigit(int n) {
        BigInteger result = fibonacciD(BigInteger.valueOf(n));
        return result.mod(TEN).intValue();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int c = getFibonacciLastDigit(n);
        System.out.println(c);
    }
}
