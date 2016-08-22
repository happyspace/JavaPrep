import java.util.*;

import static java.lang.Math.min;

class EditDistance {
    public static int EditDistance(String s, String t) {
        int[][] ed = new int[s.length() + 1][t.length() + 1];
        // initialize row and column zero
        for (int i = 0; i < s.length() + 1; i++) {
            ed[i][0] = i;
        }
        for (int i = 0; i < t.length() + 1; i++) {
            ed[0][i] = i;
        }

        for (int i = 1; i < s.length() + 1; i++) {
            for (int j = 1; j < t.length() + 1; j++) {
                int ins = ed[i][j - 1] + 1;
                int del = ed[i - 1][j] + 1;
                int match = ed[i - 1][j - 1];
                int mis = ed[i - 1][j - 1] + 1;
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    ed[i][j] = Min(ins, del, match);
                } else {
                    ed[i][j] = Min(ins, del, mis);
                }
            }
        }

        //write your code here
        return ed[s.length()][t.length()];
    }

    private static int Min(int a, int b, int c){
        return min(min(a, b), c);
    }

    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);

        String s = scan.next();
        String t = scan.next();

        System.out.println(EditDistance(s, t));
    }

}
