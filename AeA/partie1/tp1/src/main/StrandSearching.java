package main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import main.util.CommandLineParser;
import patterns.Genome;
import patterns.Strand;
import reader.util.InvalidFastaFileException;
import reader.util.NotAFastaFileException;
import algorithms.Algorithm;
import algorithms.util.StrandOccurences;
import bases.Base;

public class StrandSearching {

	private static final String DATA_FILENAME = "dotplot.txt";
	private static final String GNUPLOT_FILENAME = "dotplot.plot";
	private static final String OUTPUT_FILENAME = "dotplot.jpg";

	private static void buildGnuplotFile(int genomeSize) throws IOException {
		BufferedWriter gnuplotWriter = new BufferedWriter(new FileWriter(
				GNUPLOT_FILENAME));
		gnuplotWriter.write("set term jpeg;\n");
		gnuplotWriter.write("set nokey;\n");
		gnuplotWriter.write("set output '" + OUTPUT_FILENAME + "';\n");
		gnuplotWriter.write("set xrange[0:" + (genomeSize) + "];\n");
		gnuplotWriter.write("set yrange[0:" + (genomeSize) + "];\n");
		gnuplotWriter.write("plot '" + DATA_FILENAME
				+ "' with points pointtype 0;\n");
		gnuplotWriter.close();
	}

	private static void buildDataFile(Genome genome,
			List<StrandOccurences> occurences) throws IOException {
		final BufferedWriter dataWriter = new BufferedWriter(new FileWriter(
				DATA_FILENAME));
		final Base[] bases = genome.getBases();
		for (final StrandOccurences strandOccurence : occurences) {
			final List<Integer> indexes = strandOccurence.getOccurences();
			for (final int index : indexes) {
				for (final int index2 : indexes) {
					if (bases[index].equals(bases[index2])) {
						dataWriter.write(index + " " + index2 + "\n");
					}
				}
			}
		}
		dataWriter.close();
	}

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

	private static void generateDotplot(Genome genome,
			List<StrandOccurences> occurences) throws IOException {
		buildDataFile(genome, occurences);
		buildGnuplotFile(genome.getSize());
		final Runtime rt = Runtime.getRuntime();
		System.out.println("Generation du dotplot...");
		rt.exec("gnuplot " + GNUPLOT_FILENAME);
		System.out.println("Dotplot genere avec succes (fichier dotplot.jpg)");
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
			InvalidFastaFileException, NotAFastaFileException {
		final CommandLineParser parser = new CommandLineParser(args);
		final boolean parsing = parser.parseCommandLine();
		if (!parsing) {
			usage();
			return;
		}
		final List<Strand> strandsToLookFor = parser.getStrandsToLookFor();
		final List<Algorithm> algorithmsToUse = parser.getAlgorithmsToUse();
		System.out
				.println("taille du genome : " + parser.getGenome().getSize());
		System.out.println("taille des motifs : "
				+ parser.getStrandsToLookFor().get(0).getSize());
		System.out.println();
		List<StrandOccurences> occurences = null;
		for (final Algorithm algorithm : algorithmsToUse) {
			final long beginningTime = System.nanoTime();
			occurences = algorithm.findRepetitiveStrands(parser.getGenome(),
					strandsToLookFor);
			final long executionTime = System.nanoTime() - beginningTime;
			printResult(algorithm, parser.getGenome(), strandsToLookFor,
					occurences, executionTime);
		}
		if (parser.dotplotAsked())
			generateDotplot(parser.getGenome(), occurences);
	}

}
