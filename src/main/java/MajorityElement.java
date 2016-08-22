import java.util.*;
import java.io.*;

public class MajorityElement {
    private static int getMajorityElement(int[] a, int left, int right) {

        if (left == right) {
            return -1;
        }
        if (left + 1 == right) {
            return a[left];
        }
        // int mid = a.length / 2;
        //write your code here
        int mid = left + ((right - left) / 2);
        int len = (right - left) / 2;
        int candidateL = getMajorityElement(a, left, mid);
        int candidateR = getMajorityElement(a, mid, right);
        if (candidateL == candidateR) {
            return candidateL;
        }
        int lCount = count(a, left, right, candidateL);
        int rCount = count(a, left, right, candidateR);
        if (lCount > len ){
            return candidateL;
        }
        else if (rCount > len){
            return candidateR;
        }

        return -1;
    }

    public static int count(int[] a, int left, int right, int candidate) {
        int count = 0;

        for (int i = left; i < right; i++) {
            if(a[i] == candidate){
                count += 1;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        if (getMajorityElement(a, 0, a.length) != -1) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }
    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream stream) {
            try {
                br = new BufferedReader(new InputStreamReader(stream));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}

