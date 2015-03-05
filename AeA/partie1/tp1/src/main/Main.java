package main;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import parsing.CommandLineParser;
import parsing.options.algorithm.BoyerMooreOptionToAlgorithm;
import parsing.options.algorithm.BruteForceOptionToAlgorithm;
import parsing.options.algorithm.KMPOptionToAlgorithm;
import parsing.options.algorithm.KarpRabinOptionToAlgorithm;
import parsing.options.algorithm.OptionsToAlgorithmsManager;
import parsing.options.algorithm.ShiftOrOptionToAlgorithm;
import parsing.options.strand.ComplementaryOptionToStrand;
import parsing.options.strand.OptionsToStrandsManager;
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

	private static void printResult(Algorithm algorithm, Genome genome,
			List<Strand> strands, List<StrandOccurences> occurencesList,
			long executionTime) {
		System.out.println(algorithm);
		final Iterator<Strand> strandsIterator = strands.iterator();
		final Iterator<StrandOccurences> occurencesListIterator = occurencesList
				.iterator();
		int nbOccurences = 0;
		while (strandsIterator.hasNext()) {
			final Strand strand = strandsIterator.next();
			final StrandOccurences occurences = occurencesListIterator.next();
			System.out.println(strand + " : " + occurences);
			nbOccurences += occurences.getNbOccurences();
		}
		System.out.println(nbOccurences + " occurences trouvees au total.");
		System.out.println(algorithm.getNbComparisons()
				+ " comparaisons pour chaque mot.");
		System.out.println("Temps d'execution : " + executionTime
				+ " nanosecondes.\n");
	}

	private static void usage() {
		System.out
				.println("USAGE : java -jar strand_searching.jar filename [strand|N] --WITH [-comp|-rev|-revComp]* --USING [-bf|-so|-kr|-kmp|-bm]*");
		System.out
				.println("\tfilename : le nom du fichier fasta ou se trouve le genome a etudier");
		System.out.println("\t[strand|N] : permet de rechercher soit :");
		System.out
				.println("\t\tstrand : une sequence dont les occurences seront recherchees dans le genome");
		System.out
				.println("\t\tN : rechercher les occurences des mots de taille N");
		System.out
				.println("\t[-comp|-rev|-revComp] : permettent de rechercher egalement pour le mot entre ou les occurences des mots de taille N :");
		System.out.println("\t\tcomp : le complementaire");
		System.out.println("\t\trev : le reverse");
		System.out.println("\t\trevComp : le reverse-complementaire");
		System.out
				.println("\t\tdotplot : pour generer un dotplot comparant le genome a lui-meme");
		System.out
				.println("\t[-bf|-so|-kr|-kmp|-bm] : permet de spécifier le ou les algos a rechercher parmi :");
		System.out.println("\t\tbf : Brute-force");
		System.out.println("\t\tso : Shift-Or");
		System.out.println("\t\tkr : Karp-Rabin");
		System.out.println("\t\tkmp : Knutt-Morris-Pratt");
		System.out.println("\t\tbm : Boyer-Moore");
		System.out
				.println("EXEMPLE : java -jar strand_searching.jar donnees/simple.fasta TATA --WITH -revComp -comp -rev -kr --USING -bf -so -bm -kmp");
		System.out
				.println("Si aucun algorithme n'est specifie, l'algorithme de Boyer-Moore sera utilise");
	}

	public static void main(String[] args) throws IOException,
			InvalidFastaFileException, NotAFastaFileException,
			NonExistentPairingException {
		try {
			final ConfReader confReader = new ConfReader();
			confReader.read("init.conf");
			final Alphabet alphabet = confReader.getAlphabet();
			final PairingsManager pairingsManager = confReader
					.getPairingsManager();
			final FastaFileReader fastaFileReader = new FastaFileReader();
			final Genome genome = fastaFileReader.getGenomeFromFile(args[0],
					alphabet, pairingsManager);
			final OptionsToStrandsManager optionsToStrandsManager = new OptionsToStrandsManager();
			final OptionsToAlgorithmsManager optionsToAlgorithmsManager = new OptionsToAlgorithmsManager();
			optionsToStrandsManager
					.addOptionToStrand(new ReverseOptionToStrand());
			optionsToStrandsManager
					.addOptionToStrand(new ComplementaryOptionToStrand());
			optionsToStrandsManager
					.addOptionToStrand(new ReverseComplementaryOptionToStrand());
			optionsToAlgorithmsManager
					.addOptionToAlgorithm(new BruteForceOptionToAlgorithm());
			optionsToAlgorithmsManager
					.addOptionToAlgorithm(new ShiftOrOptionToAlgorithm());
			optionsToAlgorithmsManager
					.addOptionToAlgorithm(new KarpRabinOptionToAlgorithm());
			optionsToAlgorithmsManager
					.addOptionToAlgorithm(new KMPOptionToAlgorithm());
			optionsToAlgorithmsManager
					.addOptionToAlgorithm(new BoyerMooreOptionToAlgorithm());
			final CommandLineParser parser = new CommandLineParser(args,
					pairingsManager, optionsToStrandsManager,
					optionsToAlgorithmsManager);
			final boolean parsing = parser.parseCommandLine(alphabet);
			if (!parsing) {
				usage();
				return;
			}
			final List<Strand> strandsToLookFor = parser.getStrandsToLookFor();
			final List<Algorithm> algorithmsToUse = parser.getAlgorithmsToUse();
			System.out.println("taille du genome : " + genome.getSize());
			System.out.println("taille des motifs : "
					+ parser.getStrandsToLookFor().get(0).getSize());
			System.out.println();
			List<StrandOccurences> occurences = null;
			for (final Algorithm algorithm : algorithmsToUse) {
				final long beginningTime = System.nanoTime();
				occurences = algorithm.findRepetitiveStrands(genome,
						strandsToLookFor, alphabet);
				final long executionTime = System.nanoTime() - beginningTime;
				printResult(algorithm, genome, strandsToLookFor, occurences,
						executionTime);
			}
			if (parser.dotplotAsked()) {
				final GnuplotWriter gnuplotWriter = new GnuplotWriter();
				gnuplotWriter.generateDotplot(
						"dotplot.plot", "dotplot.txt", "dotplot.jpg", genome,
						occurences);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			usage();
			return;
		}
	}

}
