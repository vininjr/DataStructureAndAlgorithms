package br.ufc.crateus.eda.lista.q2;

import br.ufc.crateus.eda.st.hashing.SeparateChainingHashingST;

public class App {
	public static void main(String[] args) {
		Polinomios p1 = new Polinomios();
		Polinomios p2 = new Polinomios();
		p1.startPolinomio(2);
		p2.startPolinomio(2);
		Multiplica(p1, p2);

	}

	public static void Multiplica(Polinomios po1, Polinomios po2) {
		SeparateChainingHashingST<Integer, Integer> h = new SeparateChainingHashingST<>(po1.size * po2.size);
		int value, key, x;
		Node aux1, aux2;
		aux1 = po1.root;
		aux2 = po2.root;
		for (int i = 0; i < po1.size && aux1 != null; i++) {
			for (int j = 0; j < po2.size && aux2 != null; j++) {
				value = aux1.base * aux2.base;
				key = aux1.expoente + aux2.expoente;
				if (h.get(key) != null) {
					x = h.get(key);
					x = x + value;
					h.put(key, x);

				} else {
					h.put(key, value);

				}
				x = h.get(key);
				aux2 = aux2.seg;

			}
			aux1 = aux1.seg;
			aux2 = po2.root;
		}

		h.ToString();

	}

}
