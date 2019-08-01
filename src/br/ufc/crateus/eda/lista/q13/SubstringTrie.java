package br.ufc.crateus.eda.lista.q13;

import br.ufc.crateus.eda.st.string.TST;

public class SubstringTrie {
	private static int count = 0;

	private static TST<String> split(String A, int L) {
		TST<String> tst = new TST<>();
		for (int i = 0; i < A.length() - L + 1; i++) {
			String aux = A.substring(i, i + L);
			if (!tst.contains(aux)) {
				tst.put(aux, String.valueOf(i));
			} else {
				count++;
			}
		}

		return tst;
	}

	private static int countSubs(String A, int L) {
		count = 0;
		split(A, L);
		return count;
	}

	public static void main(String[] args) {
		TST<String> tst = new TST<>();

		tst = split("cgcgggcgcg", 3);

		//System.out.println(tst.keys());
		System.out.println(tst.size());
		System.out.println(countSubs("gcgggccgcgc", 3));

	}

}
