import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Scanner;

/**
 * Use Binet formula.
 *
 * http://www.maths.surrey.ac.uk/hosted-sites/R.Knott/Fibonacci/fibFormula.html
 */
public class FibonacciLastDigitBinet {

    private static int getFibonacciLastDigit(int n) {
        BigDecimal two = BigDecimal.valueOf(2);
        BigDecimal srtFive = new BigDecimal(Math.sqrt(5));
        BigDecimal nth = BigDecimal.valueOf(n);
        BigDecimal phi = (BigDecimal.ONE.add(srtFive)).divide(two);
        BigDecimal fib = new BigDecimal(
                ((Math.pow(phi.doubleValue(), n) - Math.pow((1 - phi.doubleValue()), n))
                        /Math.sqrt(5))
        );
        return fib.toBigInteger().mod(BigInteger.TEN).intValue();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int c = getFibonacciLastDigit(n);
        System.out.println(c);
    }
}
