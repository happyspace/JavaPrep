import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

public class HashSubstring {

    private static FastScanner in;
    private static PrintWriter out;

    public static void main(String[] args) throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        printOccurrences(getOccurrences(readInput()));
        out.close();
    }

    private static Data readInput() throws IOException {
        String pattern = in.next();
        String text = in.next();
        return new Data(pattern, text);
    }

    private static void printOccurrences(List<Integer> ans) throws IOException {
        for (Integer cur : ans) {
            out.print(cur);
            out.print(" ");
        }
    }

    private static long hashFunc(String s, long prime, int x) {
        long hash = 0;
        for (int i = s.length() - 1; i >= 0; --i)
            hash = (hash * x + s.charAt(i)) % prime;
        return hash;
    }

    private static long[] precomputeHashes(String text, int pLen, long prime, int x){
        long[] hashes = new long[text.length() - pLen + 1];
        String sub = text.substring(text.length() - pLen, text.length());
        // hashes[text.length() - pLen];
        hashes[text.length() - pLen] = hashFunc(sub, prime, x);
        long y = 1;
        for (int i = 0; i < pLen; i++) {
            y = (y * x) % prime;
        }
        int tt = text.length() - pLen - 1;
        for (int i = tt; i >= 0; i--) {
            long hh = (x * hashes[i + 1] + text.charAt(i) - y * text.charAt(i + pLen)) % prime;
            hashes[i] = (hh + prime) % prime;
        }
        return hashes;
    }

    private static long longPrime(){
        BigInteger bi = BigInteger.probablePrime(31, new Random());
        return bi.longValue();
    }

    private static List<Integer> getOccurrences(Data input) {
        String s = input.pattern, t = input.text;
        int m = s.length(), n = t.length();
        List<Integer> occurrences = new ArrayList<Integer>();

        if(m == n){
            if(s.equals(t)){
                occurrences.add(0);
                return occurrences;
            }
        }
        long prime = longPrime();
        int x = 256;
        long pHash = hashFunc(s, prime, x);
        long[] hashes = precomputeHashes(t, m, prime, x);
        // use java's hash
        // int pHash = s.hashCode();
        int diff = n - m;
        for (int i = 0; i <= diff; ++i) {
            long cHash = hashes[i];
            if (cHash == pHash) {
                if (t.substring(i, m + i).equals(s)) {
                    occurrences.add(i);
                }
            }
        }
        return occurrences;
    }

    static class Data {
        String pattern;
        String text;
        public Data(String pattern, String text) {
            this.pattern = pattern;
            this.text = text;
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
}

