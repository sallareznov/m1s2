package main.util;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import patterns.Alphabet;
import patterns.ConcreteStrand;
import patterns.Genome;
import patterns.Strand;
import reader.FastaFileReader;
import reader.util.InvalidFastaFileException;
import reader.util.NotAFastaFileException;
import algorithms.Algorithm;
import algorithms.BoyerMooreAlgorithm;
import algorithms.BruteForceAlgorithm;
import algorithms.KMPAlgorithm;
import algorithms.KarpRabinAlgorithm;
import algorithms.ShiftOrAlgorithm;
import bases.Base;
import bases.BaseFlyweightFactory;

/**
 * Classe se chargeant de parser la ligne de commande de l'utilisateur
 */
public class CommandLineParser {

	private static final String HELP_FLAG = "--HELP";
	private static final String STRANDS_FLAG = "--WITH";
	private static final String ALGORITHMS_FLAG = "--USING";
	private static final Algorithm DEFAULT_ALGORITHM = new BoyerMooreAlgorithm();
//	private static final String[] VALID_STRANDS_OPTIONS = { "-comp", "-rev",
//			"-revComp" };
//	private static final String[] VALID_ALGORITHMS_OPTIONS = { "-bf", "-so",
//			"-kr", "-kmp", "-bm" };
	private String[] commandLine;
	private Genome genome;
	private Strand mainStrand;
	private List<Strand> strandsToLookFor;
	private List<Algorithm> algorithmsToUse;

	/**
	 * construit le parser
	 * @param args le tableau d'arguments
	 * @throws IOException
	 * @throws InvalidFastaFileException
	 * @throws NotAFastaFileException
	 */
	public CommandLineParser(String[] args) throws IOException,
			InvalidFastaFileException, NotAFastaFileException {
		commandLine = args;
		strandsToLookFor = new LinkedList<Strand>();
		algorithmsToUse = new LinkedList<Algorithm>();
	}

	/**
	 * initialise le genome a partir du fichier entre par l'utilisateur
	 * @throws IOException si erreur de lecture
	 * @throws InvalidFastaFileException si le fichier n'a pas un contenu correspondant a la norme fasta
	 * @throws NotAFastaFileException si le fichier n'est pas un fichier fasta
	 */
	public void initGenome() throws IOException, InvalidFastaFileException,
			NotAFastaFileException {
		final FastaFileReader reader = new FastaFileReader();
		genome = reader.getGenomeFromFile(commandLine[0]);
	}

	/**
	 * initialise le brin
	 */
	public void initStrand() {
		mainStrand = new ConcreteStrand(commandLine[1], genome.getAlphabet());
		strandsToLookFor.add(mainStrand);
	}

	/**
	 * @return la liste des brins a rechercher
	 */
	public List<Strand> getStrandsToLookFor() {
		return strandsToLookFor;
	}

	/**
	 * la liste des algorithmes a utiliser 
	 */
	public List<Algorithm> getAlgorithmsToUse() {
		return algorithmsToUse;
	}

//	private static boolean isAStrandOption(String option) {
//		for (int i = 0; i < VALID_STRANDS_OPTIONS.length; i++) {
//			if (option.equals(VALID_STRANDS_OPTIONS[i]))
//				return true;
//		}
//		return false;
//	}
//
//	private static boolean isAnAlgorithmOption(String option) {
//		for (int i = 0; i < VALID_ALGORITHMS_OPTIONS.length; i++) {
//			if (option.equals(VALID_ALGORITHMS_OPTIONS[i]))
//				return true;
//		}
//		return false;
//	}

	private static boolean isAnOption(String option) {
		return (option.charAt(0) == '-' && option.charAt(1) != '-');
	}

	public int verifyStrandsOptions(int currentIndex) {
		int i = currentIndex;
		while (i < commandLine.length && isAnOption(commandLine[i])) {
			final String option = commandLine[i];
			if (option.equals("-comp")) {
				strandsToLookFor.add(mainStrand.getComplementary());
			} else {
				if (option.equals("-rev")) {
					strandsToLookFor.add(mainStrand.getReverse());
				} else {
					if (option.equals("-revComp")) {
						strandsToLookFor.add(mainStrand
								.getReverseComplementary());
					}
				}
			}
			i++;
		}
		return i;
	}
	
	public Genome getGenome() {
		return genome;
	}
	
	public static Strand nextStrand(Strand currentStrand, Alphabet alphabet) {
		Base[] nextStrandContent = currentStrand.getContent();
		int currentIndex = nextStrandContent.length - 1;
		System.out.println("currentIndex = " + currentIndex);
		boolean done = false;
		final BaseFlyweightFactory baseFactory = new BaseFlyweightFactory(alphabet);
		while ((currentIndex >= 0) && (!done)) {
			final char currentLetter = nextStrandContent[currentIndex].getWording();
			final int currentValue = alphabet.getIndex(currentLetter);
			if (currentValue < (alphabet.getSize() - 1)) {
				final char nextLetter = alphabet.getLetters()[currentValue + 1];
				nextStrandContent[currentIndex] = baseFactory.createBase(nextLetter);
				done = true;
			}
			else {
				nextStrandContent[currentIndex] = baseFactory.createBase(alphabet.getLetters()[0]);
				currentIndex--;
			}
		}
		if (currentIndex >= 0) {
			return new ConcreteStrand(nextStrandContent);
		}
		else {
			return null;
		}
	}
		
//		let certificat_suivant certificat k =
//				let donnees = certificat.donnees
//				in
//				let indice = ref (Array.length donnees - 1) and fait = ref false
//				in
//				while ((!indice >= 0) && (!fait = false)) do
//				if (donnees.(!indice) < (k - 1)) then
//					begin
//						donnees.(!indice) <- donnees.(!indice) + 1;
//					fait := true;
//					end
//				else
//					begin
//						donnees.(!indice) <- 0;
//						indice := !indice - 1;
//					end
//				done;
//				if (!indice < 0) then
//				begin
//				reset_certificat certificat;
//				raise Pas_de_certificat_suivant
//				end
		
	
	
	public static List<Strand> getAllStrands(int n, Alphabet alphabet) {
		final List<Strand> allStrands = new LinkedList<Strand>();
		char[] firstStrandArray = new char[n];
		Arrays.fill(firstStrandArray, alphabet.getLetters()[0]);
		String firstStrandString = new String(firstStrandArray);
		allStrands.add(new ConcreteStrand(firstStrandString, alphabet));
		Strand currentStrand = allStrands.get(0);
		while ((currentStrand = nextStrand(currentStrand, alphabet)) != null) {
			allStrands.add(currentStrand);
		}
		return allStrands;
	}

	public boolean parseCommandLine() throws IOException,
			InvalidFastaFileException, NotAFastaFileException {
		if ((commandLine.length < 3) || ((commandLine.length > 0) && (commandLine[0].equals(HELP_FLAG)))) {
			return false;
		}
		initGenome();
		try {
			// TODO MOTS DE TAILLE N
			Integer.parseInt(commandLine[1]);
		} catch (NumberFormatException e) {
			initStrand();
		}
		int currentIndex = 2;
		while (currentIndex < commandLine.length) {
			if (commandLine[currentIndex].equals(STRANDS_FLAG)) {
				currentIndex = verifyStrandsOptions(currentIndex + 1);
			} else {
				if (commandLine[currentIndex].equals(ALGORITHMS_FLAG)) {
					currentIndex = verifyAlgorithmsOptions(currentIndex + 1);
				}
				else {
					currentIndex++;
				}
			}
		}
		return true;
	}

	private int verifyAlgorithmsOptions(int currentIndex) {
		int i = currentIndex;
		while (i < commandLine.length && isAnOption(commandLine[i])) {
			final String option = commandLine[i];
			if (option.equals("-bf")) {
				algorithmsToUse.add(new BruteForceAlgorithm());
			} else {
				if (option.equals("-so")) {
					algorithmsToUse.add(new ShiftOrAlgorithm());
				} else {
					if (option.equals("-kr")) {
						algorithmsToUse.add(new KarpRabinAlgorithm());
					} else {
						if (option.equals("-kmp")) {
							algorithmsToUse.add(new KMPAlgorithm());
						} else {
							if (option.equals("-bm")) {
								algorithmsToUse.add(new BoyerMooreAlgorithm(
										));
							}
						}
					}
				}
			}
			i++;
		}
		// Aucun algorithme n'a ete choisi
		if (algorithmsToUse.isEmpty()) {
			algorithmsToUse.add(DEFAULT_ALGORITHM);
		}
		return i;
	}
	
	public static void main(String[] args) {
		List<Strand> hello = CommandLineParser.getAllStrands(4, Alphabet.DEFAULT_ALPHABET);
		System.out.println(hello.size());
		System.out.println(hello);
	}

}
