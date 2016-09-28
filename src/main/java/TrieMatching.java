import java.io.*;
import java.util.*;

class NodeTM
{
	public static final int Letters =  4;
	public static final int NA      = -1;
	public int next [];

	NodeTM ()
	{
		next = new int [Letters];
		Arrays.fill (next, NA);
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

public class TrieMatching implements Runnable {
	public static final String NO_MATCHES = "";

	int letterToIndex (char letter)
	{
		switch (letter)
		{
			case 'A': return 0;
			case 'C': return 1;
			case 'G': return 2;
			case 'T': return 3;
			default: assert (false); return NodeTM.NA;
		}
	}

	List <Integer> solve (String text, int n, List <String> patterns) {
		List <Integer> result = new ArrayList <Integer> ();
		List<NodeTM> trie = this.buildTrie(patterns);

		for (int i = 0; i < text.length(); i++) {
			String match = this.prefixTrieMatching(text.substring(i), trie);
            if(!match.equals(NO_MATCHES)){
                result.add(i);
            }
		}
		// write your code here

		return result;
	}

	private String prefixTrieMatching(String text, List<NodeTM> trie){
		int i = 1;
	    char c = text.charAt(0);
		NodeTM current = trie.get(0);
        int pointer = current.next[this.letterToIndex(c)];
		StringBuilder sb = new StringBuilder();
		while (true){
			if(current.isLeaf()){
				return sb.toString();
			}
			else if (pointer != NodeTM.NA) {
			    sb.append(c);
                current = trie.get(pointer);
                if(i < text.length()){
                    c = text.charAt(i);
                    pointer = current.next[this.letterToIndex(c)];
                } else {
                    pointer = NodeTM.NA;
                }
			}
			else {
				return NO_MATCHES;
			}
            i += 1;
		}
	}

	private List<NodeTM>  buildTrie(List<String> patterns) {
		List<NodeTM> trie = new ArrayList<>();
		trie.add(new NodeTM());

		for (String pattern : patterns) {
			NodeTM current = trie.get(0);
			for (int i = 0; i < pattern.length(); i++) {
				Character c = pattern.charAt(i);
				int value = current.next[this.letterToIndex(c)];
				if(value != NodeTM.NA){
					current = trie.get(value);
				} else {
					NodeTM nn = new NodeTM();
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
		new Thread (new TrieMatching ()).start ();
	}
}
