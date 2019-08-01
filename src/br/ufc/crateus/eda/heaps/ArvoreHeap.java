package br.ufc.crateus.eda.heaps;

import java.util.ArrayList;

public class ArvoreHeap {

	private ArrayList<Integer> array = new ArrayList<>();

	public ArvoreHeap() {
		array.add(0);
	}

	public void printerFamily(int index) {

		for (int i = index; i < array.size(); i++) {
			System.out.println("filhos de " + array.get(i));
			if (2 * i < array.size())
				System.out.println(array.get(2 * i));
			if (2 * i + 1 < array.size())
				System.out.println(array.get((2 * i) + 1));
		}
	}

	private void swap(int j, int p) {
		int aux = array.get(j);
		array.set(j, array.get(p));
		array.set(p, aux);

	}

	public void sinkDown(int i) {
		if (2 * i < array.size() && array.get(i) > array.get(2 * i)) {
			if (2 * i + 1 < array.size() && array.get(2 * i + 1) < array.get(2 * i)) {
				swap(i, 2 * i + 1);
				sinkDown(2 * i + 1);
			} else {
				swap(i, 2 * i);
				sinkDown(2 * i);
			}
		}
		if (2 * i + 1 < array.size() && array.get(i) > array.get(2 * i + 1)) {
			swap(i, 2 * i + 1);
			sinkDown(2 * i + 1);
		} else
			return;
	}

	private void swimUp(int i) {
		if (array.get(i) < array.get(i / 2)) {
			swap(i, i / 2);
			swimUp(i / 2);
		} else
			return;
	}

	public void insert(Integer c) {
		array.add(c);
		swimUp(array.size() - 1);
	}

	public void printer() {
		int i = 1;
		for (i = 1; i < array.size(); i++) {
			System.out.print(array.get(i));
			if (!(i == array.size() - 1))
				System.out.print(", ");
		}
	}

	public void extraticMin() {
		array.set(1, array.get(array.size() - 1));
		array.remove(array.size() - 1);
		sinkDown(1);
	}

	public void delete(int i) {
		array.remove(i);
		sinkDown(i / 2);
		if ((array.size() - 1) > (2 * i))
			swimUp(2 * i);
	}

	public void decrementIndex(int i, int d) {
		array.set(i, array.get(i) - d);
		sinkDown(i / 2);
	}

	public int findMin() {
		return array.get(1);
	}

	public void meld(ArvoreHeap h2) {
		for (int i = 1; i < h2.array.size(); i++)
			insert(h2.array.get(i));
	}

	public void makeheapLinear(ArrayList<Integer> lst) {
		this.array = lst;
		for (int i = array.size() / 2; i > 0; i--) {
			sinkDown(i);
		}
	}

	public Boolean isEmpty() {
		return (array.size() == 1) ? true : false;
	}

	public static void main(String[] args) {
		ArvoreHeap h = new ArvoreHeap();
		h.insert(1);
		h.insert(2);
		h.insert(3);
		h.insert(4);
		h.insert(5);
		System.out.println(h.isEmpty());
		System.out.println("insere Heap");
		h.printer();
		System.out.println();
		System.out.println();
		System.out.println("Extrair minimo");
		h.extraticMin();
		h.printer();
		System.out.println();
		System.out.println();
		System.out.println("delete index");
		h.delete(2);
		h.printer();

	}

}
