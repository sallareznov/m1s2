package reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import patterns.Genome;
import reader.util.InvalidFastaFileException;
import reader.util.NotAFastaFileException;
import bases.util.Alphabet;
import bases.util.NonExistentPairingException;
import bases.util.PairingsManager;

/**
 * Classe permettant de lire un fichier fasta 
 */
public class FastaFileReader {
	
	private static final String[] FILE_EXTENSIONS = { ".fasta", ".fa" };
	private static final char EXTENSION_MARKER = '.';
	private static final char FASTA_FILE_FIRST_CHARACTER = '>';
	private static final int NEWLINE_CHARACTER_ASCII_CODE = 10;
	private BufferedReader reader;

	/**
	 * teste si un fichier est un fichier fasta
	 * @param filename le nom du fichier
	 * @exception NotAFastaFileException si le fichier n'est pas un fichier fasta
	 */
	public void verifyExtension(String filename) throws NotAFastaFileException {
		final int extensionBeginning = filename.indexOf(EXTENSION_MARKER);
		String extension = filename.substring(extensionBeginning, filename.length());
		for (String fileExtension : FILE_EXTENSIONS) {
			if (fileExtension.equals(extension))
				return;
		}
		throw new NotAFastaFileException();
	}
	
	/**
	 * verifie que le contenu d'un fichier est bien conforme a la norme fasta 
	 * @param filename le nom du fichier
	 * @throws InvalidFastaFileException si le fichier n'est pas conforme
	 * @throws IOException si erreur au niveau du BufferedReader
	 */
	public void verifyContent(String filename) throws InvalidFastaFileException, IOException {
		reader = new BufferedReader(new FileReader(filename));
		if (reader.read() != (int) FASTA_FILE_FIRST_CHARACTER) {
			reader.close();
			throw new InvalidFastaFileException();
		}
	}
	
	public Genome test(String filename, Alphabet alphabet) throws InvalidFastaFileException, IOException, NotAFastaFileException, NonExistentPairingException {
		verifyExtension(filename);
		verifyContent(filename);
		final List<Character> bases = new LinkedList<Character>();
		reader.readLine();
		int characterCode = 0;
		while ((characterCode = reader.read()) != -1) {
			final char character = Character.toChars(characterCode)[0];
			if ((characterCode != NEWLINE_CHARACTER_ASCII_CODE) && alphabet.contains(character)) {
				bases.add(character);
			}
		}
		reader.close();
		return null;
	}
	
	/**
	 * lit un fichier fasta et renvoie son objet Genome, si ce fichier est correct
	 * @param filename le nom du fichier
	 * @return le genome du fichier
	 * @throws IOException si erreur au niveau du BufferedReader
	 * @throws InvalidFastaFileException si le fichier fasta n'est pas conforme
	 * @throws NotAFastaFileException si le fichier en parametre n'est pas un fichier fasta
	 */
	public Genome getGenomeFromFile(String filename) throws IOException, InvalidFastaFileException, NotAFastaFileException {
		verifyExtension(filename);
		verifyContent(filename);
		final List<Base> bases = new LinkedList<Base>();
		final BaseFlyweightFactory baseFactory = new BaseFlyweightFactory(Alphabet.DEFAULT_ALPHABET);
		final Set<Character> letters = new HashSet<Character>();
		// skip la premiere ligne du fichier
		reader.readLine();
		int characterCode;
		while ((characterCode = reader.read()) != -1) {
			if ((characterCode != NEWLINE_CHARACTER_ASCII_CODE)) {
				// ne fait rien si le caractere est deja dans l'alphabet
				final char character = Character.toChars(characterCode)[0];
				letters.add(character);
				final Base createdBase = baseFactory.createBase(character);
				bases.add(createdBase);
			}
		}
		reader.close();
		final Base[] baseArray = bases.toArray(new Base[bases.size()]);
		final Character[] lettersArray = letters.toArray(new Character[letters.size()]);
		final Alphabet alphabet = new Alphabet(lettersArray);
		return new Genome(baseArray, alphabet);
	}

}
