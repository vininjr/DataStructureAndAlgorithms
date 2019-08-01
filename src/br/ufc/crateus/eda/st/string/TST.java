package br.ufc.crateus.eda.st.string;

import java.util.LinkedList;
import java.util.Queue;

public class TST<V> implements StringST<V> {

	private Node root;
	private int size = 0;

	public class Node {
		char ch;
		V value;
		Node left, mid, right;

		public Node(char ch) {
			this.ch = ch;
		}
	}

	@Override
	public V get(String key) {
		Node node = get(root, key, 0);
		return (node != null) ? node.value : null;
	}

	private Node get(Node r, String key, int i) {
		if (r == null)
			return null;
		char ch = key.charAt(i);
		if (ch < r.ch)
			return get(r.left, key, i);
		if (ch > r.ch)
			return get(r.right, key, i);
		if (i == key.length() - 1)
			return r;
		return get(r.mid, key, i + 1);
	}

	@Override
	public void put(String key, V value) {
		root = put(root, key, value, 0);
		size++;
	}

	private Node put(Node r, String key, V value, int i) {
		char ch = key.charAt(i);
		if (r == null)
			r = new Node(ch);
		if (ch < r.ch)
			r.left = put(r.left, key, value, i);
		else if (ch > r.ch)
			r.right = put(r.right, key, value, i);
		else if (i == key.length() - 1)
			r.value = value;
		else
			r.mid = put(r.mid, key, value, i + 1);
		return r;
	}

	@Override
	public boolean contains(String key) {
		return get(key) != null;
	}

	@Override
	public boolean isEmpty() {
		return root.mid == null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	public String longestPrefixOf(String query) {

		if (query.length() == 0)
			return null;
		int length = 0;
		Node x = root;
		int i = 0;
		while (x != null && i < query.length()) {
			char c = query.charAt(i);
			if (c < x.ch)
				x = x.left;
			else if (c > x.ch)
				x = x.right;
			else {
				i++;
				if (x.value != null)
					length = i;
				x = x.mid;
			}
		}
		return query.substring(0, length);
	}

	public Iterable<String> keysWithPrefix(String prefix) {

		Queue<String> queue = new LinkedList<>();
		Node x = get(root, prefix, 0);
		if (x == null)
			return queue;
		if (x.value != null)
			queue.add(prefix);
		collection(x.mid, prefix, queue);
		return queue;
	}

	private void collection(Node x, String prefix, Queue<String> queue) {
		if (x == null)
			return;
		collect(x.left, prefix, queue);
		if (x.value != null)
			queue.add(prefix + x.ch);
		collect(x.mid, prefix + x.ch, queue);
		if (!prefix.isEmpty())
			prefix = prefix.substring(0, prefix.length() - 1);
		collect(x.right, prefix, queue);
	}

	public Iterable<String> keysThatMatch(String pattern) {
		Queue<String> queue = new LinkedList<>();
		collect(root, "", 0, pattern, queue);
		return queue;
	}

	private void collect(Node x, String prefix, int i, String pattern, Queue<String> queue) {
		if (x == null)
			return;
		char c = pattern.charAt(i);
		if (c == '.' || c < x.ch)
			collect(x.left, prefix, i, pattern, queue);
		if (c == '.' || c == x.ch) {
			if (i == pattern.length() - 1 && x.value != null)
				queue.add(prefix + x.ch);
			if (i < pattern.length() - 1) {
				collect(x.mid, prefix + x.ch, i + 1, pattern, queue);
				if (!prefix.isEmpty())
					prefix = prefix.substring(0, prefix.length() - 1);
			}
		}
		if (c == '.' || c > x.ch)
			collect(x.right, prefix, i, pattern, queue);
	}

	private void collect(Node r, String prefix, Queue<String> queue) {
		if (r != null) {
			if (r.value != null) {
				queue.add(prefix + r.ch);
			}
			collect(r.left, prefix, queue);
			collect(r.mid, prefix + r.ch, queue);
			collect(r.right, prefix, queue);
		}
	}

	@Override
	public Iterable<String> keys() {
		Queue<String> queue = new LinkedList<>();
		collect(root, "", queue);
		return queue;
	}

	private Node collect(Node r, String key, String aux, int i) {
		// System.out.println(aux);
		if (r == null)
			return null;

		char ch = key.charAt(i);

		if (r.value != null || (i == key.length() - 1))
			return r;

		if (ch < r.ch) {
			return collect(r.left, key, aux, i);
		}
		if (ch > r.ch) {
			return collect(r.right, key, aux, i);
		}

		return collect(r.mid, key, aux + r.ch, i + 1);

	}

	public Node floor(String prefix) {

		return collect(root.right, prefix, "", 0);
	}

	public int rank(String key) {
		return rank(root, key, 0);
	}

	private int rank(Node n, String key, int i) {
		if (n == null)
			return 0;
		if (i == key.length())
			return 0;

		char ch = key.charAt(i);

		if (ch > n.ch)
			if (n.value != null) {
				return 1 + rank(n.left, key, i + 1);
			} else {
				return rank(n.left, key, i + 1);
			}
		if (ch < n.ch) {
			return rank(n.right, key, i + 1);
		}
		return rank(n.mid, key, i + 1);
	}

	public void delete(String key) {
		root = delete(root, key, 0);
	}

	private Node delete(Node x, String key, int d) {
		if (x == null)
			return null;
		if (d == key.length() - 1) {
			if (x.value != null)
				x.value = null;
			return x;
		} else {
			char c = key.charAt(d);

			if (c < x.ch)
				x.left = delete(x.left, key, d);
			if (c > x.ch)
				x.right = delete(x.right, key, d);
			if (c == x.ch)
				x.mid = delete(x.mid, key, d + 1);
		}

		return x;

	}

	public static void main(String[] args) {

		TST<String> tst = new TST<>();

		tst.put("joao", "30");
		tst.put("joao paulo", "10");
		tst.put("paulo", "20");
		tst.put("marlon", "24");
		tst.put("marcus", "40");
		tst.put("paulinho", "50");
		tst.put("joaozinho", "60");
		tst.put("marquinhos", "70");
		tst.put("maria", "80");
		tst.put("mariana", "90");

		// System.out.println(tst.keysThatMatch("...o"));
		// tst.floor("joao paulo");
		// System.out.println(tst.floor("marlon").value);
		// System.out.println(tst.rank("joao"));
		// System.out.println(tst.get("marquinhos"));
		// tst.delete("marquinhos");
		// System.out.println(tst.get("marcus"));
		// System.out.println(tst.get("marquinhos"));
	}

}
