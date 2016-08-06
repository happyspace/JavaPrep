import java.util.Scanner;

public class Change {


    private static int[] coins = {10, 5, 1};

    private static int getChange(int m) {
        int numCoins = 0;
        if(m > 0) {
            int rem = m;
            for (int coin : coins) {
                // integer divide
                int n = rem / coin;
                if(n > 0) {
                    numCoins = numCoins + n;
                }
                rem = rem % coin;
            }
        }
        return numCoins;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        System.out.println(getChange(m));

    }
}

