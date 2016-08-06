import java.util.*;

public class DotProduct {
    private static long minDotProduct(int[] a, int[] b) {
        //write your code here
        Integer[] abox = Arrays.stream(a).boxed().toArray( Integer[]::new);
        Integer[] bbox = Arrays.stream(b).boxed().toArray( Integer[]::new );
        Arrays.sort(abox, Collections.reverseOrder());
        Arrays.sort(bbox);

        long result = 0;
        for (int i = 0; i < abox.length; i++) {
            result += (long) abox[i] * (long) bbox[i];
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            b[i] = scanner.nextInt();
        }
        System.out.println(minDotProduct(a, b));
    }
}

