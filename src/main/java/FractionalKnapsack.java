import java.util.*;

public class FractionalKnapsack {
    private static double getOptimalValue(int capacity, int[] values, int[] weights) {
        double value = 0;
        int weightAdded = 0;
        Map<Double, Integer> items = new TreeMap<>(Collections.reverseOrder());
        for (int i = 0; i < values.length; i++) {
            // deal with duplicate keys
            double perUnit = (double) values[i] / weights[i];
            if(items.containsKey(perUnit)){
                int total = items.get(perUnit) + weights[i];
                items.put(perUnit, total);
            }
            else {
                items.put(perUnit, weights[i]);
            }

        }

        for (Map.Entry<Double, Integer> entry : items.entrySet()) {
            int leftToFill = capacity - weightAdded;
            if(weightAdded < capacity){
                if(leftToFill >= entry.getValue()){
                    weightAdded = weightAdded + entry.getValue();
                    double addedValue = (double) entry.getValue() * entry.getKey();
                    value = value + addedValue;
                } else {
                    double addedValue = leftToFill * entry.getKey();
                    value = value + addedValue;
                    break;
                }
            }
        }

        return value;
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int capacity = scanner.nextInt();
        int[] values = new int[n];
        int[] weights = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = scanner.nextInt();
            weights[i] = scanner.nextInt();
        }
        System.out.println(getOptimalValue(capacity, values, weights));
    }

} 
