import java.util.Scanner;

/**
 * GCD
 */
public class EuclidGCD {

    public static int gcd_r(int p, int q){
        if (q == 0) return p;
        else {
            return gcd_r(q, p % q);
        }
    }

    public static int gcd_it(int p, int q) {
        while (q != 0) {
            int temp = q;
            q = p % q;
            p = temp;
        }
        return p;
    }


    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int p = s.nextInt();
        int q = s.nextInt();
        // int d_r = gcd_r(p, q);
        int d_it = gcd_it(p, q);
        // System.out.println("gcd of " + p + " and " + q + " recursive is " + d_r);
        System.out.println(d_it);
    }

}

