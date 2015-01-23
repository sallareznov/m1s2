package reader;

import static org.junit.Assert.assertEquals;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import patterns.Alphabet;
import patterns.Genome;
import reader.util.InvalidFastaFileException;
import reader.util.NotAFastaFileException;

public class FastaFileReaderTest {

	private FastaFileReader testedReader;
	private File validFastaFileExtension1;
	private File  validFastaFileExtension2;
	private File invalidFastaFileExtension;
	private File validFastaFileContent1;
	private File validFastaFileContent2;
	private File validFastaFileContent3;
	private File validFastaFileContent4;
	private File validFastaFileContent5;
	private File invalidFastaFileContent;

	@Before
	public void setUp() throws Exception {
		testedReader = new FastaFileReader();
		validFastaFileExtension1 = new File("donnees/valid_fasta_file_extension_1.fasta");
		validFastaFileExtension2 = new File("donnees/valid_fasta_file_extension_2.fa");
		invalidFastaFileExtension = new File("donnees/invalid_fasta_file_extension.fst");
		invalidFastaFileContent = new File("donnees/invalid_fasta_file_content.fasta");
		validFastaFileContent1 = new File("donnees/exemple1.fasta");
		validFastaFileContent2 = new File("donnees/exemple2.fasta");
		validFastaFileContent3 = new File("donnees/exemple3.fasta");
		validFastaFileContent4 = new File("donnees/exemple4.fasta");
		validFastaFileContent5 = new File("donnees/simple.fasta");
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
	public void testValidFastaFilesExtensions() throws FileNotFoundException, InvalidFastaFileException, NotAFastaFileException {
		testedReader.verifyExtension(validFastaFileExtension1.getAbsolutePath());
		testedReader.verifyExtension(validFastaFileExtension2.getAbsolutePath());
		return;
	}

	@Test(expected=NotAFastaFileException.class)
	public void testValidFastaFilesExtension() throws NotAFastaFileException, IOException {
		testedReader.verifyExtension(invalidFastaFileExtension.getAbsolutePath());
		return;
	}
	
	@Test
	public void testValidFastaFileContent() throws IOException, InvalidFastaFileException {
		testedReader.verifyContent(validFastaFileContent1.getAbsolutePath());
		testedReader.verifyContent(validFastaFileContent2.getAbsolutePath());
		testedReader.verifyContent(validFastaFileContent3.getAbsolutePath());
		testedReader.verifyContent(validFastaFileContent4.getAbsolutePath());
		return;
	}
	
	@Test(expected=InvalidFastaFileException.class)
	public void testInvalidFastaFileContent() throws IOException, InvalidFastaFileException {
		final BufferedWriter writer = new BufferedWriter(new FileWriter(invalidFastaFileContent));
		writer.write("dumb content");
		writer.close();
		testedReader.verifyContent(invalidFastaFileContent.getAbsolutePath());
		return;
	}
	
	@Test
	public void testGetGenome() throws IOException, InvalidFastaFileException, NotAFastaFileException {
		final Character[] letters = { 'A', 'C', 'G', 'T' };
		final Genome expectedGenome = new Genome("CTACTATATATC", new Alphabet(letters));
		final Genome actualGenome = testedReader.getGenomeFromFile(validFastaFileContent5.getAbsolutePath());
		assertEquals(expectedGenome, actualGenome);
	}

}
