package main.util;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
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

    // FLAGS
    private static final String HELP_FLAG = "--HELP";
    private static final String STRANDS_FLAG = "--WITH";
    private static final String ALGORITHMS_FLAG = "--USING";
    // STRANDS OPTIONS
    private static final String REVERSE_OPTION = "-rev";
    private static final String COMPLEMENTARY_OPTION = "-comp";
    private static final String REVERSE_COMPLEMENTARY_OPTION = "-revComp";
    private static final String DOTPLOT_OPTION = "-dotplot";
    // ALGORITHMS OPTIONS
    private static final String BRUTE_FORCE_OPTION = "-bf";
    private static final String SHIFT_OR_OPTION = "-so";
    private static final String KARP_RABIN_OPTION = "-kr";
    private static final String KMP_OPTION = "-kmp";
    private static final String BOYER_MOORE_OPTION = "-bm";
    // DEFAULT ALGORITHM
    private static final Algorithm DEFAULT_ALGORITHM = new BoyerMooreAlgorithm();
    
    private String[] commandLine;
    private Genome genome;
    private Strand mainStrand;
    private List<Strand> strandsToLookFor;
    private List<Algorithm> algorithmsToUse;
    private boolean dotplot;

    /**
     * construit le parser
     * 
     * @param args
     *            le tableau d'arguments
     * @throws IOException
     * @throws InvalidFastaFileException
     * @throws NotAFastaFileException
     */
    public CommandLineParser(String[] args) throws IOException, InvalidFastaFileException, NotAFastaFileException {
	commandLine = args;
	strandsToLookFor = new LinkedList<Strand>();
	algorithmsToUse = new LinkedList<Algorithm>();
	dotplot = false;
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

    /**
     * le genome etudie 
     */
    public Genome getGenome() {
	return genome;
    }
    
    /**
     * 
     */
    public boolean dotplotAsked() {
	return dotplot;
    }
    
    /**
     * initialise le genome a partir du fichier entre par l'utilisateur
     * 
     * @throws IOException
     *             si erreur de lecture
     * @throws InvalidFastaFileException
     *             si le fichier n'a pas un contenu correspondant a la norme fasta
     * @throws NotAFastaFileException
     *             si le fichier n'est pas un fichier fasta
     */
    private void initGenome() throws IOException, InvalidFastaFileException, NotAFastaFileException {
	final FastaFileReader reader = new FastaFileReader();
	genome = reader.getGenomeFromFile(commandLine[0]);
    }

    /**
     * initialise le brin
     */
    private void initStrand() {
	mainStrand = new ConcreteStrand(commandLine[1], genome.getAlphabet());
	strandsToLookFor.add(mainStrand);
    }
    
    private static boolean isAnOption(String option) {
	return (option.charAt(0) == '-' && option.charAt(1) != '-');
    }
    
    private Strand optionToStrand(Strand strand, String option) {
	if (option.equals(REVERSE_OPTION)) {
	    return strand.getReverse();
	}
	else {
	    if (option.equals(COMPLEMENTARY_OPTION)) {
		return strand.getComplementary();
	    }
	    else {
		if (option.equals(REVERSE_COMPLEMENTARY_OPTION)) {
		    return strand.getReverseComplementary();
		}
		else {
		    if (option.equals(DOTPLOT_OPTION)) {
			dotplot = true;
		    }
		}
	    }
	    return strand;
	}
    }
    
    private void treatOption(String option) {
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

    private int verifyStrandsOptions(int currentIndex) {
	int i = currentIndex;
	while (i < commandLine.length && isAnOption(commandLine[i])) {
	    treatOption(commandLine[i]);
	    i++;
	}
	return i;
    }

    private static Strand nextStrand(Strand currentStrand, Alphabet alphabet) {
	Base[] nextStrandContent = currentStrand.getContent();
	int currentIndex = nextStrandContent.length - 1;
	boolean done = false;
	final BaseFlyweightFactory baseFactory = new BaseFlyweightFactory(alphabet);
	while ((currentIndex >= 0) && (!done)) {
	    final char currentLetter = nextStrandContent[currentIndex].getWording();
	    final int currentValue = alphabet.getIndex(currentLetter);
	    if (currentValue < (alphabet.getSize() - 1)) {
		final char nextLetter = alphabet.getLetters()[currentValue + 1];
		nextStrandContent[currentIndex] = baseFactory.createBase(nextLetter);
		done = true;
	    } else {
		nextStrandContent[currentIndex] = baseFactory.createBase(alphabet.getLetters()[0]);
		currentIndex--;
	    }
	}
	if (currentIndex >= 0) {
	    return new ConcreteStrand(nextStrandContent);
	} else {
	    return null;
	}
    }

    private void buildAllStrands(int n, Alphabet alphabet) {
	char[] firstStrandArray = new char[n];
	Arrays.fill(firstStrandArray, alphabet.getLetters()[0]);
	String firstStrandString = new String(firstStrandArray);
	strandsToLookFor.add(new ConcreteStrand(firstStrandString, alphabet));
	Strand currentStrand = strandsToLookFor.get(0);
	while ((currentStrand = nextStrand(currentStrand, alphabet)) != null) {
	    strandsToLookFor.add(currentStrand.clone());
	}
    }
    
    private int verifyAlgorithmsOptions(int currentIndex) {
	int i = currentIndex;
	while (i < commandLine.length && isAnOption(commandLine[i])) {
	    final String option = commandLine[i];
	    if (option.equals(BRUTE_FORCE_OPTION)) {
		algorithmsToUse.add(new BruteForceAlgorithm());
	    } else {
		if (option.equals(SHIFT_OR_OPTION)) {
		    algorithmsToUse.add(new ShiftOrAlgorithm());
		} else {
		    if (option.equals(KARP_RABIN_OPTION)) {
			algorithmsToUse.add(new KarpRabinAlgorithm());
		    } else {
			if (option.equals(KMP_OPTION)) {
			    algorithmsToUse.add(new KMPAlgorithm());
			} else {
			    if (option.equals(BOYER_MOORE_OPTION)) {
				algorithmsToUse.add(new BoyerMooreAlgorithm());
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

    public boolean parseCommandLine() throws IOException, InvalidFastaFileException, NotAFastaFileException {
	if ((commandLine.length < 3) || ((commandLine.length > 0) && (commandLine[0].equals(HELP_FLAG)))) {
	    return false;
	}
	initGenome();
	try {
	    final int strandsLength = Integer.parseInt(commandLine[1]);
	    buildAllStrands(strandsLength, genome.getAlphabet());
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
		} else {
		    currentIndex++;
		}
	    }
	}
	return true;
    }

}
