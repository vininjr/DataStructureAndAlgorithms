package br.ufc.crateus.eda.st.hashing;

import br.ufc.crateus.eda.st.ST;

public class SeparateChainingHashingST<K, V> implements ST<K, V> {

	private Node[] array;
	private int m;
	private int size;

	private static class Node {
		Object key;
		Object value;
		Node next;

		Node(Object key, Object value, Node next) {
			this.key = key;
			this.value = value;
			this.next = next;
		}
	}

	public SeparateChainingHashingST(int m) {
		this.array = new Node[m];
		this.m = m;
		this.size = 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public V get(K key) {
		int i = hash(key);
		for (Node n = array[i]; n != null; n = n.next)
			if (key.equals(n.key))
				return (V) n.value;
		return null;
	}

	@Override
	public void put(K key, V value) {
		if ((size / m) >= 8)
			rehashing(2 * m);

		int i = hash(key);
		for (Node n = array[i]; n != null; n = n.next)
			if (key.equals(n.key)) {
				n.value = value;
				return;
			}
		array[i] = new Node(key, value, array[i]);
		size++;
	}

	@SuppressWarnings("unchecked")
	private void rehashing(int newM) {
		SeparateChainingHashingST<K, V> hash = new SeparateChainingHashingST<>(newM);
		for (Node h : array) {
			for (Node node = h; node != null; node = node.next)
				hash.put((K) node.key, (V) node.value);
		}
		array = hash.array;
		m = newM;
	}

	@Override
	public boolean contains(K key) {
		// TODO Auto-generated method stub
		return get(key) != null;
	}

	public void ToString() {
		for (int i = 0; i < m; i++) {
			for (Node n = array[i]; n != null; n = n.next) {
				if ((int) n.value > 0) {
					System.out.print(" + ");
				}
				System.out.print(n.value + "^" + n.key + " ");
			}
		}
		System.out.println("  Fim !!!");
	}

	@Override
	public void delete(K key) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public Iterable<K> keys() {
		// TODO Auto-generated method stub
		return null;
	}

	private int hash(K key) {
		return (key.hashCode() & 0x7fffffff) % m;
	}
}
