import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BuildHeap {
    private int[] data;
    private List<Swap> swaps;

    private FastScanner in;
    private PrintWriter out;

    public static void main(String[] args) throws IOException {
        new BuildHeap().solve();
    }

    private void readData() throws IOException {
        int n = in.nextInt();
        data = new int[n];
        for (int i = 0; i < n; ++i) {
          data[i] = in.nextInt();
        }
    }

    private void writeResponse() {
        out.println(swaps.size());
        for (Swap swap : swaps) {
          out.println(swap.index1 + " " + swap.index2);
        }
    }

    private void generateSwaps() {
      swaps = new ArrayList<Swap>();
      // The following naive implementation just sorts 
      // the given sequence using selection sort algorithm
      // and saves the resulting sequence of swaps.
      // This turns the given array into a heap, 
      // but in the worst case gives a quadratic number of swaps.
      //
      // TODO: replace by a more efficient implementation

        ArrayList<Integer> dd = new ArrayList<>();
        for (int i = 0; i < data.length; i++) {
            dd.add(data[i]);
        }

        BinaryHeap<Integer> bh = new BinaryHeap<Integer>(dd, swaps);

        /**
         *

        for (int i = 0; i < data.length; ++i) {
        for (int j = i + 1; j < data.length; ++j) {
          if (data[i] > data[j]) {
            swaps.add(new Swap(i, j));
            int tmp = data[i];
            data[i] = data[j];
            data[j] = tmp;
          }
        }
      }
         */
    }

    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        readData();
        generateSwaps();
        writeResponse();
        out.close();
    }

    static class Swap {
        int index1;
        int index2;

        public Swap(int index1, int index2) {
            this.index1 = index1;
            this.index2 = index2;
        }
    }

    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

    public static class BinaryHeap<E extends Comparable<? super E>> {
        private List<E> data = new ArrayList<>();
        private int size;
        private List<Swap> swaps;

        public BinaryHeap() {
            data = new ArrayList<>();
        }

        public BinaryHeap(ArrayList<E> data, List<Swap> swaps) {
            size = data.size();
            this.data = new ArrayList<E>();
            this.data.add(null);
            this.data.addAll(data);
            this.swaps = swaps;
            buildHeap();
        }

        public int parent(int i){
            return i / 2;
        }

        public int leftChild(int i) {
            return 2 * i;
        }

        public int rightChild(int i){
            return (2 * i) + 1;
        }

        public void siftUp(int i){
            int j = i;
            E parent = data.get(parent(j));
            E item = data.get(j);
            while (j > 1 && parent.compareTo(item) < 0) {
                swap(parent(j), j);
                j = parent(j);
                parent = data.get(parent(j));
            }
        }

        public void buildHeap() {
            int half = size / 2;
            for (int i = half; i >= 1 ; i--) {
                siftDown(i);
            }
        }


        public void siftDown(int i){
            int maxIndex = i;
            int l = leftChild(i);
            if (l <= size && data.get(l).compareTo(data.get(maxIndex)) < 0) {
                maxIndex = l;
            }
            int r = rightChild(i);
            if (r <= size && data.get(r).compareTo(data.get(maxIndex)) < 0) {
                maxIndex = r;
            }
            if (i != maxIndex){
                swap(i, maxIndex);
                siftDown(maxIndex);
            }
        }

        public void changePriority(int i, E p){
            E temp = data.get(i);
            data.set(i, p);
            if (p.compareTo(temp) > 0) {
                siftUp(i);
            }
            else {
                siftDown(i);
            }
        }

        public E extractMax(){
            E result = data.get(0);
            E last = data.remove(data.size() - 1);
            data.set(0, last);
            siftDown(0);
            return result;
        }

        public void insert(E p){
            data.add(p);
            size++;
            siftUp(data.size() - 1);
        }

        private void swap(int i, int j){
            // report zero based
            Swap swap = new Swap(i - 1, j - 1);
            swaps.add(swap);

            E temp = data.get(i);
            data.set(i, data.get(j));
            data.set(j, temp);
        }
    }
}
