package parsing;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import parsing.options.algorithm.OptionsToAlgorithmsManager;
import parsing.options.strand.OptionsToStrandsManager;
import pattern.Strand;
import reader.util.InvalidFastaFileException;
import reader.util.NotAFastaFileException;
import algorithms.Algorithm;
import algorithms.KMPAlgorithm;
import bases.Alphabet;
import bases.PairingsManager;
import bases.util.NonExistentPairingException;

/**
 * Classe se chargeant de parser la ligne de commande de l'utilisateur
 */
public class CommandLineParser {

	private OptionsToStrandsManager optionsToStrandsManager;
	private OptionsToAlgorithmsManager optionsToAlgorithmsManager;

	// FLAGS
	private static final String HELP_FLAG = "--HELP";
	private static final String STRANDS_FLAG = "--WITH";
	private static final String ALGORITHMS_FLAG = "--USING";
	private static final String DOTPLOT_FLAG = "--DOTPLOT";

	private String[] commandLine;
	private Strand mainStrand;
	private List<Strand> strandsToLookFor;
	private List<Algorithm> algorithmsToUse;
	private boolean dotplot;
	private PairingsManager pairingsManager;

	/**
	 * construit le parser
	 * 
	 * @param args
	 *            le tableau d'arguments
	 * @throws IOException
	 * @throws InvalidFastaFileException
	 * @throws NotAFastaFileException
	 */
	public CommandLineParser(String[] args, PairingsManager pairingsManager,
			OptionsToStrandsManager optionsToStrandsManager,
			OptionsToAlgorithmsManager optionsToAlgorithmsManager)
			throws IOException, InvalidFastaFileException,
			NotAFastaFileException {
		commandLine = args;
		strandsToLookFor = new LinkedList<Strand>();
		algorithmsToUse = new LinkedList<Algorithm>();
		dotplot = false;
		this.pairingsManager = pairingsManager;
		this.optionsToStrandsManager = optionsToStrandsManager;
		this.optionsToAlgorithmsManager = optionsToAlgorithmsManager;
	}

	/**
	 * @return la liste des brins a rechercher
	 */
	public List<Strand> getStrandsToLookFor() {
		return strandsToLookFor;
	}

	/**
	 * @return la liste des algorithmes choisis par l'utilisateur
	 */
	public List<Algorithm> getAlgorithmsToUse() {
		return algorithmsToUse;
	}

	/**
	 * @return <code>true</code> si l'utilisateur desire un dotplot
	 */
	public boolean dotplotAsked() {
		return dotplot;
	}

	/**
	 * initialise le brin
	 */
	private void initStrand() {
		mainStrand = new Strand(commandLine[1], pairingsManager);
		strandsToLookFor.add(mainStrand);
	}

	/**
	 * analyse une option
	 * 
	 * @param option
	 *            l'option a analyser
	 * @return <code>true</code> si l'option est valide (commence par un tiret
	 *         simple)
	 */
	private static boolean isAnOption(String option) {
		return (option.charAt(0) == '-' && option.charAt(1) != '-');
	}

	/**
	 * renvoie le brin correspondant a l'option choisie par l'utilisateur
	 * 
	 * @param strand
	 *            le brin etudie
	 * @param option
	 *            l'option appliquee sur le brin
	 * @return le brin correspondant a l'option
	 * @throws NonExistentPairingException
	 */
	private Strand optionToStrand(Strand strand, String option)
			throws NonExistentPairingException {
		return optionsToStrandsManager.getAdequateStrand(option, strand);
	}

	/**
	 * met a jour la liste des brins en fonction de l'option
	 * 
	 * @param option
	 * @throws NonExistentPairingException
	 */
	private void treatOption(String option) throws NonExistentPairingException {
		final List<Strand> addedStrands = new LinkedList<Strand>();
		final Iterator<Strand> strandIterator = strandsToLookFor.iterator();
		while (strandIterator.hasNext()) {
			final Strand currentStrand = strandIterator.next();
			final Strand optionnedStrand = optionToStrand(currentStrand, option);
			if (!strandsToLookFor.contains(optionnedStrand))
				addedStrands.add(optionnedStrand);
		}
		strandsToLookFor.addAll(addedStrands);
	}

	private int verifyStrandsOptions(int currentIndex)
			throws NonExistentPairingException {
		int i = currentIndex;
		while (i < commandLine.length && isAnOption(commandLine[i])) {
			treatOption(commandLine[i]);
			i++;
		}
		return i;
	}

	private Strand nextStrand(Strand currentStrand, Alphabet alphabet) {
		Character[] nextStrandContent = currentStrand.getContent();
		int currentIndex = nextStrandContent.length - 1;
		boolean done = false;
		while ((currentIndex >= 0) && (!done)) {
			final char currentLetter = nextStrandContent[currentIndex];
			final int currentValue = alphabet.getIndex(currentLetter);
			if (currentValue < (alphabet.size() - 1)) {
				final char nextLetter = alphabet.getBases()[currentValue + 1];
				nextStrandContent[currentIndex] = nextLetter;
				done = true;
			} else {
				nextStrandContent[currentIndex] = alphabet.getBases()[0];
				currentIndex--;
			}
		}
		if (currentIndex >= 0) {
			return new Strand(nextStrandContent, pairingsManager);
		} else {
			return null;
		}
	}

	private void buildAllStrands(int n, Alphabet alphabet) {
		char[] firstStrandArray = new char[n];
		Arrays.fill(firstStrandArray, alphabet.getBases()[0]);
		String firstStrandString = new String(firstStrandArray);
		strandsToLookFor.add(new Strand(firstStrandString, pairingsManager));
		Strand currentStrand = strandsToLookFor.get(0);
		while ((currentStrand = nextStrand(currentStrand, alphabet)) != null) {
			strandsToLookFor.add(currentStrand.clone());
		}
	}

	private int verifyAlgorithmsOptions(int currentIndex) {
		int i = currentIndex;
		while (i < commandLine.length && isAnOption(commandLine[i])) {
			final String option = commandLine[i];
			algorithmsToUse.add(optionsToAlgorithmsManager
					.getAdequateAlgorithm(option));
			i++;
		}
		return i;
	}

	public boolean parseCommandLine(Alphabet alphabet) throws IOException,
			InvalidFastaFileException, NotAFastaFileException,
			NonExistentPairingException {
		if ((commandLine.length < 2)
				|| ((commandLine.length > 0) && (commandLine[0]
						.equals(HELP_FLAG)))) {
			return false;
		}
		try {
			final int strandsLength = Integer.parseInt(commandLine[1]);
			buildAllStrands(strandsLength, alphabet);
		} catch (NumberFormatException e) {
			initStrand();
		}
		int currentIndex = 2;
		while (currentIndex < commandLine.length) {
			if (commandLine[currentIndex].equals(STRANDS_FLAG)) {
				currentIndex = verifyStrandsOptions(currentIndex + 1);
			} else if (commandLine[currentIndex].equals(ALGORITHMS_FLAG)) {
				currentIndex = verifyAlgorithmsOptions(currentIndex + 1);
			} else if (commandLine[currentIndex].equals(DOTPLOT_FLAG)) {
				dotplot = true;
				currentIndex++;
			} else {
				currentIndex++;
			}
		}
		if (algorithmsToUse.isEmpty()) {
			algorithmsToUse.add(new KMPAlgorithm());
		}
		return true;
	}

}
