package algorithms;

import pattern.Genome;
import pattern.Strand;
import algorithms.util.StrandOccurences;
import bases.Alphabet;

/**
 * Algorithme de Knuth, Morris et Pratt
 */
public class KMPAlgorithm extends Algorithm {

	// Chaque case contient la longueur du plus long mot <i>u</i> possible tel
	// que <i>u</i> est un bord de M(0..i-1) | M étant le motif et uM(i) n'est
	// pas un préfixe de M next[i] = -1 si un tel <i>u</i> n'existe pas
	private int[] next;

	/**
	 * pretraitement de l'algorithme
	 * 
	 * @param strand
	 *            le brin
	 */
	private void preTreat(Strand strand) {
		next = new int[strand.getSize() + 1];
		int i = 0;
		int j = -1;
		next[0] = -1;
		char c = 0;
		while (i < strand.getSize()) {
			if (c == strand.getBaseAt(i)) {
				next[i + 1] = j + 1;
				i++;
				j++;
			}
			else {
				if (j > 0) {
					j = next[j];
				}
				else {
					next[i + 1] = 0;
					i++;
					j = 0;
				}
			}
			c = strand.getBaseAt(j);
		}
	}

	@Override
	public StrandOccurences findRepetitiveStrand(Genome genome, Strand strand, Alphabet alphabet) {
		preTreat(strand);
		resetNbComparisons();
		final StrandOccurences strandOccurences = new StrandOccurences();
		final Character[] genomeBases = genome.getContent();
		final Character[] strandBases = strand.getContent();
		int i = 0;
		while (i < genomeBases.length - strand.getSize() + 1) {
			int j = 0;
			while (j < strand.getSize()
					&& (genomeBases[j + i] == strandBases[j])) {
				j++;
				incrNbComparisons();
			}
			if (j == strand.getSize()) {
				strandOccurences.addIndex(i);
				i++;
			} else {
				i += (j - next[j]);
			}
		}
		return strandOccurences;
	}

	@Override
	public String toString() {
		return "Algorithme de Knuth-Morris-Pratt";
	}

}
