import java.util.*;

/**
 * hackerrank
 */
public class Solution {

    static int numberOfPairs(int[] a, long k) {
        int pairs = 0;
        if(a.length < 2){
            return pairs;
        }
        if(a.length == 2)
            return 1;

        Set<Integer> pairElements = new HashSet<>();
        for (int i = 0; i < a.length - 1; i++) {
            int ai = a[i];
            for (int j = i + 1; j < a.length; j++) {
                int aj = a[j];
                if(!pairElements.contains(ai)) {
                    long sum = ai + aj;
                    if (sum == k) {
                        pairElements.add(ai);
                        pairElements.add(aj);
                        pairs++;
                    }
                }
            }
        }
        return pairs;
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        // int a = scanner.nextInt();
        int[] a = new int[]{1,2,46,1,39};
        int k = 47;
        long pairs1 = numberOfPairs(a, k);

        int[] b = new int[]{6,6,3,9,3,5,1};
        int kk = 12;
        long pairs2 = numberOfPairs(b, kk);


        System.out.println("moo");
    }
}
