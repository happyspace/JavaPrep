import java.util.Scanner;

/**
 * hackerrank
 */
public class Problem2 {
    private final static String symbol = "#";
    private final static String space = " ";


    static void StairCase(int n) {

        for (int i = 0; i < n; i++) {
            StringBuffer buffer = new StringBuffer();
                for (int j = 0; j < n; j++) {
                    if(j < n - (i + 1)) {
                        buffer.append(space);
                    }
                    else {
                        buffer.append(symbol);
                    }

                }
                System.out.println(buffer.toString());
            }

        }


    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();

        StairCase(a);
    }
}
