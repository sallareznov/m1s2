package reader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FastaFileReader {
	
	private static final String[] FILE_EXTENSIONS = { ".fasta", ".fa" };
	private static final char EXTENSION_MARKER = '.';
	private static final char FASTA_FILE_FIRST_CHARACTER = '>';
	private static final int NEWLINE_ASCII_CODE = 10;
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
	
	public String getSequenceString() throws IOException, InvalidFastaFileException {
		if (reader.read() != (int) FASTA_FILE_FIRST_CHARACTER) {
			reader.close();
			throw new InvalidFastaFileException();
		}
		final StringBuilder text = new StringBuilder();
		// skip la premiere ligne du fichier
		reader.readLine();
		int characterCode;
		while ((characterCode = reader.read()) != -1) {
			if (characterCode != NEWLINE_ASCII_CODE)
			text.append(Character.toChars(characterCode)[0]);
		}
		return text.toString();
	}
	
	public void close() throws IOException {
		reader.close();
	}

}
