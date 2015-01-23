package main;

import java.io.IOException;
import java.util.Iterator;
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
import algorithms.util.StrandOccurences;

public class StrandSearching {
	
	private static List<Strand> buildStrandList(String[] args, Strand mainStrand) {
		final List<Strand> strands = new LinkedList<Strand>();
		strands.add(mainStrand);
		for (int i = 2 ; i < args.length ; i++) {
			if (args[i].equals("-comp")) {
				strands.add(mainStrand.getComplementary());
			}
			else {
				if (args[i].equals("-rev")) {
					strands.add(mainStrand.getReverse());
				}
				else {
					if (args[i].equals("-revComp")) {
						strands.add(mainStrand.getReverseComplementary());
					}
				}
			}
		}
		return strands;
	}

	private static Algorithm getChosenAlgorithm(String option, Genome genome) {
		if (option.equals("-bf")) {
			return new BruteForceAlgorithm(genome);
		}
		else {
			if (option.equals("-so")) {
				return new ShiftOrAlgorithm(genome);
			}
			else {
				if (option.equals("-kr")) {
					return new KarpRabinAlgorithm(genome);
				}
				else {
					if (option.equals("-kmp")) {
						return new KMPAlgorithm(genome);
					}
					else {
						if (option.equals("-bm")) {
							return new BoyerMooreAlgorithm(genome);
						}
						else {
							throw new IllegalArgumentException("Option incorrecte : " + option);							
						}
					}
				}
			}
		}
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

	private static void treatIntCase(String filename, String[] args) {
		//final int n = Integer.parseInt(args[1]);
		// MOTS DE TAILLE N
	}

	private static void treatStringCase(String filename, String[] args)
			throws IOException, InvalidFastaFileException,
			NotAFastaFileException {
		final String strandString = args[1];
		final Strand strand = new ConcreteStrand(strandString);
		final FastaFileReader reader = new FastaFileReader();
		final Genome genome = reader.getGenomeFromFile(filename);
		final List<Algorithm> chosenAlgorithms = new LinkedList<Algorithm>();
		for (int i = 2 ; i < args.length ; i++) {
			chosenAlgorithms.add(getChosenAlgorithm(args[i], genome));
		}
		final List<Strand> strands = buildStrandList(args, strand);
		for (final Algorithm algorithm : chosenAlgorithms) {
			final long beginningTime = System.nanoTime();
			final List<StrandOccurences> occurences = algorithm.findRepetitiveStrands(strands);
			final long executionTime = System.nanoTime() - beginningTime;
			printResult(algorithm, strands, occurences, executionTime);
		}
	}

	private static void usage() {
		System.out.println("USAGE : java -jar strand_searching.jar filename [strand|N] [-comp|-rev|-revComp]* [-bf|-so|-kr|-kmp|-bm]*");
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
		System.out.println("EXEMPLE : java -jar strand_searching.jar donnees/simple.fasta TATA -revComp -comp -rev -kr -bf -so -bm -kmp");
	}

	public static void main(String[] args) throws IOException,
			InvalidFastaFileException, NotAFastaFileException {
		if (args.length < 3) {
			usage();
			return;
		}
		final String filename = args[0];
		try {
			treatIntCase(filename, args);
		} catch (NumberFormatException e) {
			treatStringCase(filename, args);
		}
	}

}
