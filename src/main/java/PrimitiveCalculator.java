import java.util.*;

public class PrimitiveCalculator {
    private static List<Integer> optimal_sequence(int n) {
        List<Integer> sequence = new ArrayList<Integer>();
        while (n >= 1) {
            sequence.add(n);
            if (n % 3 == 0) {
                n /= 3;
            } else if (n % 2 == 0) {
                n /= 2;
            } else {
                n -= 1;
            }
        }
        Collections.reverse(sequence);
        return sequence;
    }

    private enum Operations {
        PLUS_ONE {
            @Override
            public int operation(int i) {
                return i + 1;
            }

            @Override
            public int invOperation(int i) {
                return i - 1;
            }

            @Override
            public int mod(int i) {
                return  0;
            }
        },
        TIMES_TWO {
            @Override
            public int operation(int i) {
                return  i * 2;
            }

            @Override
            public int invOperation(int i) {
                return  i / 2;
            }

            @Override
            public int mod(int i) {
                return i % 2;
            }

        },
        TIMES_THREE {
            @Override
            public int operation(int i) {
                return i * 3;
            }

            @Override
            public int invOperation(int i) {
                return i / 3;
            }

            @Override
            public int mod(int i) {
                return i % 3;
            }
        };
        public abstract int operation(int i);
        public abstract int invOperation(int i);
        public abstract int mod(int i);
    }

    private static List<Integer> optimal_sequence_dy(int n){
        List<Integer> sequence = new ArrayList<>();
        sequence.add(0);
        sequence.add(0);
        // calculate the min number of steps.
        for (int i = 2; i < n + 1; i++) {
            sequence.add(i, Integer.MAX_VALUE);
            for(Operations op : Operations.values()){
                if(op.mod(i) == 0){
                    int inv = op.invOperation(i);
                    int no = sequence.get(inv) + 1;
                    if ( no < sequence.get(i)) {
                        sequence.set(i, no);
                    }
                }
            }
        }
        // recreate the list of numbers
        List<Integer> numbers = new ArrayList<>();
        int nn = n;
        numbers.add(n);
        while (nn != 1) {
            int lowest_n = Integer.MAX_VALUE;
            int lowest_steps = Integer.MAX_VALUE;
            for(Operations op: Operations.values()){
                if(op.mod(nn) == 0){
                    int target = op.invOperation(nn);
                    if (sequence.get(target) < lowest_steps) {
                        lowest_steps = sequence.get(target);
                        lowest_n = target;
                    }
                }
            }
            numbers.add(lowest_n);
            nn = lowest_n;
        }
        Collections.reverse(numbers);
        return numbers;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> sequence = optimal_sequence_dy(n);
        System.out.println(sequence.size() - 1);
        for (Integer x : sequence) {
            System.out.print(x + " ");
        }
    }
}

