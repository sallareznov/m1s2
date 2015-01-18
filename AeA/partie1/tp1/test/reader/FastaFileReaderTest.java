package reader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FastaFileReaderTest {

	private FastaFileReader testedReader;
	private File validFastaFileExtension1;
	private File  validFastaFileExtension2;
	private File invalidFastaFileExtension;
	private File validFastaFileContent;
	private File invalidFastaFileContent;

	@Before
	public void setUp() throws Exception {
		validFastaFileExtension1 = new File("donnees/valid_fasta_file_extension_1.fasta");
		validFastaFileExtension2 = new File("donnees/valid_fasta_file_extension_2.fa");
		invalidFastaFileExtension = new File("donnees/invalid_fasta_file_extension.fst");
		invalidFastaFileContent = new File("donnees/invalid_fasta_file_content.fasta");
		validFastaFileContent = new File("donnees/exemple1.fasta");
		validFastaFileExtension1.createNewFile();
		validFastaFileExtension2.createNewFile();
		invalidFastaFileExtension.createNewFile();
		invalidFastaFileContent.createNewFile();
	}
	
	@After
	public void tearDown() {
		validFastaFileExtension1.delete();
		validFastaFileExtension2.delete();
		invalidFastaFileExtension.delete();
		invalidFastaFileContent.delete();
	}
	
	@Test
	public void testValidFastaFilesExtensions() throws FileNotFoundException, InvalidFastaFileException {
		testedReader = new FastaFileReader(validFastaFileExtension1.getAbsolutePath());
		testedReader = new FastaFileReader(validFastaFileExtension2.getAbsolutePath());
		return;
	}

	@Test(expected=InvalidFastaFileException.class)
	public void testValidFastaFilesExtension() throws FileNotFoundException, InvalidFastaFileException {
		testedReader = new FastaFileReader(invalidFastaFileExtension.getAbsolutePath());
		return;
	}
	
	@Test
	public void testValidFastaFileContent() throws IOException, InvalidFastaFileException {
		testedReader = new FastaFileReader(validFastaFileContent.getAbsolutePath());
		testedReader.getSequenceString();
		return;
	}
	
	@Test(expected=InvalidFastaFileException.class)
	public void testInvalidFastaFileContent() throws IOException, InvalidFastaFileException {
		final BufferedWriter writer = new BufferedWriter(new FileWriter(invalidFastaFileContent));
		writer.write("dumb content");
		writer.close();
		testedReader = new FastaFileReader(invalidFastaFileContent.getAbsolutePath());
		testedReader.getSequenceString();
		testedReader.close();
		
	}

}
