package main;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import main.util.CommandLineParser;
import patterns.Strand;
import reader.util.InvalidFastaFileException;
import reader.util.NotAFastaFileException;
import algorithms.Algorithm;
import algorithms.util.StrandOccurences;

public class StrandSearching {

	private static void usage() {
		System.out.println("USAGE : java -jar strand_searching.jar filename [strand|N] --WITH [-comp|-rev|-revComp]* --USING [-bf|-so|-kr|-kmp|-bm]*");
		System.out.println("\tfilename : le nom du fichier fasta ou se trouve le genome a etudier");
		System.out.println("\t[strand|N] : permet de rechercher soit :");
		System.out.println("\t\tstrand : une sequence dont les occurences seront recherchees dans le genome");
		System.out.println("\t\tN : rechercher les occurences des mots de taille N");
		System.out.println("\t[-comp|-rev|-revComp] : permettent de rechercher egalement pour le mot entre ou les occurences des mots de taille N :");
		System.out.println("\t\tcomp : le complementaire");
		System.out.println("\t\trev : le reverse");
		System.out.println("\t\trevComp : le reverse-complementaire");
		System.out.println("\t[-bf|-so|-kr|-kmp|-bm] : permet de spécifier le ou les algos a rechercher parmi :");
		System.out.println("\t\tbf : Brute-force");
		System.out.println("\t\tso : Shift-Or");
		System.out.println("\t\tkr : Karp-Rabin");
		System.out.println("\t\tkmp : Knutt-Morris-Pratt");
		System.out.println("\t\tbm : Boyer-Moore");
		System.out.println("EXEMPLE : java -jar strand_searching.jar donnees/simple.fasta TATA --WITH -revComp -comp -rev -kr --USING -bf -so -bm -kmp");
	}
	
	private static void printResult(Algorithm algorithm, List<Strand> strands, List<StrandOccurences> occurencesList, long executionTime) {
		System.out.println(algorithm);
		final Iterator<Strand> strandsIterator = strands.iterator();
		final Iterator<StrandOccurences> occurencesListIterator = occurencesList.iterator();
		while (strandsIterator.hasNext()) {
			final Strand strand = strandsIterator.next();
			final StrandOccurences occurences = occurencesListIterator.next();
			System.out.println(strand + " : " + occurences);
		}
		System.out.println("Temps d'execution : " + executionTime + " nanosecondes.\n");
	}
	
	public static void main(String[] args) throws IOException, InvalidFastaFileException, NotAFastaFileException {
		final CommandLineParser parser = new CommandLineParser(args);
		final boolean parsing = parser.parseCommandLine();
		if (!parsing) {
			usage();
			return;
		}
		final List<Strand> strandsToLookFor = parser.getStrandsToLookFor();
		final List<Algorithm> algorithmsToUse = parser.getAlgorithmsToUse();
		for (final Algorithm algorithm : algorithmsToUse) {
			final long beginningTime = System.nanoTime();
			final List<StrandOccurences> occurences = algorithm
					.findRepetitiveStrands(parser.getGenome(), strandsToLookFor);
			final long executionTime = System.nanoTime() - beginningTime;
			printResult(algorithm, strandsToLookFor, occurences, executionTime);
		}
	}

}
