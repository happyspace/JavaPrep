import java.util.*;

public class Knapsack {
    static int optimalWeight(int W, int[] w) {
        int[][] kn = new int[W + 1][w.length + 1];

        for (int i = 1; i < w.length + 1; i++) {
            for (int wi = 1; wi < W + 1; wi++) {
                kn[wi][i] = kn[wi][i - 1];
                int wItem = w[i - 1];
                if(wItem <= wi) {
                    int val = kn[wi - wItem][i - 1] + wItem;
                    if(kn[wi][i] < val){
                        kn[wi][i] = val;
                    }
                }
            }
        }
        return kn[W][w.length];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int W, n;
        W = scanner.nextInt();
        n = scanner.nextInt();
        int[] w = new int[n];
        for (int i = 0; i < n; i++) {
            w[i] = scanner.nextInt();
        }
        System.out.println(optimalWeight(W, w));
    }
}

