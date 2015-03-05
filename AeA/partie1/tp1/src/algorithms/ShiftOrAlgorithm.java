package algorithms;

import java.util.HashMap;
import java.util.Map;

import pattern.Genome;
import pattern.Strand;
import algorithms.util.StrandOccurences;
import bases.Alphabet;

/**
 * Classe representant l'algorithme ShiftOr
 */
public class ShiftOrAlgorithm extends Algorithm {

	// les vecteurs des lettres de l'alphabet
	private Map<Character, Boolean[]> lettersVectors;
	// la matrice finale
	private boolean[][] matrix;

	/**
	 * construit l'algorithme ShiftOr
	 * 
	 * @param genome
	 *            le genome sur lequel s'effectuera la recherche
	 */
	public ShiftOrAlgorithm() {
		super();
		lettersVectors = new HashMap<Character, Boolean[]>();
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
	private void preTreat(Genome genome, Strand strand, Alphabet alphabet) {
		lettersVectors.clear();
		final String strandString = strand.toString();
		final Character[] letters = alphabet.getBases();
		for (int i = 0; i < alphabet.size(); i++) {
			final char letter = letters[i];
			final Boolean[] currentLetterVector = new Boolean[strandString
					.length()];
			for (int j = 0; j < strandString.length(); j++) {
				currentLetterVector[j] = (strandString.charAt(j) == letter) ? true
						: false;
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
	private void initMatrix(Character[] strandBases, Character[] genomeBases) {
		matrix = new boolean[genomeBases.length][strandBases.length];
		for (int i = 0; i < strandBases.length; i++) {
			if (i == 0 && (strandBases[i] == genomeBases[i]))
				matrix[0][i] = true;
			else
				matrix[0][i] = false;
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
	private void calculateNextStep(int nbColumn, boolean[] currentColumn,
			char genomeBase, int strandSize) {
		for (int i = currentColumn.length - 1; i >= 1; i--) {
			currentColumn[i] = currentColumn[i - 1];
		}
		currentColumn[0] = true;
		final Boolean[] letterVector = lettersVectors.get(genomeBase);
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
	private StrandOccurences retrieveOccurences(Character[] strandBases,
			Character[] genomeBases) {
		final StrandOccurences strandOccurences = new StrandOccurences();
		for (int i = strandBases.length - 1; i < genomeBases.length; i++) {
			if (matrix[i][strandBases.length - 1] == true)
				strandOccurences.addIndex(i - strandBases.length + 1);
		}
		return strandOccurences;
	}

	@Override
	public StrandOccurences findRepetitiveStrand(Genome genome, Strand strand, Alphabet alphabet) {
		preTreat(genome, strand, alphabet);
		resetNbComparisons();
		final Character[] genomeBases = genome.getContent();
		final Character[] strandBases = strand.getContent();
		initMatrix(strandBases, genomeBases);
		boolean[] currentColumn = matrix[0];
		for (int i = 1; i < genomeBases.length; i++) {
			final char currentBaseOfTheGenome = genomeBases[i];
			calculateNextStep(i, currentColumn, currentBaseOfTheGenome,
					strandBases.length);
			incrNbComparisons();
		}
		return retrieveOccurences(strandBases, genomeBases);
	}

	@Override
	public String toString() {
		return "Algorithme ShiftOr";
	}

}
