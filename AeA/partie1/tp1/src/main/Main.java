package main;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import logger.LoggerFactory;
import manager.BehaviorManager;
import parsing.CommandLineParser;
import parsing.options.algorithm.BoyerMooreOptionToAlgorithm;
import parsing.options.algorithm.BruteForceOptionToAlgorithm;
import parsing.options.algorithm.KMPOptionToAlgorithm;
import parsing.options.algorithm.KarpRabinOptionToAlgorithm;
import parsing.options.algorithm.OptionToAlgorithmParameters;
import parsing.options.algorithm.OptionToAlgorithmResult;
import parsing.options.algorithm.ShiftOrOptionToAlgorithm;
import parsing.options.strand.ComplementaryOptionToStrand;
import parsing.options.strand.OptionToStrandParameters;
import parsing.options.strand.OptionToStrandResult;
import parsing.options.strand.ReverseComplementaryOptionToStrand;
import parsing.options.strand.ReverseOptionToStrand;
import pattern.Genome;
import pattern.Strand;
import reader.ConfReader;
import reader.FastaFileReader;
import reader.util.InvalidFastaFileException;
import reader.util.NotAFastaFileException;
import writer.GnuplotWriter;
import algorithms.Algorithm;
import algorithms.util.StrandOccurences;
import bases.Alphabet;
import bases.PairingsManager;
import bases.util.NonExistentPairingException;

public class Main {

	private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
	
	private Main() {
		// Utility class : private constructor to hide the implicit 'public' declaration of the class
	}

	private static void printResult(Algorithm algorithm, List<Strand> strands,
			List<StrandOccurences> occurencesList, long executionTime) {
		LOGGER.log(Level.INFO, algorithm.toString());
		final Iterator<Strand> strandsIterator = strands.iterator();
		final Iterator<StrandOccurences> occurencesListIterator = occurencesList
				.iterator();
		int nbOccurences = 0;
		while (strandsIterator.hasNext()) {
			final Strand strand = strandsIterator.next();
			final StrandOccurences occurences = occurencesListIterator.next();
			LOGGER.info(strand + " : " + occurences);
			nbOccurences += occurences.getNbOccurences();
		}
		LOGGER.log(Level.INFO, nbOccurences + " occurences trouvees au total.");
		LOGGER.log(Level.INFO, algorithm.getNbComparisons()
				+ " comparaisons pour chaque mot.");
		LOGGER.log(Level.INFO, "Temps d'execution : " + executionTime
				+ " nanosecondes.\n");
	}

	private static void usage() {
		LOGGER.log(
				Level.SEVERE,
				"USAGE : java -jar strand_searching.jar filename [strand|N] --WITH [-comp|-rev|-revComp]* --USING [-bf|-so|-kr|-kmp|-bm]*");
		LOGGER.log(Level.SEVERE,
				"\tfilename : le nom du fichier fasta ou se trouve le genome a etudier");
		LOGGER.log(Level.SEVERE, "\t[strand|N] : permet de rechercher soit :");
		LOGGER.log(
				Level.SEVERE,
				"\t\tstrand : une sequence dont les occurences seront recherchees dans le genome");
		LOGGER.log(Level.SEVERE,
				"\t\tN : rechercher les occurences des mots de taille N");
		LOGGER.log(
				Level.SEVERE,
				"\t[-comp|-rev|-revComp] : permettent de rechercher egalement pour le mot entre ou les occurences des mots de taille N :");
		LOGGER.log(Level.SEVERE, "\t\tcomp : le complementaire");
		LOGGER.log(Level.SEVERE, "\t\trev : le reverse");
		LOGGER.log(Level.SEVERE, "\t\trevComp : le reverse-complementaire");
		LOGGER.log(Level.SEVERE,
				"\t\tdotplot : pour generer un dotplot comparant le genome a lui-meme");
		LOGGER.log(
				Level.SEVERE,
				"\t[-bf|-so|-kr|-kmp|-bm] : permet de spécifier le ou les algos a rechercher parmi :");
		LOGGER.log(Level.SEVERE, "\t\tbf : Brute-force");
		LOGGER.log(Level.SEVERE, "\t\tso : Shift-Or");
		LOGGER.log(Level.SEVERE, "\t\tkr : Karp-Rabin");
		LOGGER.log(Level.SEVERE, "\t\tkmp : Knutt-Morris-Pratt");
		LOGGER.log(Level.SEVERE, "\t\tbm : Boyer-Moore");
		LOGGER.log(
				Level.SEVERE,
				"EXEMPLE : java -jar strand_searching.jar donnees/simple.fasta TATA --WITH -revComp -comp -rev -kr --USING -bf -so -bm -kmp");
		LOGGER.log(Level.SEVERE,
				"Si aucun algorithme n'est specifie, l'algorithme de Boyer-Moore sera utilise");
	}

	public static void main(String[] args) throws IOException,
			InvalidFastaFileException, NotAFastaFileException,
			NonExistentPairingException {
		try {
			final ConfReader confReader = new ConfReader();
			confReader.read(args[0]);
			final Alphabet alphabet = confReader.getAlphabet();
			final PairingsManager pairingsManager = confReader
					.getPairingsManager();
			final FastaFileReader fastaFileReader = new FastaFileReader();
			final Genome genome = fastaFileReader.getGenomeFromFile(args[1],
					alphabet, pairingsManager);
			final BehaviorManager<OptionToStrandParameters, OptionToStrandResult> optionsToStrandsManager = new BehaviorManager<OptionToStrandParameters, OptionToStrandResult>();
			final BehaviorManager<OptionToAlgorithmParameters, OptionToAlgorithmResult> optionsToAlgorithmsManager = new BehaviorManager<OptionToAlgorithmParameters, OptionToAlgorithmResult>();
			optionsToStrandsManager.add(new ReverseOptionToStrand());
			optionsToStrandsManager.add(new ComplementaryOptionToStrand());
			optionsToStrandsManager
					.add(new ReverseComplementaryOptionToStrand());
			optionsToAlgorithmsManager.add(new BruteForceOptionToAlgorithm());
			optionsToAlgorithmsManager.add(new ShiftOrOptionToAlgorithm());
			optionsToAlgorithmsManager.add(new KarpRabinOptionToAlgorithm());
			optionsToAlgorithmsManager.add(new KMPOptionToAlgorithm());
			optionsToAlgorithmsManager.add(new BoyerMooreOptionToAlgorithm());
			final CommandLineParser parser = new CommandLineParser(args,
					pairingsManager, optionsToStrandsManager,
					optionsToAlgorithmsManager);
			final boolean parsing = parser.parseCommandLine(alphabet, 3);
			if (!parsing) {
				usage();
				return;
			}
			final List<Strand> strandsToLookFor = parser.getStrandsToLookFor();
			final List<Algorithm> algorithmsToUse = parser.getAlgorithmsToUse();
			LOGGER.log(Level.INFO, "taille du genome : " + genome.getSize());
			LOGGER.log(Level.INFO, "taille des motifs : "
					+ parser.getStrandsToLookFor().get(0).getSize());
			LOGGER.log(Level.INFO, "\n");
			List<StrandOccurences> occurences = null;
			for (final Algorithm algorithm : algorithmsToUse) {
				final long beginningTime = System.nanoTime();
				occurences = algorithm.findRepetitiveStrands(genome,
						strandsToLookFor, alphabet);
				final long executionTime = System.nanoTime() - beginningTime;
				printResult(algorithm, strandsToLookFor, occurences,
						executionTime);
			}
			if (parser.dotplotAsked()) {
				final GnuplotWriter gnuplotWriter = new GnuplotWriter();
				gnuplotWriter.generateDotplot("dotplot.plot", "dotplot.txt",
						"dotplot.jpg", genome, occurences);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			usage();
			return;
		}
	}

}
