package br.ufc.crateus.eda.lista.q1;

import br.ufc.crateus.eda.st.hashing.LinearProbingST;
import br.ufc.crateus.eda.st.hashing.SeparateChainingHashingST;

public class App {

	public static void main(String[] args) {

		LinearProbingST<Integer, String> hst = new LinearProbingST<>(9);
		SeparateChainingHashingST<Integer, String> sst = new SeparateChainingHashingST<>(9);

		hst.put(5, "joao");
		hst.put(28, "marcus");
		hst.put(19, "rafael");
		hst.put(15, "roger");
		hst.put(20, "ronaldinho garrancho");
		hst.put(33, "akon");
		hst.put(12, "wemerson");
		hst.put(17, "davi");
		hst.put(10, "Matheus");
		sst.put(5, "joao");
		sst.put(28, "marcus");
		sst.put(19, "rafael");
		sst.put(15, "roger");
		sst.put(20, "ronaldinho garrancho");
		sst.put(33, "akon");
		sst.put(12, "wemerson");
		sst.put(17, "davi");
		sst.put(10, "Matheus");

	}
}
