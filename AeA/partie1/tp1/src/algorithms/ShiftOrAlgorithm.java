package algorithms;

import java.util.HashMap;
import java.util.Map;

import patterns.Alphabet;
import patterns.Genome;
import patterns.Strand;
import algorithms.util.StrandOccurences;
import bases.Base;

/**
 * Classe representant l'algorithme ShiftOr
 */
public class ShiftOrAlgorithm extends Algorithm {

	private Map<Character, Integer[]> lettersVectors;
	private int[][] matrix;

	/**
	 * construit l'algorithme ShiftOr
	 * 
	 * @param genome
	 *            le genome sur lequel s'effectuera la recherche
	 */
	public ShiftOrAlgorithm(Genome genome) {
		super(genome);
		lettersVectors = new HashMap<Character, Integer[]>();
	}

	/**
	 * pre-traite l'algorithme en initialisant les vecteurs des lettres de
	 * l'alphabet
	 * 
	 * @param strand
	 *            le brin a rechercher
	 * @param genome
	 *            le genome
	 */
	private void preTreat(Strand strand) {
		lettersVectors.clear();
		final String strandString = strand.toString();
		final Alphabet alphabet = getGenome().getAlphabet();
		final Character[] letters = alphabet.getLetters();
		for (int i = 0; i < alphabet.getSize(); i++) {
			final char letter = letters[i];
			final Integer[] currentLetterVector = new Integer[strandString
					.length()];
			for (int j = 0; j < strandString.length(); j++) {
				currentLetterVector[j] = (strandString.charAt(j) == letter) ? 1
						: 0;
			}
			lettersVectors.put(letter, currentLetterVector);
		}
	}

	/**
	 * initialise la matrice B
	 * 
	 * @param strandBases
	 *            le tableau de bases du brin a rechercher
	 * @param genomeBases
	 *            le tableau de bases du genome
	 */
	private void initMatrix(Base[] strandBases, Base[] genomeBases) {
		matrix = new int[genomeBases.length][strandBases.length];
		for (int i = 0; i < strandBases.length; i++) {
			if (i == 0 && strandBases[i].equals(genomeBases[i]))
				matrix[0][i] = 1;
			else
				matrix[0][i] = 0;
		}
	}

	/**
	 * calcule la prochaine colonne et met a jour la matrice
	 * 
	 * @param nbColumn
	 *            l'indice de la prochaine colonne
	 * @param currentColumn
	 *            la colonne courante
	 * @param genomeBase
	 *            la base du genome etudie
	 * @param strandSize
	 *            la taille du brin
	 */
	private void calculateNextStep(int nbColumn, int[] currentColumn,
			Base genomeBase, int strandSize) {
		for (int i = currentColumn.length - 1; i >= 1; i--) {
			currentColumn[i] = currentColumn[i - 1];
		}
		currentColumn[0] = 1;
		final Integer[] letterVector = lettersVectors.get(genomeBase
				.getWording());
		for (int i = 0; i < currentColumn.length; i++) {
			currentColumn[i] &= letterVector[i];
		}
		for (int i = 0; i < strandSize; i++) {
			matrix[nbColumn][i] = currentColumn[i];
		}
	}

	/**
	 * recupere les occurences en parcourant la derniere ligne de la matrice
	 * 
	 * @param strandBases
	 * @param genomeBases
	 * @return les occurences trouvees
	 */
	private StrandOccurences retrieveOccurences(Base[] strandBases,
			Base[] genomeBases) {
		final StrandOccurences strandOccurences = new StrandOccurences();
		for (int i = 0; i < genomeBases.length; i++) {
			if (matrix[i][strandBases.length - 1] == 1)
				strandOccurences.addIndex(i - strandBases.length + 1);
		}
		return strandOccurences;
	}

	@Override
	public StrandOccurences findRepetitiveStrand(Strand strand) {
		preTreat(strand);
		final Base[] genomeBases = getGenome().getBases();
		final Base[] strandBases = strand.getContent();
		initMatrix(strandBases, genomeBases);
		int[] currentColumn = matrix[0];
		for (int i = 1; i < genomeBases.length; i++) {
			final Base currentBaseOfTheGenome = genomeBases[i];
			calculateNextStep(i, currentColumn, currentBaseOfTheGenome,
					strandBases.length);
		}
		return retrieveOccurences(strandBases, genomeBases);
	}
	
	@Override
	public String toString() {
		return "Algorithme ShiftOr";
	}

}
