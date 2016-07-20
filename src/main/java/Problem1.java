import java.util.Scanner;

/**
 * hackrrank
 */
public class Problem1 {

    static int sum(int[] numbers) {
        int result = 0;
        for (int i = 0; i < numbers.length; i++) {
            result = result + numbers[i];
        }
        return result;
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int[] ints = new int[]{1,2,3,4,5};

        System.out.println("moo");
    }
}
