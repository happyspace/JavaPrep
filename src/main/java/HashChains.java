import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class HashChains {

    private FastScanner in;
    private PrintWriter out;
    // store all strings in one list
    private List<String> elems;
    // our hash table
    private List<List<String>> hashTable;
    // for hash function
    private int bucketCount;
    private int prime = 1000000007;
    private int multiplier = 263;

    public static void main(String[] args) throws IOException {
        new HashChains().processQueries();
    }

    private int hashFunc(String s) {
        long hash = 0;
        for (int i = s.length() - 1; i >= 0; --i)
            hash = (hash * multiplier + s.charAt(i)) % prime;
        return (int)hash % bucketCount;
    }

    private Query readQuery() throws IOException {
        String type = in.next();
        if (!type.equals("check")) {
            String s = in.next();
            return new Query(type, s);
        } else {
            int ind = in.nextInt();
            return new Query(type, ind);
        }
    }

    private void writeSearchResult(boolean wasFound) {
        out.println(wasFound ? "yes" : "no");
        // Uncomment the following if you want to play with the program interactively.
        // out.flush();
    }

    public void add(String string){
        int hash = hashFunc(string);
        List<String> list = hashTable.get(hash);
        if(list.isEmpty()){
            list.add(0, string);
        }
        else {
            if(!list.contains(string)){
                list.add(0, string);
            }
        }
    }

    public boolean find(String string) {
        boolean found = false;
        int hash = hashFunc(string);
        List<String> list = hashTable.get(hash);
        if(!list.isEmpty()){
            found = list.contains(string);
        }
        return found;
    }

    public void del(String string){
        int hash = hashFunc(string);
        List<String> list = hashTable.get(hash);
        if(!list.isEmpty()){
            if(list.contains(string)){
                list.remove(string);
            }
        }
    }

    public String check(int i){
        StringBuilder sb = new StringBuilder();
        List<String> list = hashTable.get(i);
        if(!list.isEmpty()){
            for (String s : list) {
                if(sb.length() > 0){
                    sb.append(" ");
                }
                sb.append(s);
            }
        }
        else {
            sb.append("");
        }

        return sb.toString();
    }

    private void processQuery(Query query) {
        switch (query.type) {
            case "add":
                add(query.s);
                //if (!elems.contains(query.s))
                 //   elems.add(0, query.s);
                break;
            case "del":
                del(query.s);
                //if (elems.contains(query.s))
                //    elems.remove(query.s);
                break;
            case "find":
                writeSearchResult(find(query.s));
                // writeSearchResult(elems.contains(query.s));
                break;
            case "check":
                String result = check(query.ind);
                out.print(result);
                // for (String cur : elems)
                //    if (hashFunc(cur) == query.ind)
                //        out.print(cur + " ");
                out.println();
                // Uncomment the following if you want to play with the program interactively.
                // out.flush();
                break;
            default:
                throw new RuntimeException("Unknown query: " + query.type);
        }
    }

    public void processQueries() throws IOException {
        elems = new ArrayList<>();
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        bucketCount = in.nextInt();
        int queryCount = in.nextInt();

        hashTable = new ArrayList<>(bucketCount);
        for (int i = 0; i < bucketCount; i++) {
            hashTable.add(new ArrayList<>());
        }

        for (int i = 0; i < queryCount; ++i) {
            processQuery(readQuery());
        }
        out.close();
    }

    static class Query {
        String type;
        String s;
        int ind;

        public Query(String type, String s) {
            this.type = type;
            this.s = s;
        }

        public Query(String type, int ind) {
            this.type = type;
            this.ind = ind;
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
