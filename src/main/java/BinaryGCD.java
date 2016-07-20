import java.util.Scanner;

/**
 * Binary GCD .
 * https://en.wikipedia.org/wiki/Binary_GCD_algorithm
 * The algorithm reduces the problem of finding the GCD by repeatedly applying these identities:

 1. gcd(0, v) = v, because everything divides zero, and v is the largest number that divides v. Similarly, gcd(u, 0) = u. gcd(0, 0) is not typically defined, but it is convenient to set gcd(0, 0) = 0.

 2. If u and v are both even, then gcd(u, v) = 2·gcd(u/2, v/2), because 2 is a common divisor.
 3. If u is even and v is odd, then gcd(u, v) = gcd(u/2, v), because 2 is not a common divisor.
 Similarly, if u is odd and v is even, then gcd(u, v) = gcd(u, v/2).

 4. If u and v are both odd, and u ≥ v, then gcd(u, v) = gcd((u − v)/2, v).
 If both are odd and u < v, then gcd(u, v) = gcd((v − u)/2, u).
 These are combinations of one step of the simple Euclidean algorithm,
 which uses subtraction at each step, and an application of step 3 above.
 The division by 2 results in an integer because the difference of two odd numbers is even.[3]

 5. Repeat steps 2–4 until u = v, or (one more step) until u = 0.
 In either case, the GCD is 2kv, where k is the number of common factors of 2 found in step 2.

 6. The algorithm requires O(n2)[4] worst-case time, where n is the number of bits in the
 larger of the two numbers. Although each step reduces at least one of the operands by at
 least a factor of 2, the subtract and shift operations take linear time for very large integers
 (although they're still quite fast in practice, requiring about one operation per word of the representation).

 An extended binary GCD, analogous to the extended Euclidean algorithm, is given by Knuth along with pointers to other versions.[5]
 */
public class BinaryGCD {

    /**
     * A recursive binary algorithm for GCD.
     * @param p first number of GCD
     * @param q second number of GCD
     * @return GCD
     */
    public static int rbGDC(int p, int q){
        if (q == 0)
            return p;
        if (q == 0)
            return q;
        if (p == q)
            return p;


        if ( (p & 1) == 0)
        {
            // q is odd
            if ((q & 1) != 0) {
                return rbGDC(p >> 1, q);
            }
            // step 2 above both are even
            // divide by two
            else {
                return rbGDC(p >> 1, q >> 1) << 1;
            }
        }
        // p is odd q is even
        else if ((q & 1) == 0)
            return rbGDC(p, q >> 1);
        // both are odd
        // reduce larger argument
        if (p > q)
            return rbGDC((p - q) >> 1, q);

        return rbGDC((q - p) >> 1, p);
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();

        System.out.println(rbGDC(a, b));
    }
}
