package br.ufc.crateus.eda.st.hashing;

import br.ufc.crateus.eda.st.ST;

public class LinearProbingST<K, V> implements ST<K, V> {

	private K[] keys;
	private V[] values;
	private int m;

	@SuppressWarnings("unchecked")
	public LinearProbingST(int m) {
		this.keys = (K[]) new Object[m];
		this.values = (V[]) new Object[m];
		this.m = m;
	}

	private int hash(K key) {
		return (key.hashCode() & 0x7fffffff) % m;
	}

	@Override
	public V get(K key) {
		for (int i = hash(key); keys[i] != null; i = (i + 1) % m)
			if (key.equals(keys[i]))
				return values[i];
		return null;
	}

	@Override
	public void put(K key, V value) {
		int i = hash(key);
		while (keys[i] != null) {
			if (key.equals(keys[i])) {
				values[i] = value;
				return;
			}
			i = (i + 1) % m;
		}

		keys[i] = key;
		values[i] = value;
	}

	@Override
	public boolean contains(K key) {
		return get(key) != null;
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Iterable<K> keys() {
		// TODO Auto-generated method stub
		return null;
	}

}
