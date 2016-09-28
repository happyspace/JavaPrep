import java.io.*;
import java.util.*;

class Node
{
	public static final int Letters =  4;
	public static final int NA      = -1;
	public int next [];
	public boolean patternEnd;

	Node ()
	{
		next = new int [Letters];
		Arrays.fill (next, NA);
		patternEnd = false;
	}

	public boolean isLeaf() {
		for (int i = 0; i < next.length; i++) {
			if(next[i] != NA){
				return false;
			}
		}
		return true;
	}
}

public class TrieMatchingExtended implements Runnable {
    public static final String NO_MATCHES = "";

	int letterToIndex (char letter)
	{
		switch (letter)
		{
			case 'A': return 0;
			case 'C': return 1;
			case 'G': return 2;
			case 'T': return 3;
			default: assert (false); return Node.NA;
		}
	}

	List <Integer> solve (String text, int n, List <String> patterns) {
		List <Integer> result = new ArrayList <Integer> ();

        List<Node> trie = this.buildTrie(patterns);

        for (int i = 0; i < text.length(); i++) {
            List<String> matchs = this.prefixTrieMatching(text.substring(i), trie);
            if(!matchs.isEmpty()){
                result.add(i);
            }
        }

		return result;
	}

    private List<String> prefixTrieMatching(String text, List<Node> trie){
        int i = 1;
        char c = text.charAt(0);
        Node current = trie.get(0);
        int pointer = current.next[this.letterToIndex(c)];
        StringBuilder sb = new StringBuilder();
        List<String> prefices = new ArrayList<>();
        while (true){
            if(current.isLeaf()){
                return prefices;
            }
            else if (pointer != Node.NA) {
                sb.append(c);
                current = trie.get(pointer);
                if(current.patternEnd){
                    prefices.add(sb.toString());
                }

                if(i < text.length()){
                    c = text.charAt(i);
                    pointer = current.next[this.letterToIndex(c)];
                } else {
                    pointer = Node.NA;
                }
            }
            else {
                return prefices;
            }
            i += 1;
        }
    }

    private List<Node>  buildTrie(List<String> patterns) {
        List<Node> trie = new ArrayList<>();
        trie.add(new Node());

        for (String pattern : patterns) {
            Node current = trie.get(0);
            for (int i = 0; i < pattern.length(); i++) {
                Character c = pattern.charAt(i);
                int value = current.next[this.letterToIndex(c)];
                if(value != Node.NA){
                    current = trie.get(value);

                    if(i == pattern.length() - 1){
                        current.patternEnd = true;
                    }
                } else {
                    Node nn = new Node();
                    if(i == pattern.length() - 1){
                        nn.patternEnd = true;
                    }

                    trie.add(nn);
                    current.next[this.letterToIndex(c)] = trie.size() - 1;
                    current = nn;
                }
            }
        }

        return trie;
    }

	public void run () {
		try {
			BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
			String text = in.readLine ();
		 	int n = Integer.parseInt (in.readLine ());
		 	List <String> patterns = new ArrayList <String> ();
			for (int i = 0; i < n; i++) {
				patterns.add (in.readLine ());
			}

			List <Integer> ans = solve (text, n, patterns);

			for (int j = 0; j < ans.size (); j++) {
				System.out.print ("" + ans.get (j));
				System.out.print (j + 1 < ans.size () ? " " : "\n");
			}
		}
		catch (Throwable e) {
			e.printStackTrace ();
			System.exit (1);
		}
	}

	public static void main (String [] args) {
		new Thread (new TrieMatchingExtended ()).start ();
	}
}
