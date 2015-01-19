package reader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import patterns.Alphabet;
import patterns.Genome;
import bases.Base;
import bases.BaseFlyweightFactory;

public class FastaFileReader {
	
	private static final String[] FILE_EXTENSIONS = { ".fasta", ".fa" };
	private static final char EXTENSION_MARKER = '.';
	private static final char FASTA_FILE_FIRST_CHARACTER = '>';
	private static final int NEWLINE_CHARACTER_ASCII_CODE = 10;
	private String filename;
	private BufferedReader reader;

	public FastaFileReader(String filename) throws InvalidFastaFileException, FileNotFoundException {
		this.filename = filename;
		if (!acceptFile()) {
			throw new InvalidFastaFileException();
		}
		reader = new BufferedReader(new FileReader(filename));
	}
	
	public boolean acceptFile() {
		final int extensionBeginning = filename.indexOf(EXTENSION_MARKER);
		String extension = filename.substring(extensionBeginning, filename.length());
		return validExtension(extension);
	}

	private boolean validExtension(String extension) {
		for (String fileExtension : FILE_EXTENSIONS) {
			if (fileExtension.equals(extension))
				return true;
		}
		return false;
	}
	
	public Genome getGenomeFromFile() throws IOException, InvalidFastaFileException {
		final List<Base> bases = new LinkedList<Base>();
		final BaseFlyweightFactory baseFactory = new BaseFlyweightFactory();
		final Set<Character> letters = new HashSet<Character>();
		if (reader.read() != (int) FASTA_FILE_FIRST_CHARACTER) {
			reader.close();
			throw new InvalidFastaFileException();
		}
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
		final Base[] baseArray = bases.toArray(new Base[bases.size()]);
		final Character[] lettersArray = letters.toArray(new Character[letters.size()]);
		final Alphabet alphabet = new Alphabet(lettersArray);
		return new Genome(baseArray, alphabet);
	}
	
	public void close() throws IOException {
		reader.close();
	}

}
