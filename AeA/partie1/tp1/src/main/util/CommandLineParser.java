package main.util;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

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

/**
 * Classe se chargeant de parser la ligne de commande de l'utilisateur
 */
public class CommandLineParser {

	private static final String HELP_FLAG = "--HELP";
	private static final String STRANDS_FLAG = "--WITH";
	private static final String ALGORITHMS_FLAG = "--USING";
	private static final String[] VALID_STRANDS_OPTIONS = { "-comp", "-rev",
			"-revComp" };
	private static final String[] VALID_ALGORITHMS_OPTIONS = { "-bf", "-so",
			"-kr", "-kmp", "-bm" };
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

	public void initGenome() throws IOException, InvalidFastaFileException,
			NotAFastaFileException {
		final FastaFileReader reader = new FastaFileReader();
		genome = reader.getGenomeFromFile(commandLine[0]);
	}

	public void initStrand() {
		mainStrand = new ConcreteStrand(commandLine[1]);
		strandsToLookFor.add(mainStrand);
	}

	public List<Strand> getStrandsToLookFor() {
		return strandsToLookFor;
	}

	public List<Algorithm> getAlgorithmsToUse() {
		return algorithmsToUse;
	}

	private static boolean isAStrandOption(String option) {
		for (int i = 0; i < VALID_STRANDS_OPTIONS.length; i++) {
			if (option.equals(VALID_STRANDS_OPTIONS[i]))
				return true;
		}
		return false;
	}

	private static boolean isAnAlgorithmOption(String option) {
		for (int i = 0; i < VALID_ALGORITHMS_OPTIONS.length; i++) {
			if (option.equals(VALID_ALGORITHMS_OPTIONS[i]))
				return true;
		}
		return false;
	}

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
		return i;
	}

}
