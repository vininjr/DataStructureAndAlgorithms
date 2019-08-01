package br.ufc.crateus.eda.lista.q2;

import java.util.Scanner;

public class Polinomios {
	Scanner scan = new Scanner(System.in);

	Node root = null;
	int size;

	private void addPolinomio(int b, int e) {
		Node novo = new Node();
		novo.base = b;
		novo.expoente = e;
		novo.seg = root;
		root = novo;
		size++;
	}

	public void startPolinomio(int size) {
		int b, e, i;
		for (i = 0; i < size; i++) {
			System.out.println("informe a base: ");
			b = scan.nextInt();
			System.out.println("informe o expoente: ");
			e = scan.nextInt();
			addPolinomio(b, e);
		}
	}

	public void get() {
		Node aux = root;
		while (aux != null) {
			System.out.println("base: " + aux.base);
			System.out.println("expoente: " + aux.expoente);
			System.out.println("---------");
			aux = aux.seg;
		}
	}

}
