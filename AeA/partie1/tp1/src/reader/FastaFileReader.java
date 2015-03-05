package reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import pattern.Genome;
import reader.util.InvalidFastaFileException;
import reader.util.NotAFastaFileException;
import bases.Alphabet;
import bases.PairingsManager;
import bases.util.NonExistentPairingException;

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
	
	public Genome getGenomeFromFile(String filename, Alphabet alphabet, PairingsManager pairingsManager) throws InvalidFastaFileException, IOException, NotAFastaFileException, NonExistentPairingException {
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
		return new Genome(bases.toArray(new Character[bases.size()]), pairingsManager);
	}

}
