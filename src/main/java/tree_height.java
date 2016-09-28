import java.util.*;
import java.io.*;

public class tree_height {
    class FastScanner {
		StringTokenizer tok = new StringTokenizer("");
		BufferedReader in;

		FastScanner() {
			in = new BufferedReader(new InputStreamReader(System.in));
		}

		String next() throws IOException {
			while (!tok.hasMoreElements())
				tok = new StringTokenizer(in.readLine());
			return tok.nextToken();
		}
		int nextInt() throws IOException {
			return Integer.parseInt(next());
		}
	}

	public class Tree<E extends Comparable<? super E>> {
		TreeNode<E> root;

		public Tree(TreeNode<E> root) {
			this.root = root;
		}

        public TreeNode<E> getRoot() {
            return root;
        }

        public void setRoot(TreeNode<E> root) {
            this.root = root;
        }

        public int levelOrder(TreeNode<E> node){
			int level = 0;
			Queue<TreeNode<E>> q = new LinkedList<>();
			q.add(node);
			while(!q.isEmpty()){
				int count = q.size();
				while (count > 0) {
					TreeNode<E> nn = q.remove();
					if(nn.hasChildren()) {
                        List<TreeNode<E>> children = nn.getChildren();
                        q.addAll(children);
                    }

					count--;
				}
				if(!q.isEmpty()) {
                    level++;
                }
			}

			return level + 1;
		}
	}

	public class TreeNode<E> {
		private E value;
		private TreeNode<E> parent;
		private List<TreeNode<E>> children;

		public TreeNode(E value, TreeNode<E> parent) {
			this.value = value;
			this.parent = parent;
			children = new LinkedList<>();
		}

		public E getValue() {
			return value;
		}

		public void setValue(E value) {
			this.value = value;
		}

		public TreeNode<E> getParent() {
			return parent;
		}

		public void setParent(TreeNode<E> parent) {
			this.parent = parent;
		}

		public List<TreeNode<E>> getChildren() {
			return children;
		}

		public boolean setChild(TreeNode<E> child) {
			return this.children.add(child);
		}

		public boolean hasChildren() {
			return !children.isEmpty();
		}
	}

	public class TreeHeight {
		int n;
		int parent[];
		
		void read() throws IOException {
			FastScanner in = new FastScanner();
			n = in.nextInt();
			parent = new int[n];
			for (int i = 0; i < n; i++) {
				parent[i] = in.nextInt();
			}
		}

		int computeHeight() {
                        // Replace this code with a faster implementation
			// build our tree
            Tree<Integer> tree = new Tree<>(new TreeNode<>(-1, null));
            List<TreeNode<Integer>> nodes = new ArrayList<>(parent.length);
            for (int i = 0; i < parent.length; i++) {
                nodes.add(null);
            }

            for (int i = 0; i < parent.length; i++) {
                int p = parent[i];
                // get or create our node
                TreeNode<Integer> node = nodes.get(i);
                if (node == null) {
                    node = new TreeNode<>(i, null);
                    nodes.set(i, node);
                }

                // root
                if(p == -1){
                    tree.setRoot(node);
                }
                else {
                    // get or create parent
                    TreeNode<Integer> pnode = nodes.get(p);
                    if(pnode == null) {
                        pnode = new TreeNode<>(p, null);
                        nodes.set(p, pnode);
                    }
                    node.setParent(pnode);
                    pnode.setChild(node);
                }
            }

            return tree.levelOrder(tree.getRoot());
		}
	}

	static public void main(String[] args) throws IOException {
            new Thread(null, new Runnable() {
                    public void run() {
                        try {
                            new tree_height().run();
                        } catch (IOException e) {
                        }
                    }
                }, "1", 1 << 26).start();
	}
	public void run() throws IOException {
		TreeHeight tree = new TreeHeight();
		tree.read();
		System.out.println(tree.computeHeight());
	}
}
