package lettre;

import java.util.AbstractMap;
import java.util.Map.Entry;

public class LevenshteinBuilder {
	
	/**
	 * renvoie le nombre de suppressions et de substitutions faites pour aller
	 * d'un mot à l'autre
	 * 
	 * @param u
	 *            le mot de départ
	 * @param v
	 *            le mot d'arrivée
	 * @return une association clé-valeur représentant le nombre de suppressions
	 *         et de substitutions
	 */
	public Entry<Integer, Integer> getNbreOperations(String u, String v) {
		int nbSup = 0;
		int nbDif = 0;
		final int n = u.length();
		final int m = v.length();
		final int[][] table = Graphe.construireTableLevenshtein(u, v);
		int i = n;
		int j = m;
		if (u.equals(v)) {
			return new AbstractMap.SimpleImmutableEntry<Integer, Integer>(0,
					1);
		}
		while (table[i][j] != 0 || i != 0 || j != 0) {
			if (i == 0) {
				j--;
			} else {
				if (j == 0) {
					nbSup++;
					i--;
				} else {
					if (u.charAt(i - 1) == v.charAt(j - 1)) {
						i--;
						j--;
					} else {
						if (table[i][j] == 1 + table[i][j - 1]) {
							j--;
						} else {
							if (table[i][j] == 1 + table[i - 1][j]) {
								nbSup++;
								i--;
							} else {
								nbDif++;
								i--;
								j--;
							}
						}
					}
				}
			}
		}
		return new AbstractMap.SimpleImmutableEntry<Integer, Integer>(nbSup,
				nbDif);
	}

}
