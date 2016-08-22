import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlacingParentheses {
    private static long getMaximValue(String exp) {

      //write your code here
        ArrayList<Long> digits = new ArrayList<>();
        ArrayList<Character> operations = new ArrayList<>();
        Pattern match = Pattern.compile("[0-9]+|[\\-*+]");
        Matcher matcher = match.matcher(exp);
        while (matcher.find()) {
            String group = matcher.group();
            if(group.matches("[0-9]+")){
                digits.add(Long.parseLong(group));
            }
            if(group.matches("[\\-*+]")){
                operations.add(group.charAt(0));
            }
        }
        int n = digits.size() + 1;
        long[][] m = new long[n][n];
        long[][] M = new long[n][n];
        for (int i = 1; i < n; i++) {
            m[i][i] = digits.get(i - 1);
            M[i][i] = digits.get(i - 1);
        }
        for (int s = 1; s < n; s++) {
            for (int i = 1; i < n - s; i++) {
                int j = i + s;
                minAndMax(m, M, operations, i, j);
            }
        }
        return M[1][n-1];
    }

    private static void minAndMax(long[][] m, long[][] M, ArrayList<Character> ops, int i, int j){
        long min = Long.MAX_VALUE;
        long max = Long.MIN_VALUE;
        for (int k = i; k < j; k++) {
            char op = ops.get(k - 1);
            long a = eval(M[i][k], M[k + 1][j], op);
            long b = eval(M[i][k], m[k + 1][j], op);
            long c = eval(m[i][k], M[k + 1][j], op);
            long d = eval(m[i][k], m[k + 1][j], op);
            min = min(min, a, b, c, d);
            max = max(max, a, b, c, d);
        }
        m[i][j] = min;
        M[i][j] = max;
    }

    private static long min(long min, long a, long b, long c, long d){
        return Math.min(min, Math.min(a, Math.min(b, Math.min(c, d))));
    }
    private static long max(long max, long a, long b, long c, long d){
        return Math.max(max, Math.max(a, Math.max(b, Math.max(c, d))));
    }

    private static long eval(long a, long b, char op) {
        if (op == '+') {
            return a + b;
        } else if (op == '-') {
            return a - b;
        } else if (op == '*') {
            return a * b;
        } else {
            assert false;
            return 0;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String exp = scanner.next();
        System.out.println(getMaximValue(exp));
    }
}

