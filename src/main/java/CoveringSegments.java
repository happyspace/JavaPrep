import java.util.*;

public class CoveringSegments {

    private static int[] optimalPoints(Segment[] segments) {
        //write your code here
        Arrays.sort(segments);
        int[] points = new int[2 * segments.length];
        List<Integer> op = new ArrayList<>(segments.length);
        for (int i = 0; i < segments.length; i++) {
            points[2 * i] = segments[i].start;
            points[2 * i + 1] = segments[i].end;
        }
        for (int i = 0; i < points.length; i += 2) {
            if(op.isEmpty()){
                op.add(points[i + 1]);
            }
            else {
                int rightMost = op.get(op.size() - 1);
                if (points[i] > rightMost){
                    op.add(points[i + 1]);
                }
            }
        }
        return op.stream().mapToInt(i -> i).toArray();
    }

    private static class Segment implements Comparable<Segment> {
        int start, end;

        Segment(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Segment o) {
            return Integer.compare(this.end, o.end);
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Segment[] segments = new Segment[n];
        for (int i = 0; i < n; i++) {
            int start, end;
            start = scanner.nextInt();
            end = scanner.nextInt();
            segments[i] = new Segment(start, end);
        }
        int[] points = optimalPoints(segments);
        System.out.println(points.length);
        for (int point : points) {
            System.out.print(point + " ");
        }
    }
}
 
