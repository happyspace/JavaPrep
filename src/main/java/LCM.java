import java.util.*;

public class LCM {
  private static long lcm(int a, int b) {
    //write your code here
    return ((long) a * b)/rbGDC(a, b);
  }

  private static int rbGDC(int p, int q){
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

    System.out.println(lcm(a, b));
  }
}
