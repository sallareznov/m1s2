package algorithms;

import patterns.Genome;
import patterns.Strand;
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
		for (int i = 0; i < next.length; i++) {
			final Strand subStrand = strand.getPrefix(i);
			int j = i - 1;
			boolean found = false;
			Strand u = subStrand;
			while (j >= 0 && !found) {
				u = u.getLongestEdge();
				final Character[] uMiBases = new Character[u.getSize() + 1];
				System.arraycopy(u.getContent(), 0, uMiBases, 0, u.getSize());
				Strand str = null;
				if (i < strand.getSize()) {
					uMiBases[u.getSize()] = strand.getContent()[i];
					str = new Strand(uMiBases, strand.getManager());
				} else {
					str = new Strand(new Character[0], strand.getManager());
				}
				if (!strand.isPrefix(str)) {
					found = true;
				}
				j = u.getSize() - 1;
			}
			next[i] = (found) ? u.getSize() : -1;
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
